package 数组_Array;

import java.util.*;

public class 合并区间_MergeIntervals_56_Medium {
    public static void main(String[] args) {

        int[][] nums = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        nums = new int[][] {{1,4},{4,5}};
        nums = new int[][] {{1,4},{1,1}};
        nums = new int[][] {{2,3},{4,5},{6,7},{8,9},{1,10}};
        nums = new int[][] {{4,5},{2,4},{4,6},{3,4},{0,0},{1,1},{3,5},{2,2}};
        nums = new int[][] {{0,0},{1,1},{2,2},{1,2}};
        nums = new int[][] {{0,0},{4,5},{5,6},{5,5},{2,3},{5,7},{0,0}};
        Solution56 solution = new Solution56();

        int[][] result = solution.merge(nums);
        Arrays.stream(result).forEach(r -> {
            System.out.print("[");
            Arrays.stream(r).forEach(r1 -> {System.out.print(r1+" ");});
            System.out.print("]");
        });
    }
}

class Solution56 {

    private void doMerge(LinkedList<Integer> intervallist, int startind, int endind) {
        if (endind - startind > 1) {
            Iterator<Integer> iterator = intervallist.iterator();
            int i = 0;
            while (i <= startind && iterator.hasNext()) {
                iterator.next();
                i++;
            }
            while (i < endind && iterator.hasNext()) {
                iterator.next();
                iterator.remove();
                i++;
            }
        }
    }

    private void findAndMerge(LinkedList<Integer> intervallist, int low, int high) {
        if (intervallist.size() == 0) {
            intervallist.add(low);
            intervallist.add(high);
        } else {
            int i = 0;
            int j = intervallist.size()-1;

            while (i < intervallist.size() && intervallist.get(i) < low && intervallist.get(i+1) < low) i+=2;
            //直接插入
            if (i >= intervallist.size()) {
                intervallist.add(low);
                intervallist.add(high);
                return;
            }
            while (j >= 0 && intervallist.get(j) > high && intervallist.get(j-1) > high) j-=2;

            //直接插入
            if (j < 0) {
                intervallist.add(0, low);
                intervallist.add(1, high);
                return;
            }

            if (i > j) {
                intervallist.add(i, low);
                intervallist.add(i+1, high);
                return;
            }
            if(intervallist.get(i) > low) {
                intervallist.set(i, low);
            }
            if (intervallist.get(j) < high) {
                intervallist.set(j, high);
            }
            doMerge(intervallist, i, j);
        }
    }
    public int[][] merge(int[][] intervals) {
        int rows = intervals.length;
        if (rows == 0)
            return intervals;

        int cols = intervals[0].length;

        LinkedList<Integer> intervallist = new LinkedList<>();

        for (int[] interval:intervals) {

            int low = interval[0];
            int high = interval[1];
            findAndMerge(intervallist, low, high);
        }
        rows = intervallist.size() / 2;
        int[][] ans = new int[rows][2];

        for (int i = 0; i < rows; i++) {
            ans[i][0] = intervallist.get(i*2);
            ans[i][1] = intervallist.get(i*2+1);
        }
        return ans;
    }
}