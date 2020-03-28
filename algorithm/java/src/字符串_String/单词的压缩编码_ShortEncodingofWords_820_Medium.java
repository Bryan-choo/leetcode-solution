package 字符串_String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 2020-3-28
 */
public class 单词的压缩编码_ShortEncodingofWords_820_Medium {

    public static void main(String[] args) {
        String[] words = new String[]{"time", "me", "longtime", "bell"};
        Solution820 solution = new Solution820();
        int result = solution.minimumLengthEncoding(words);
        System.out.println(result);
    }


}

/**
 * 定义字典树
 */
class TrieNode {
//    char var;
//    TrieNode []children = new TrieNode[26];
    HashMap<Character, TrieNode> next = new HashMap<>();
}

class Solution820 {


    private int insert(TrieNode root, String word) {
        TrieNode cur = root;

        char[] chars = word.toCharArray();

        boolean isNewChar = false;

        for (int i = chars.length-1; i >= 0; i--) {

            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new TrieNode());
                isNewChar = true;
            }
            cur = cur.next.get(c);
//            int index = c - 'a';
//            //插入新词
//            if (cur.children[index] == null) {
//                cur.children[index] = new TrieNode();
//                isNewChar = true;
//            }
//            cur = cur.children[index];
        }
        return isNewChar ? word.length() + 1 : 0;
    }

    private boolean canReplace(String longstr, String shortstr) {
        int llen = longstr.length();
        int slen = shortstr.length();
        return longstr.substring(llen-slen, llen).equals(shortstr);
    }
    public int minimumLengthEncoding(String[] words) {

        int len = words.length;
        if (len == 1)
            return words[0].length()+1;

        //按长度逆序排序
        Arrays.sort(words, (r1, r2)-> {return r2.length() - r1.length();});

        TrieNode root = new TrieNode();

        int ans = 0;
        for (String word : words) {
            ans += insert(root, word);
        }
        return ans;
//        HashSet<String> set = new HashSet<>();
//
//        set.add(words[0]);
//
//        for (int i = 0; i < len; i++) {
//
//            String word = words[i];
//
//            if (set.contains(word))
//                continue;
//            boolean contains = false;
//            Iterator<String> iterator = set.iterator();
//            while (iterator.hasNext() && !contains) {
//                String nextval = iterator.next();
//                if (nextval.length() >= word.length() && canReplace(nextval, word))  {
//                    contains = true;
//                } else if (word.length() >= nextval.length() && canReplace(word, nextval)) {
//                    iterator.remove();
//                    set.add(word);
//                    contains = true;
//                }
//            }
//            if (!contains)
//                set.add(word);
//        }
//
//        int ans[] = new int[1];
//
//        set.stream().forEach(r -> {
//            int slen = r.length();
//            ans[0] += slen+1;
//        });
//
//        return ans[0];
    }
}
