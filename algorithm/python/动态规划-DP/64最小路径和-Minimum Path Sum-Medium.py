# 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
#
# 说明：每次只能向下或者向右移动一步。
#
# 示例:
#
# 输入:
# [
#   [1,3,1],
#   [1,5,1],
#   [4,2,1]
# ]
# 输出: 7
# 解释: 因为路径 1→3→1→1→1 的总和最小。

# ============================================================================
# 动态规划求解:
#       递推式: d[i,j] = grid[i,j] + min{d[i-1,j], d[i, j-1]}
#       边界:   d[0,0] = grid[0,0]
# 有了递推式和边界，就可以逐步构造dp矩阵，最终返回矩阵最后一个值

class Solution:
    def minPathSum(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        if(len(grid) == 0):
            return 0
        d_matrix = [[0 for j in range(len(i))] for i in grid]
        d_matrix[0][0] = grid[0][0]
        # print(d_matrix)
        for i in range(len(d_matrix)):
            for j in range(len(d_matrix[i])):
                if i == 0 and j == 0:
                    continue;
                if i == 0:
                    d_matrix[i][j] = grid[i][j] + d_matrix[i][j-1]
                elif j == 0:
                    d_matrix[i][j] = grid[i][j] + d_matrix[i-1][j]
                else:
                    d_matrix[i][j] = grid[i][j] + min(d_matrix[i-1][j], d_matrix[i][j-1])
        return d_matrix[-1][-1]

s = Solution()
result = s.minPathSum([[1,2],[5,6],[1,1]])
print(result)