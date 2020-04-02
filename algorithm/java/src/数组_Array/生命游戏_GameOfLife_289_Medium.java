package 数组_Array;


import java.util.Arrays;

/**
 * 2020 4-2
 */
public class 生命游戏_GameOfLife_289_Medium {

    public static void main(String[] args) {
//        int a = 1;
//        Integer b = a | 0b10;
//        System.out.println(Integer.toBinaryString(b>>1));

        int[][] board = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        Solution289 solution = new Solution289();
        solution.gameOfLife(board);

        Arrays.stream(board).forEach(r -> {
            Arrays.stream(r).forEach(System.out::print);
            System.out.println();
        });
    }
}


class Solution289 {

    /**
     * 使用高位存储新的存活状态，低位存储原先存活状态，最终判断时统一右移一位
     * @param board
     * @param rows
     * @param cols
     * @param i
     * @param j
     * @param directionx
     * @param directiony
     */
    private void checkAlive(int[][]board, int rows, int cols, int i, int j, int[] directionx, int[] directiony) {
        int sum = 0;
        for (int di = 0; di < directionx.length; di++) {
                int indx = i + directionx[di];
                int indy = j + directiony[di];
                if (indx < 0 || indy < 0 || indx >= rows || indy >= cols)
                    continue;
                sum += board[indx][indy] & 0b01;
        }

        //当前存活
        if ((board[i][j] & 0b01) == 1) {
            //死亡
            if (sum < 2 || sum > 3)
                board[i][j] &= 0b01;
            else
                board[i][j] |= 0b10;
        } else {
            //复活
            if (sum == 3)
                board[i][j] |= 0b10;
        }

    }

    public void gameOfLife(int[][] board) {
        int[] directionx = new int[]{1,1,0,-1,0,-1,-1,1};
        int[] directiony = new int[]{1,0,1,0,-1,-1,1,-1};

        int rows = board.length;
        if (rows == 0)
            return;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                checkAlive(board, rows, cols, i, j, directionx, directiony);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <cols; j++) {
                board[i][j] >>= 1;
            }
        }
    }
}