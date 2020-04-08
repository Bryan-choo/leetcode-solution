package 面试题;

public class 机器人的运动范围_LCOF_13 {

    public static void main(String[] args) {
        Solution13 solution = new Solution13();

        int ans = solution.movingCount(16,8, 4);
        System.out.println(ans);
    }

}

class Solution13 {

    private void dfs(int[][] visited, int i, int j, int rows, int cols, int k, int[] ans) {

        if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j] == 1)
            return;

        visited[i][j] = 1;

        String var1 = String.valueOf(i);
        String var2 = String.valueOf(j);

        int sum = 0;
        sum += (i/10 + i%10 + j/10 + j%10);

        if (sum <= k)
            ans[0]++;
        //这里如果无法移动，就需要直接返回了，不能跳过这一格
        else
            return;

        dfs(visited, i-1, j, rows, cols, k, ans);
        dfs(visited, i+1, j, rows, cols, k, ans);
        dfs(visited, i, j-1, rows, cols, k, ans);
        dfs(visited, i, j+1, rows, cols, k, ans);
    }

    public int movingCount(int m, int n, int k) {

        if (k == 0)
            return 1;
        int[][] visited = new int[m][n];
        int []ans = new int[1];

        dfs(visited, 0,0, m, n, k, ans);

        return ans[0];
    }
}