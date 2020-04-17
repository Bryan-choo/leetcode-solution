package 数组_Array;

/**
 * 2020 4-17
 */
public class 跳跃游戏_JumpGame_55_Medium {
    public static void main(String[] args) {
        int[] nums = new int[] {3,2,1,1,4};
        Solution55 solution = new Solution55();
        boolean result = solution.canJump(nums);
        System.out.println(result);
    }
}

class Solution55 {

    private boolean checkJump(int[] nums, int end) {
        if (end == 0)
            return true;

        for (int i = end-1; i >= 0; i--) {
            if (nums[i] >= end-i)
                return checkJump(nums, i);
        }
        return false;
    }
    public boolean canJump(int[] nums) {
        int len = nums.length;

        if (len <= 1)
            return true;

        boolean ans = checkJump(nums, len-1);
        return ans;
    }
}