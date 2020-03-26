package 回溯_BackTracking;

import java.util.*;
import java.util.stream.Collectors;

public class 全排列_PermutationsII_47_Medium {

    public static void main(String[] args) {
        int[] vars = {1,1,2};

        new Solution47().permuteUnique(vars);
    }


}

class Solution47 {

    public static void permutation(List<Integer> leftIndex, StringBuilder permutation, Map<String, String> ans, int[] nums) {

        if (leftIndex.size() == 0) {
            ans.put(permutation.toString(), permutation.toString());
        }
        else {
            LinkedList<Integer> list = (LinkedList<Integer>) leftIndex;
            for (int i = 0; i < list.size(); i++) {

                int var1 = list.get(i);
                String var2 = nums[var1] + ":";
                int startind = (permutation.length() == 0) ? 0 : permutation.length()-1;
                int endind = startind + var2.length();
                permutation.append(var2);
                list.remove(i);
                permutation(leftIndex, permutation, ans, nums);
                permutation.delete(startind, endind);
                list.add(i, var1);
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {

        if (nums.length == 0)
            return Collections.emptyList();

        Map<String, String> result = new HashMap<>();
        List<Integer> leftIndex = new LinkedList<>();
        StringBuilder permutation = new StringBuilder();

        for (int i = 0; i < nums.length; i++)
            leftIndex.add(i);

        permutation(leftIndex, permutation, result, nums);

        List<List<Integer>> ans = new ArrayList<>();

        result.values().stream().forEach(r -> {
            String[] vars1 = r.split(":");
            List<Integer> var1 = Arrays.stream(vars1).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
            ans.add(var1);
        });


//        System.out.println(ans.size());

//        return ans.values().stream().collect(Collectors.toList());
        return ans;
    }
}