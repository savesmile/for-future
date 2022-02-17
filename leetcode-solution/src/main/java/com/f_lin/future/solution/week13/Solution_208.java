package com.f_lin.future.solution.week13;

import java.util.HashSet;
import java.util.Set;

/**
 * 哈哈哈哈 前缀树是什么东西？
 * <p>
 * 执行用时：
 * 297 ms
 * , 在所有 Java 提交中击败了
 * 6.58%
 * 的用户
 * 内存消耗：
 * 46.6 MB
 * , 在所有 Java 提交中击败了
 * 93.27%
 * 的用户
 */
public class Solution_208 {

}

class Trie {

    Set<String> cache = new HashSet<>();

    /**
     * Initialize your data structure here.
     */
    public Trie() {

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        cache.add(word);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return cache.contains(word);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (cache.contains(prefix)) {
            return true;
        }
        for (String key : cache) {
            if (key.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}