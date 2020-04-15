package 广度优先搜索_BFS;

import java.util.*;
import java.util.stream.Collectors;

public class 离建筑物最近的距离_ShortestDistancefromAllBuildings_317_Hard {
    public static void main(String[] args) {
        int [][]grid = new int[][]{{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};

        Solution317 solution = new Solution317();

        int result = solution.shortestDistance(grid);
        System.out.println(result);
    }
}

class Solution317 {

    private void bfs(int[][] grid, int x, int y, int rows, int cols, Map<Integer, Integer> totalDistance, Map<Integer, Set<Integer>> allPath) {

        LinkedList<Integer[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int indxy = x*1000 + y;
        int ind = 0;

        Set<Integer> path = allPath.getOrDefault(indxy, new HashSet<>());

        if (x-1 >= 0 && grid[x-1][y] ==0) {
            ind = 1000*(x-1)+y;
            if (!visited.contains(ind) && visited.add(ind)) {
                queue.offerLast(new Integer[]{x-1, y, 1});
                int newdistance = totalDistance.getOrDefault(ind, 0) +1;
                totalDistance.put(ind, newdistance);
                path.add(ind);
            }
        }
        if (x+1 < rows && grid[x+1][y] == 0) {
            ind = 1000*(x+1)+y;
            if (!visited.contains(ind) && visited.add(ind)) {
                queue.offerLast(new Integer[]{x+1, y, 1});
                int newdistance = totalDistance.getOrDefault(ind, 0) +1;
                totalDistance.put(ind, newdistance);
                path.add(ind);
            }
        }
        if (y-1 >= 0 && grid[x][y-1] == 0) {
            ind = 1000*x+y-1;
            if (!visited.contains(ind) && visited.add(ind)) {
                queue.offerLast(new Integer[]{x, y-1, 1});
                int newdistance = totalDistance.getOrDefault(ind, 0) +1;
                totalDistance.put(ind, newdistance);
                path.add(ind);
            }
        }
        if (y+1 < cols && grid[x][y+1] == 0) {
            ind = 1000*x+y+1;
            if (!visited.contains(ind) && visited.add(ind)) {
                queue.offerLast(new Integer[]{x, y+1, 1});
                int newdistance = totalDistance.getOrDefault(ind, 0) +1;
                totalDistance.put(ind, newdistance);
                path.add(ind);
            }
        }

        while (queue.size() > 0) {
            Integer[] info = queue.pollFirst();
            int i = info[0];
            int j = info[1];
            int dis = info[2];


            //top
            if (i-1 >= 0 && grid[i-1][j] == 0) {
                ind = 1000*(i-1) + j;
                if (!visited.contains(ind) && visited.add(ind)) {
                    queue.offerLast(new Integer[]{i-1, j, dis+1});
                    int newdistance = totalDistance.getOrDefault(ind, 0)+dis+1;
                    totalDistance.put(ind, newdistance);
                    path.add(ind);
                }
            }
            //buttom
            if (i+1 < rows && grid[i+1][j] ==0) {
                ind = 1000*(i+1) + j;
                if(!visited.contains(ind) && visited.add(ind)) {
                    queue.offerLast(new Integer[]{i+1, j, dis+1});
                    int newdistance = totalDistance.getOrDefault(ind, 0)+dis+1;
                    totalDistance.put(ind, newdistance);
                    path.add(ind);
                }
            }

            //left
            if (j-1 >= 0 && grid[i][j-1] ==0) {
                ind = 1000*i + j-1;
                if(!visited.contains(ind) && visited.add(ind)) {
                    queue.offerLast(new Integer[]{i, j-1, dis+1});
                    int newdistance = totalDistance.getOrDefault(ind, 0)+dis+1;
                    totalDistance.put(ind, newdistance);
                    path.add(ind);
                }
            }

            //right
            if (j+1 < cols && grid[i][j+1] ==0) {
                ind = 1000*i + j+1;
                if(!visited.contains(ind) && visited.add(ind)) {
                    queue.offerLast(new Integer[]{i, j+1, dis+1});
                    int newdistance = totalDistance.getOrDefault(ind, 0)+dis+1;
                    totalDistance.put(ind, newdistance);
                    path.add(ind);
                }
            }
        }

        allPath.put(indxy, path);

    }
    public int shortestDistance(int[][] grid) {
        int rows = grid.length;
        if (rows == 0)
            return -1;
        int cols = grid[0].length;

        Map<Integer, Integer> totalDistance = new HashMap<>();

        Map<Integer, Set<Integer>> allPath = new HashMap<>();

        List<Integer[]> constructions = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1)
                    constructions.add(new Integer[]{i,j});
            }
        }

        for (Integer[] cons:constructions) {
            int x = cons[0];
            int y = cons[1];
            bfs(grid, x, y, rows, cols, totalDistance, allPath);
        }

        Set<Integer> ans = allPath.values().stream().reduce((a, b) -> {
            a.retainAll(b);
            return a;
        }).get();

        if (ans == null || ans.size() == 0)
            return -1;
        List<Map.Entry<Integer, Integer>> result = totalDistance.entrySet().stream().filter(r -> {
            return ans.contains(r.getKey());
        }).sorted((a, b) -> {
            return a.getValue() - b.getValue();
        }).collect(Collectors.toList());
        return result.get(0).getValue();
    }
}
