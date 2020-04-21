package 面试题;

public class 二维数组中的查找_04 {

    public static void main(String[] args) {

        int[][] matrix = new int[][] {{1,3,5,7,9},{2,4,6,8,10},{11,13,15,17,19},{12,14,16,18,20},{21,22,23,24,25}};
        Solution04 solution = new Solution04();
        boolean result = solution.findNumberIn2DArray(matrix, 13);
        System.out.println(result);
    }
}


class Solution04{

    private int getColInd(int[][] matrix, int target, int cols) {

        int start = 0;
        int end = cols-1;

        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (matrix[0][mid] > target) {
                end = mid-1;
            } else if (matrix[0][mid] < target) {
                start = mid+1;
            } else {
                return mid;
            }
        }
        return end < 0 ? 0 : end;
    }

    private int getRowInd(int[][] matrix, int target, int rows) {
        int start = 0;
        int end = rows-1;

        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (matrix[mid][0] > target) {
                end = mid-1;
            } else if(matrix[mid][0] < target) {
                start = mid+1;
            } else {
                return mid;
            }
        }
        return end < 0 ? 0 :end;
    }
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0)
            return false;
        int cols = matrix[0].length;
        if (cols == 0)
            return false;

        int col = getColInd(matrix, target, cols);
        int row = getRowInd(matrix, target, rows);

        for(int i = rows-1; i >= 0; i--) {
            if (matrix[i][col] == target)
                return true;
        }
        for(int j = cols-1; j >= 0; j--) {
            if (matrix[row][j] == target)
                return true;
        }
        return false;

    }
}