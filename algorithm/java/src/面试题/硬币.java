package 面试题;

public class 硬币 {
    public static void main(String[] args) {

        Solution0811 solution = new Solution0811();

        int result = solution.waysToChange(5);
    }
}

class Solution0811 {

    public int waysToChange(int n) {
        if (n == 0)
            return 1;
        int[] cents = new int[]{0,1,5,10,25};
        int[][] dp = new int[5][n+1];

        for (int i = 0; i <= 4; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
            dp[1][j] = 1;
        }

        for (int i = 2; i <= 4; i++) {
            for (int j = cents[i]; j <= n; j++) {
                dp[i][j] = dp[i-1][j-cents[i]] + dp[i-1][j];
            }
        }
        return dp[4][n];
    }
}