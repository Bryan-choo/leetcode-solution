package 字符串_String;
/*
 * 我们给出两个单词数组
A
和
B。每个单词都是一串小写字母。

现在，如果
b
中的每个字母都出现在
a
中，包括重复出现的字母，那么称单词
b
是单词
a
的子集。 例如，“wrr” 是 “warrior” 的子集，但不是 “world” 的子集。

如果对
B
中的每一个单词
b，b
都是
a
的子集，那么我们称
A
中的单词
a
是通用的。

你可以按任意顺序以列表形式返回
A
中所有的通用单词。



示例
1：

输入：A = ["amazon", "apple", "facebook", "google", "leetcode"], B = ["e", "o"]
输出：["facebook", "google", "leetcode"]
示例
2：

输入：A = ["amazon", "apple", "facebook", "google", "leetcode"], B = ["l", "e"]
输出：["apple", "google", "leetcode"]
示例
3：

输入：A = ["amazon", "apple", "facebook", "google", "leetcode"], B = ["e", "oo"]
输出：["facebook", "google"]
示例
4：

输入：A = ["amazon", "apple", "facebook", "google", "leetcode"], B = ["lo", "eo"]
输出：["google", "leetcode"]
示例
5：

输入：A = ["amazon", "apple", "facebook", "google", "leetcode"], B = ["ec", "oc", "ceo"]
输出：["facebook", "leetcode"]

提示：

1 <= A.length, B.length <= 10000
1 <= A[i].length, B[i].length <= 10
A[i]
和
B[i]
只由小写字母组成。
A[i]
中所有的单词都是独一无二的，也就是说不存在
i != j
使得
A[i] == A[j]。

==========================================================================================================================
为B中所有的单词构造一个hashmap key为每个字母, value为该字母在所有单词中出现的最大次数
  例如 B = ["ec", "oc", "ceo", "ccoo"]
  则 hashmap = {'c': 2, 'e': 1, 'o': 2}
  这样, 遍历A中的每个单词, 只需要判断hashmap中的所有字母是否在A中出现过并且出现次数均要大于等于hashmap中保存的最大次数, 满足
  即为通用的, 加入结果集中
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author chuchengwei
 *
 */
public class 单词子集_WordSubsets_916_Medium {
	public static void main(String[] args) {
		Solution916 s = new Solution916();
		String[] A = new String[] {"amazon","apple","facebook","google","leetcode"};
		String[] B = new String[] {"e","oo"};
		List<String> wordSubsets = s.wordSubsets(A, B);
		for (String i : wordSubsets)
			System.out.println(i);
	}
}

class Solution916 {
	private int getCharCounts(String str, char c) {
		if (str.indexOf(c) == -1) 
			return 0;
		char[] charArray = str.toCharArray();
		int count = 0;
		for (char i : charArray) {
			if (i == c)
				count++;
		}
		return count;
	}
    public List<String> wordSubsets(String[] A, String[] B) {
    	HashMap<Character, Integer> character_map = new HashMap<Character, Integer>();
    	
    	HashSet<String> BSet = new HashSet();
    	for (String b : B)
    		BSet.add(b);
    	
//    	构造HashMap
    	for (String b : BSet) {
    		char[] charArray = b.toCharArray();
    		HashMap<Character, Integer> map = new HashMap<>();
    		for (char i : charArray) {
    			if (!map.containsKey(i))
    				map.put(i, 1);
    			else
    				map.put(i, map.get(i) + 1);
    		}
    		Set<Entry<Character,Integer>> entrySet = map.entrySet();
    		for (Entry<Character, Integer> entry : entrySet) {
    			char key = entry.getKey();
    			int counts = entry.getValue();
    			character_map.put(key, Math.max(character_map.getOrDefault(key, 0), counts));
    		}
    	}
    	
    	System.out.println(character_map.toString());
    	List<String> result = new ArrayList();
    	
    	for (String a : A) {
    		Set<Entry<Character,Integer>> entrySet = character_map.entrySet();
    		boolean flag = true;
    		for (Entry<Character, Integer> entry : entrySet) {
    			char key = entry.getKey();
    			int count = entry.getValue();
    			if (this.getCharCounts(a, key) < count) {
    				flag = false;
    				break;
    			}
    		}
    		if (flag) 
    			result.add(a);
    	}
        return result;
    }
}
