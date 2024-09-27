package org.example.ploblem1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.*;

class CategoryManager {
    private final Map<Integer, Category> categoryMap;
    private final Map<Integer, List<Integer>> connectMap; //parentId 와 childId 리스트
    private final ObjectMapper objectMapper;
    private static final String NOT_FOUND_CATEGORY = "{\"error\": \"결과가 없습니다.\"}";

    public CategoryManager() {
        this.categoryMap = new HashMap<>();
        this.connectMap = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // 카테고리 add
    public void addCategory(String name, int childId, Integer parentId) {
        Category newCategory = new Category(childId, name);
        categoryMap.put(childId, newCategory);

        if (parentId != null) {
            if (connectMap.containsKey(parentId)) {
                connectMap.get(parentId).add(childId);
            } else {
                List<Integer> childList = new ArrayList<>();
                childList.add(childId);
                connectMap.put(parentId, childList);
            }
        } else {
            if (!connectMap.containsKey(childId)) {
                connectMap.put(childId, new ArrayList<>());
            }
        }
    }

    // id로 카테고리 찾기
    public String searchById(int id) throws JsonProcessingException {
        if (categoryMap.containsKey(id)) {
            Map<String, Object> result = convertToJson(id);
            return objectMapper.writeValueAsString(result);
        } else {
            return NOT_FOUND_CATEGORY;
        }
    }

    //이름으로 카테고리 찾기
    public String searchByName(String name) throws JsonProcessingException {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Category category : categoryMap.values()) {
            if (category.getName().equals(name)) {
                result.add(convertToJson(category.getId()));
            }
        }
        return !result.isEmpty() ? objectMapper.writeValueAsString(result) : NOT_FOUND_CATEGORY;
    }

    //전체 카테고리 조회
    public String getAllCategory() throws JsonProcessingException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Integer id : connectMap.keySet()) {
            if (!isChildCategory(id)) {
                resultList.add(convertToJson(id));
            }
        }
        return objectMapper.writeValueAsString(resultList);
    }

    private boolean isChildCategory(int id) {
        for (List<Integer> childlist : connectMap.values()) {
            if (childlist.contains(id)) {
                return true;
            }
        }
        return false;
    }

    private Map<String, Object> convertToJson(int id) {
        Map<String, Object> jsonObject = new LinkedHashMap<>();
        Category category = categoryMap.get(id);
        jsonObject.put("id", category.getId());
        jsonObject.put("카테고리명", category.getName());

        List<Integer> children = connectMap.get(id);
        if (children != null && !children.isEmpty()) {
            List<Map<String, Object>> childList = new ArrayList<>();
            for (Integer childId : children) {
                childList.add(convertToJson(childId));
            }
            jsonObject.put("하위카테고리", childList);
        }
        return jsonObject;
    }

}
