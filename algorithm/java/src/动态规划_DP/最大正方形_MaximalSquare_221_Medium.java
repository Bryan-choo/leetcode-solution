package 动态规划_DP;

import java.util.HashMap;
import java.util.Map;

public class 最大正方形_MaximalSquare_221_Medium {

    public static void main(String[] args) {
        char[][] vals = new char[][]{{'1','1','1','0','0'}, {'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','1','1','1'}};
        vals = new char[][]{{'0','0','0','0','0'},{'1','0','0','0','0'},{'0','0','0','0','0'},{'0','0','0','0','0'}};
//        vals = new char[][] {{'0','0','0','1'},{'1','1','0','1'},{'1','1','1','1'},{'0','1','1','1'},{'0','1','1','1'}};
//        vals = new char[][] {
//                {'1','0','1','1','0','1'},
//                {'1','1','1','1','1','1'},
//                {'0','1','1','0','1','1'},
//                {'1','1','1','0','1','0'},
//                {'0','1','1','1','1','1'},
//                {'1','1','0','1','1','1'}
//        };
        Solution221 solution = new Solution221();
        int result = solution.maximalSquare(vals);
        System.out.println(result);
    }
}


class Solution221 {



    private boolean checkSquare(char[][] matrix, int i, int j, int len) {

        for (int pi = i-len+1; pi <= i; pi++) {
            for (int pj = j-len+1; pj <= j; pj++) {
                if (matrix[pi][pj] == '0')
                    return false;
            }
        }

        return true;
//        int d = 0;
//        while (d < len) {
//            if (matrix[i][j-d] == '0' || matrix[i-d][j] == '0')
//                return false;
//            d++;
//        }
//        return true;
    }


    private int getSquare(char[][] matrix, int i, int j) {

        int d = 0;

        while (i-d >=0 && j-d >=0 && matrix[i-d][j-d] == '1' && checkSquare(matrix, i, j, d+1))
            d++;

        return d;

    }

    private int iterate(char[][] matrix, int rows, int cols, int i, int j, Map<Integer, Integer> squares, Map<Integer,Integer> tmpMap) {


        if (squares.size() == 0) {
            //添加所有1
            for (int item = 0; item <= j; item++) {
                if (matrix[i][item] == '1')
                    squares.put(i*10000+item, 1);
            }

            for (int item = 0;  item < i; item++) {
                if (matrix[item][j] == '1')
                    squares.put(item*10000+j, 1);
            }

            return squares.size() == 0 ? 0 : 1;

        } else {

            squares.entrySet().stream().forEach(r->{
                int ind = r.getKey();
                int value = r.getValue();

                int x = ind / 10000;
                int y = ind % 10000;

                if (x+1 < rows && y+ 1 < cols && matrix[x+1][y+1] == '1' && checkSquare(matrix, x+1, y+1, value+1)) {
                    tmpMap.put((x+1)*10000+y+1, value+1);
                }
            });

            if (tmpMap.size() == 0) {
                //添加所有1
                for (int item = 0; item <= j; item++) {
                    if (matrix[i][item] == '1')
                        tmpMap.put(i*10000+item, getSquare(matrix, i, item));
                }

                for (int item = 0;  item < i; item++) {
                    if (matrix[item][j] == '1')
                        tmpMap.put(item*10000+j, getSquare(matrix, item, j));
                }
            }

            int val = squares.values().stream().max((a,b)->a-b).get();
            int val2 = tmpMap.size() == 0 ? 0 : tmpMap.values().stream().max((a,b)->a-b).get();

            squares.clear();
//                squares.clearAll();
            squares.putAll(tmpMap);
//                squares.addAll(tmpMap);
            tmpMap.clear();
//                tmpMap.clearAll();

            return Math.max(val, val2);
        }

    }

    private int getArea(char[][] matrix, int rows, int cols, Map<Integer, Integer> squares) {

        int i = 0;
        int j = 0;

        Map<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();
        int ans = Integer.MIN_VALUE;

        while (i < rows && j < cols) {

            int iterans = iterate(matrix, rows, cols,i, j, squares, tmpMap);
            ans = (iterans > ans) ? iterans :ans;
            i++;
            j++;
        }

        while (i < rows) {
            int iterans = iterate(matrix, rows, cols,i, j-1, squares, tmpMap);
            ans = (iterans > ans) ? iterans :ans;
            i++;
        }
        while (j < cols) {
            int iterans = iterate(matrix, rows, cols, i - 1, j, squares, tmpMap);
            ans = (iterans > ans) ? iterans : ans;
            j++;
        }

        return ans*ans;

    }

    public int maximalSquare(char[][] matrix) {

        int rows = matrix.length;
        if (rows == 0)
            return 0;
        int cols = matrix[0].length;

        return getArea(matrix, rows, cols, new HashMap<Integer, Integer>());

    }
}
