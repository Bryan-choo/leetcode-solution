package 字符串_String;

import java.util.HashMap;
import java.util.Map;

/**
 * 2020 3-28
 */
public class 实现Trie_ImplementTrie_208_Medium {
}


class Trie {


    Node root;
    private static class Node {
        Map<Character, Node> next = new HashMap<>();
        boolean isWord = false;
    }
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        char[] chars = word.toCharArray();

        for (char c : chars) {
            //插入新词
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new Node());
            }

            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        char[] chars = word.toCharArray();

        for (char c : chars) {
            if (!cur.next.containsKey(c))
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        char[] chars = prefix.toCharArray();

        for (char c : chars) {
            if (!cur.next.containsKey(c))
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }
}