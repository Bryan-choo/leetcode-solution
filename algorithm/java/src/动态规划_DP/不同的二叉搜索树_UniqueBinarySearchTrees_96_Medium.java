package 动态规划_DP;

/*
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

示例:

输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
============================================================================
  动态规划求解思路:
  对于给定1...n的序列，可组成二叉搜索树的个数看做该时刻的状态dp_n
  对于序列 1，2，3，4而言，可将序列分为1，2，3和4
      1. 将1，2，3看成一个整体，则4可插入到1,2，3左右，则存在2*dp(1,2,3)种可能
      2. 将4分别插入到1,2,3中间,则有dp(1)*dp(2,3)+dp(1,2)*dp(3)种可能
      3. 最终将步骤1，步骤2可能相加即为当前序列可组成的所有二叉排序树可能数
  递推式: dp(i) = 2*dp(i-1)+sum(dp(j)*dp(i-j))  j from 1 to i-1
  边界: dp(1) = 1, dp(2) = 2
 */

/**
 * 
 * @author chuchengwei
 *
 */
public class 不同的二叉搜索树_UniqueBinarySearchTrees_96_Medium {
	public static void main(String[] args) {
		Solution96 s = new Solution96();
		System.out.println(s.numTrees(18));
	}
}

class Solution96 {
    public int numTrees(int n) {
    	if (n == 0)
    		return 0;
    	if (n == 1)
    		return 1;
    	if (n == 2)
    		return 2;
    	int []dp = new int[n];
    	dp[0] = 1;
    	dp[1] = 2;
    	for (int i = 2; i < n; i++) {
    		int val = 0;
    		for(int j = 1; j < i; j++) {
    			val += dp[j-1]*dp[i-j-1];
    		}
    		dp[i] = 2*dp[i-1] + val;
    	}
        return dp[n-1];
    }
}
