package org.example.ploblem1;
;

public class Main {
    public static void main(String[] args) throws Exception {
        CategoryManager manager = new CategoryManager();

        // 카테고리 만들기
        manager.addCategory("남자", 0, null);
        manager.addCategory("엑소", 1, 0);
        manager.addCategory("공지사항", 2, 1);
        manager.addCategory("방탄소년단", 3, 0);
        manager.addCategory("공지사항", 4, 3);
        manager.addCategory("익명게시판", 5, 3);

        manager.addCategory("여자", 6, null);
        manager.addCategory("블랙핑크", 7, 6);
        manager.addCategory("공지사항", 8, 7);
        manager.addCategory("익명게시판", 5, 7); //익명은 id 같음


        System.out.println("전체 카테고리 : ");
        String allCategoriesJson = manager.getAllCategory();
        System.out.println(allCategoriesJson);

        System.out.println("식별자로 검색 : ");
        String categoryByIdJson = manager.searchById(3);
        System.out.println(categoryByIdJson);

        System.out.println("이름으로 검색 : ");
        String categoryByNameJson = manager.searchByName("블랙핑크");
        System.out.println(categoryByNameJson);
    }
}
