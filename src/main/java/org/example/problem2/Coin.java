package org.example.problem2;

/*
* [2번 문제]
* 서로 다른 종류의 통화를 나타내는 N 크기의 coin[ ] 정수 배열과 정수 합계가 주어지면, coin[]의 다양한 조합을 사용하여 합계를 만드는 방법의 수를 찾는 것이 과제입니다.

참고: 각 유형의 동전이 무한정 공급된다고 가정합니다.

입력: sum = 4, coins[] = {1,2,3},
출력: 4
설명: {1, 1, 1, 1}, {1, 1, 2}, {2, 2}, {1, 3}의 네 가지 솔루션이 있습니다.


결과 예시)
입력: 합계 = 10, coins[] = {2, 5, 3, 6}
출력: 5
설명: 다섯 가지 솔루션이 있습니다.
{2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} 및 {5,5}.
* */
public class Coin
{
    public static int count(int[] coins, int sum) {
        int[] dp = new int[sum + 1];

        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= sum; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[sum];
    }


    public static void main(String[] args) {

        int[] coins = {1, 2, 3};
        int sum = 4;
        System.out.println("예제 1 : " + count(coins, sum));

        int[] coins2 = {2, 5, 3, 6};
        int sum2 = 10;
        System.out.println("예제 2 : " + count(coins2, sum2));
    }

}
