# 我们给出两个单词数组
# A
# 和
# B。每个单词都是一串小写字母。
#
# 现在，如果
# b
# 中的每个字母都出现在
# a
# 中，包括重复出现的字母，那么称单词
# b
# 是单词
# a
# 的子集。 例如，“wrr” 是 “warrior” 的子集，但不是 “world” 的子集。
#
# 如果对
# B
# 中的每一个单词
# b，b
# 都是
# a
# 的子集，那么我们称
# A
# 中的单词
# a
# 是通用的。
#
# 你可以按任意顺序以列表形式返回
# A
# 中所有的通用单词。
#
#
#
# 示例
# 1：
#
# 输入：A = ["amazon", "apple", "facebook", "google", "leetcode"], B = ["e", "o"]
# 输出：["facebook", "google", "leetcode"]
# 示例
# 2：
#
# 输入：A = ["amazon", "apple", "facebook", "google", "leetcode"], B = ["l", "e"]
# 输出：["apple", "google", "leetcode"]
# 示例
# 3：
#
# 输入：A = ["amazon", "apple", "facebook", "google", "leetcode"], B = ["e", "oo"]
# 输出：["facebook", "google"]
# 示例
# 4：
#
# 输入：A = ["amazon", "apple", "facebook", "google", "leetcode"], B = ["lo", "eo"]
# 输出：["google", "leetcode"]
# 示例
# 5：
#
# 输入：A = ["amazon", "apple", "facebook", "google", "leetcode"], B = ["ec", "oc", "ceo"]
# 输出：["facebook", "leetcode"]
#
# 提示：
#
# 1 <= A.length, B.length <= 10000
# 1 <= A[i].length, B[i].length <= 10
# A[i]
# 和
# B[i]
# 只由小写字母组成。
# A[i]
# 中所有的单词都是独一无二的，也就是说不存在
# i != j
# 使得
# A[i] == A[j]。

# ==========================================================================================================================
# 为B中所有的单词构造一个hashmap key为每个字母, value为该字母在所有单词中出现的最大次数
#   例如 B = ["ec", "oc", "ceo", "ccoo"]
#   则 hashmap = {'c': 2, 'e': 1, 'o': 2}
#   这样, 遍历A中的每个单词, 只需要判断hashmap中的所有字母是否在A中出现过并且出现次数均要大于等于hashmap中保存的最大次数, 满足
#   即为通用的, 加入结果集中

class Solution:
    def wordSubsets(self, A: 'List[str]', B: 'List[str]') -> 'List[str]':
        characters_map = dict()

        # 去除B中重复出现的单词
        B = set(B)

        #构造hashmap
        for b in B:
            s = set(b)
            for i in s:
                counts = b.count(i)
                if i not in characters_map.keys():
                    characters_map[i] = counts
                else:
                    characters_map[i] = max(characters_map[i], counts)
        # print(characters_map)

        clen = len(characters_map.keys())
        result = []
        keys = list(characters_map.keys())

        #遍历A中所有单词 判断是否满足通用条件
        for a in A:
            flag = True
            ind = 0
            while(flag and ind < clen):
                if a.count(keys[ind]) < characters_map[keys[ind]]:
                    flag = False
                else:
                    ind += 1
            if flag:
                result.append(a)

        return result

s = Solution()
result = s.wordSubsets(["amazon","apple","facebook","google","leetcode"],
["e","oo"])
print(result)