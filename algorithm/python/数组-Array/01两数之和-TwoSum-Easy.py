
# 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
#
# 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
#
# 示例:
#
# 给定 nums = [2, 7, 11, 15], target = 9
#
# 因为 nums[0] + nums[1] = 2 + 7 = 9
# 所以返回 [0, 1]


# =========================================================================================
#  方式一：
#       采用双层for 循环依次遍历
#  方式二:
#       查找问题可以使用hashmap提高速度，将数组下标作为key, 值作为value, 在查找时，若map中存在满足条件的
#       数，则可以直接返回下标，若不存在，则将数字存入hashmap中用于下次查找

class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        if len(nums) <=1:
            return

        hashmap = {}
        for index, val in enumerate(nums):
            another_num = target - val
            if another_num in hashmap.keys():
                return [hashmap[another_num], index]
            hashmap[val] = index


s = Solution()
print(s.twoSum([-1,-2,-3,-4,-5], -8))