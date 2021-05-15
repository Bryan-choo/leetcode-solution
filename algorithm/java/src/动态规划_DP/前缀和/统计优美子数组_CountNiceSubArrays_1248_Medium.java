package 动态规划_DP.前缀和;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 统计优美子数组_CountNiceSubArrays_1248_Medium {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,1};
        int k = 2;
        Solution1248 solution = new Solution1248();
        int result = solution.numberOfSubarrays(nums, k);
        System.out.println(result);
    }
}


class Solution1248 {


    public int numberOfSubarrays(int[] nums, int k) {

//        Arrays.stream(nums).boxed().collect(Collectors.toList());
        int len = nums.length;
        if (len == 1)
            return (nums[0] % 2 == 0) ? 0 : 1;

        int presum = 0;

        Map<Integer, Integer> countsMap = new HashMap<>();
        countsMap.put(0, 1);
        int ans = 0;
        for (int i:nums) {
            if ((i & 1) != 0)
                presum++;

            if (presum >= k) {
                ans += countsMap.getOrDefault(presum-k,0);
            }
            countsMap.put(presum, countsMap.getOrDefault(presum,0)+1);

        }

        return ans;

    }
}
