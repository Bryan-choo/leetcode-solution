package 数组_Array;

import java.util.HashMap;

import javax.sound.midi.Soundbank;

/**
 * 
 * @author chuchengwei
 *
 */

/*
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

 * 示例:

 * 给定 nums = [2, 7, 11, 15], target = 9

 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 */

/*
 *   方式一：
 *      采用双层for 循环依次遍历
 *   方式二:
 *      查找问题可以使用hashmap提高速度，将数组下标作为key, 值作为value, 在查找时，若map中存在满足条件的
 *      数，则可以直接返回下标，若不存在，则将数字存入hashmap中用于下次查找
 */

public class 两数之和_TwoSum__01_Easy {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		int [] result = s.twoSum(new int[] {3, 3}, 6);
		for(int i:result)
			System.out.println(i);
	}

}

class Solution {
	public Solution() {};
    public int[] twoSum(int[] nums, int target) {
    	int len = nums.length;
    	if(len <= 1)
    		return null;
    	HashMap<Integer, Integer> hashmap = new HashMap<>();
    	for(int i=0; i<len; i++) {
    		int another_num = target - nums[i];
    		if(hashmap.containsKey(another_num)) {
    			return new int[] {hashmap.get(another_num), i};
    		}
    		hashmap.put(nums[i], i);
 
    	}
    	return null;
    }
}
