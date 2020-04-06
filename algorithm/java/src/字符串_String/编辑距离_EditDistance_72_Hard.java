package 字符串_String;

import java.util.Arrays;

public class 编辑距离_EditDistance_72_Hard {

    public static void main(String[] args) {
        Solution72 solution = new Solution72();

        int ans = solution.minDistance("horse", "ros");
        System.out.println(ans);
    }
}

class Solution72 {

    private int getMin(int ... arrs) {
        return Arrays.stream(arrs).min().getAsInt();
    }

    public int minDistance(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();

        if (len1 == 0)
            return len2;
        if (len2 == 0)
            return len1;

        int [][]dp = new int[len1][len2];

        dp[0][0] = (word1.charAt(0) == word2.charAt(0)) ? 0 : 1;

        for (int j = 1; j <len2; j++) {
            dp[0][j] = (word1.charAt(0) == word2.charAt(j)) ? j : dp[0][j-1] + 1;
        }

        for (int i = 1; i < len1; i++) {
            dp[i][0] = (word1.charAt(i) == word2.charAt(0)) ? i : dp[i-1][0] + 1;
        }

        for (int i = 1; i < len1; i++) {
            for(int j = 1; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = getMin(dp[i-1][j-1], dp[i][j-1], dp[i-1][j]) + 1;
                }
            }
        }

        return dp[len1-1][len2-1];

    }
}
