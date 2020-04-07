package 面试题;

import java.util.Arrays;
import java.util.Objects;

public class 矩阵旋转_RotateMatrix_0107_Medium {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        Solution0107 solution = new Solution0107();
        solution.rotate(matrix);
        Arrays.stream(matrix).forEach(r -> {
            System.out.println();
            Arrays.stream(r).forEach(a -> System.out.print(" "+ a));
        });
    }
}

class Solution0107 {

    //矩阵转置
    private void transpose(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0)
            return;
        int cols = matrix[0].length;

        for(int i = 0; i < rows; i++) {
            for (int j = i; j < cols; j++) {
//                matrix[i][j] ^= matrix[j][i];
//                matrix[j][i] ^= matrix[i][j];
//                matrix[i][j] ^= matrix[j][i];
                int k = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = k;
            }
        }
    }

    //交换
    private void reverse(int[][] matrix) {

        int rows = matrix.length;
        if (rows == 0)
            return;
        int cols = matrix[0].length;
        int j = 0;
        while (j < cols-j-1) {
            for (int i = 0; i < rows; i++) {
                matrix[i][j] ^= matrix[i][cols-j-1];
                matrix[i][cols-j-1] ^= matrix[i][j];
                matrix[i][j] ^= matrix[i][cols-j-1];
            }
            j++;
        }
    }

    public void rotate(int[][] matrix) {

        int rows = matrix.length;
        if (rows <= 1)
            return;
        transpose(matrix);
        reverse(matrix);
    }
}
