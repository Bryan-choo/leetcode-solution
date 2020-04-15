package 深度优先搜索_DFS;

import java.util.Arrays;
import java.util.LinkedList;

public class 零一矩阵_01Matrix_542_Miedum {
    public static void main(String[] args) {
        Solution542 solution = new Solution542();
        int[][] matrix = new int[][] {{1,1,1},{1,0,1},{1,1,1}};
        int[][] ans = solution.updateMatrix(matrix);
        Arrays.stream(ans).forEach(r -> {
            System.out.println();
            Arrays.stream(r).forEach(r1 -> System.out.print(r1+" "));
        });
    }
}

class Solution542 {

    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0)
            return matrix;
        int cols = matrix[0].length;

        int [][] visited = new int[rows][cols];

        LinkedList<Integer[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <cols; j++) {
                if (matrix[i][j] == 0) {
                    queue.offerLast(new Integer[]{i, j, 1});
                    visited[i][j] = 1;
                }
            }
        }

        while (queue.size() > 0) {
            Integer[] ind = queue.pollFirst();
            int x = ind[0];
            int y = ind[1];
            int dis = ind[2];

            if (x -1 >= 0 && visited[x-1][y] == 0) {
                visited[x-1][y] = dis + 1;
                queue.offerLast(new Integer[]{x-1, y, dis+1});
            }
            if (x+1 < rows && visited[x+1][y] == 0) {
                visited[x+1][y] = dis + 1;
                queue.offerLast(new Integer[]{x+1, y, dis+1});
            }
            if (y-1 >=0 && visited[x][y-1] == 0) {
                visited[x][y-1] = dis +1;
                queue.offerLast(new Integer[] {x, y-1, dis+1});
            }
            if (y+1 <cols && visited[x][y+1] == 0) {
                visited[x][y+1] = dis + 1;
                queue.offerLast(new Integer[] {x, y+1, dis+1});
            }

        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <cols; j++) {
                visited[i][j] -= 1;
            }
        }
        return visited;
    }
}