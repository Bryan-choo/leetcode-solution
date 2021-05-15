package 并查集_DSU;

import java.util.*;

public class 岛屿的个数_NumberofIslands_200_Medium {
	
	public static void main(String[] args) {
		Solution200 s = new Solution200();
		char [][]grid = new char[][] {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
		s.numIslands(grid);
	}

}


class Solution200 {
	
	public int bfs(char [][]grid) {
		Set<Integer> set = new LinkedHashSet<>();
		List list =new ArrayList(set);

		int [][] stats = new int [grid.length][grid[0].length];
    	for (int i = 0; i < stats.length; i++) {
    		for (int j = 0; j < stats[i].length; j++) {
    			stats[i][j] = 0;
    		}
    	};
		int setnum = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1' && stats[i][j] == 0) {
					setnum ++;
					LinkedList<Integer[]> queue = new LinkedList<>();
					queue.offerLast(new Integer [] {i, j});
					while (queue.size() != 0) {
						Integer[] val = queue.pollFirst();
						int m = val[0];
						int n = val[1];
						stats[m][n] = 1;
						if (m - 1 >= 0 && grid[m-1][n] == '1' && stats[m-1][n] == 0) {
							queue.offerLast(new Integer[]{m-1, n});
							stats[m-1][n] = 1;
						}
						if (n - 1 >= 0 && grid[m][n-1] == '1' && stats[m][n-1] == 0) {
							queue.offerLast(new Integer[] {m, n-1});
							stats[m][n-1] = 1;
						}
						if (m + 1 < grid.length && grid[m+1][n] == '1' && stats[m+1][n] == 0) {
							queue.offerLast(new Integer[] {m+1, n});
							stats[m+1][n] = 1;
						}
						if (n + 1 < grid[0].length && grid[m][n+1] == '1' && stats[m][n+1] == 0) {
							queue.offerLast(new Integer[] {m, n+1});
							stats[m][n+1] = 1;
						}
					}
				}
			}
		}
		return setnum;
	}
    public int numIslands(char[][] grid) {
    	int result = bfs(grid);
    	System.out.println(result);
        return result;
    }
}
