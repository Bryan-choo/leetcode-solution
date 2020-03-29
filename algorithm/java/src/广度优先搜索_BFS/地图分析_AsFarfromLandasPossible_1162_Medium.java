package 广度优先搜索_BFS;

import java.util.LinkedList;

public class 地图分析_AsFarfromLandasPossible_1162_Medium {

    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,0,1},{0,0,0},{1,0,1}};
//        grid = new int[][]{{1,0,0},{0,0,0},{0,0,0}};
        grid = new int[][]{{1}};
        Solution1162 solution = new Solution1162();
        int ans = solution.maxDistance(grid);
        System.out.println(ans);
    }

}

class Solution1162 {

    public int maxDistance(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] distance = new int[rows][cols];

        LinkedList<Integer[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    Integer[] var = new Integer[]{i,j};
                    distance[i][j] = 1;
                    queue.offerLast(var);
                }
            }
        }

        int maxlen = 0;
        if (queue.size() > 0 && queue.size() == cols * rows)
            return -1;
        else if (queue.size() == 0)
            return -1;
        else
            maxlen = Integer.MIN_VALUE;
        while (queue.size() > 0) {
            Integer[] head = queue.pollFirst();
            int i = head[0];
            int j = head[1];
            int dis = distance[i][j]+1;
            maxlen = (maxlen < dis) ? dis : maxlen;
            if (j > 0 && distance[i][j-1] == 0) {
                distance[i][j-1] = dis;
                Integer[] left = new Integer[]{i, j-1};
                queue.offerLast(left);
            }
            if (j < cols-1 && distance[i][j+1] == 0) {
                distance[i][j+1] = dis;
                Integer[] right = new Integer[] {i, j+1};
                queue.offerLast(right);
            }
            if (i < rows-1 && distance[i+1][j] == 0) {
                distance[i+1][j] = dis;
                Integer[] buttom = new Integer[]{i+1, j};
                queue.offerLast(buttom);
            }
            if (i > 0 && distance[i-1][j] == 0) {
                distance[i-1][j] = dis;
                Integer[] top = new Integer[] {i-1, j};
                queue.offerLast(top);
            }

        }

        return maxlen-2;
    }
}