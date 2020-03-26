package 数组_Array;


/**
 * 2020 3-26
 */
public class 车的可用捕获量_AvailableCapturesforRook_999_Easy {
}

class Solution999 {

    public static int getNums(char[][] board, int i, int j, int rows, int cols) {
        int nums = 0;

        for (int left = i-1; left >= 0; left--) {
            if (board[left][j] == 'B')
                break;
            if (board[left][j] == 'p') {
                nums ++;
                break;
            }
        }

        for (int right = i+1; right < cols; right++) {
            if (board[right][j] == 'B')
                break;
            if (board[right][j] == 'p'){
                nums ++;
                break;
            }
        }

        for (int top = j-1; top >= 0; top--) {
            if (board[i][top] == 'B')
                break;
            if (board[i][top] == 'p'){
                nums ++;
                break;
            }
        }

        for (int buttom = j+1; buttom < rows; buttom++) {
            if (board[i][buttom] == 'B')
                break;
            if (board[i][buttom] == 'p'){
                nums ++;
                break;
            }
        }
        return nums;
    }

    public int numRookCaptures(char[][] board) {

        int rows = board.length;
        int cols = board.length;

        if (rows <= 1)
            return 0;

        //定位

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <cols; j++) {

                if (board[i][j] == 'R') {
                    return getNums(board, i, j, rows, cols);
                }

            }
        }
        return 0;

    }
}