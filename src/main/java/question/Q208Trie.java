/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

/**
 * @author wuziheng
 * @description
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 *
 * 示例：
 *
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 *
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 *
 * 提示：
 *
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 *
 * @date 2025/5/1 22:41
 **/
public class Q208Trie {

    class Trie {
        Trie[] children;
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            return searchPrefix(word, true);
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix, false);
        }

        private boolean searchPrefix(String prefix, boolean checkEnd) {
            Trie node = this;
            for (char ch : prefix.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return false;
                }
                node = node.children[index];
            }

            return checkEnd ? node.isEnd : true;
        }
    }

    class Trie1 {
        class TrieNode {
            boolean isEnd;
            TrieNode[] children;

            TrieNode() {
                this.children = new TrieNode[26];
                isEnd = false;
            }
        }

        TrieNode root;

        public Trie1() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    current.children[c - 'a'] = new TrieNode();
                }
                current = current.children[c - 'a'];
            }
            current.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                TrieNode child = current.children[c - 'a'];
                if (child == null) {
                    return false;
                }
                current = child;
            }
            return current.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode current = root;
            for (char c : prefix.toCharArray()) {
                TrieNode child = current.children[c - 'a'];
                if (child == null) {
                    return false;
                }
                current = child;
            }
            return true;
        }
    }
}
