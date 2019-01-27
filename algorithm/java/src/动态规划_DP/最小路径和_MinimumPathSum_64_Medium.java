package 动态规划_DP;


/*
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
 */

/*
# ============================================================================
# 动态规划求解:
#       递推式: d[i,j] = grid[i,j] + min{d[i-1,j], d[i, j-1]}
#       边界:   d[0,0] = grid[0,0]
# 有了递推式和边界，就可以逐步构造dp矩阵，最终返回矩阵最后一个值
*/

/**
 * 
 * @author chuchengwei
 *
 */
public class 最小路径和_MinimumPathSum_64_Medium {
		public static void main(String[] args) {
			Solution s = new Solution();
			int result = s.minPathSum(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
			System.out.println(result);
		}
}


class Solution {
    public int minPathSum(int[][] grid) {
    	if(grid.length == 0)
    		return 0;
    	int [][]dp = new int[grid.length][grid[0].length];
    	dp[0][0] = grid[0][0];
    	
    	for(int i=0; i<grid.length; i++) {
    		for(int j=0; j<grid[0].length; j++) {
    			if(i == 0&&j == 0)
    				continue;
    			if(i == 0) {
    				dp[i][j] = grid[i][j] + dp[i][j-1];
    			} else {
    				if(j == 0) {
    					dp[i][j] = grid[i][j] + dp[i-1][j];
    				} else {
    					dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
    				}
    			}
    		}
    	}
    	
        return dp[grid.length-1][grid[0].length-1];
    }
}