package 并查集_DSU;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.util.*;

public class 被围绕的区域_Surrounded_Regions_130_Medium {


    public static void main(String[] args) {

        char[][] vars = new char[][] {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        new Solution130().solve(vars);
        System.out.println(vars);
    }
}


class Solution130 {

    public static void checkBoard(List<Integer[]> unions, Map<String, Boolean> roots, char[][] board, int[][] visited, int row, int col, int x, int y) {

        if (x < 0 || x >= row || y < 0 || y >= col)
            return;

        if (visited[x][y] == 1)
            return;

        if (board[x][y] == 'X')
            return;


        Integer[] var1 = new Integer[] {x, y};
        unions.add(var1);
        visited[x][y] = 1;

        if (x == 0 || x == row-1 || y == 0 || y == col-1) {

            Integer[] vars1 = unions.get(0);
            int rootx = vars1[0];
            int rooty = vars1[1];

            StringBuilder sb = new StringBuilder();
            sb.append(rootx);
            sb.append('-');
            sb.append(rooty);
            roots.put(sb.toString(), false);

        } else {
            checkBoard(unions, roots, board, visited, row, col, x-1, y);
            checkBoard(unions, roots, board, visited, row, col, x, y+1);
            checkBoard(unions, roots, board, visited, row, col, x+1, y);
            checkBoard(unions, roots, board, visited, row, col, x, y-1);
        }

    }

    public static void changeBoard(char[][] board, List<Integer[]> unions) {

        unions.stream().forEach(r -> {
            int x = r[0];
            int y = r[1];
            board[x][y] = 'X';
        });

    }
    public void solve(char[][] board) {

        int rows = board.length;
        if (rows <= 2)
            return;
        int cols = board[0].length;
        if (cols <= 2)
            return;

        int [][] visited = new int[rows][cols];
        Map<String, List<Integer[]>> allroots = new HashMap<>();
        Map<String, Boolean> rootstatus = new HashMap<>();

        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < cols-1; j++) {
                if (visited[i][j] == 1 || board[i][j] == 'X')
                    continue;
                StringBuilder sb = new StringBuilder();
                sb.append(i).append('-').append(j);

                rootstatus.put(sb.toString(), true);

                List<Integer[]> unions = new ArrayList<>();
                allroots.put(sb.toString(), unions);
                checkBoard(unions,rootstatus,board, visited,rows, cols, i, j);
            }
        }

        rootstatus.entrySet().stream().filter(r->{return r.getValue() == true;}).forEach(r -> {
            String var1 = r.getKey();
//            String[] vars1 = var1.split("-");
            changeBoard(board, allroots.get(var1));

        });


    }
}