package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ziheng on 2020/8/6.
 */
public class Q336PalindromePairs {
    /**
     * @Description: 336. 回文对
     *
     * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
     *
     * 示例 1:
     *
     * 输入: ["abcd","dcba","lls","s","sssll"]
     * 输出: [[0,1],[1,0],[3,2],[2,4]]
     * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
     * 示例 2:
     *
     * 输入: ["bat","tab","cat"]
     * 输出: [[0,1],[1,0]]
     * 解释: 可拼接成的回文串为 ["battab","tabbat"]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/8/6 上午10:04
     * @param
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public class TrieNode {
        // 顺序trie node
        TrieNode[] children; // 子节点
        int index; // 如果到当前节点正好是一个word（的逆序），保存word在words中的id

        public TrieNode() {
            this.children = new TrieNode[26];
            this.index = -1;
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        // 暴力匹配，时间复杂度O(N^2 * K)，超时
        // 优化，两个字符串匹配
        // 较长的字符串x，如果去掉长度为y的部分，
        // 剩下的部分x(i, xLen)还是回文串且长度x(0, i)的逆序等于y，
        // 那么两个字符串x,y可以拼接为回文串
        // 相应的，如果x(0, i)还是回文串且长度x(i, xLen)的逆序等于y,
        // 那么两个字符串y,x可以拼接为回文串
        List<List<Integer>> result = new ArrayList<>();
        // 构建trie
        TrieNode root = new TrieNode();
        buildTrie(root, words);

        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            for (int j = 0; j <= word.length(); j++) {
                // xLen > yLen, 判断x的后半部分是否回文
                if (isPalindrome(word.substring(j, word.length()))) {
                    // 在字典树中寻找word(0, j-1)的逆序
                    int leftId = search(new StringBuilder(word.substring(0, j)).reverse().toString(), root);
                    if (leftId != -1 && leftId != i) {
                        result.add(Arrays.asList(i, leftId));
                    }
                }
                // xLen < yLen, 判断x的前半部分是否回文
                if (j != 0 && isPalindrome(word.substring(0, j))) {
                    // 在字典树中寻找word(j, len)的逆序
                    int rightId = search(new StringBuilder(word.substring(j, word.length())).reverse().toString(), root);
                    if (rightId != -1 && rightId != i) {
                        result.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }

        return result;
    }

    private void buildTrie(TrieNode root, String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode curr = root;
            for (int j = 0; j < word.length(); j++) {
                int index = word.charAt(j) - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.index = i;
        }
    }

    private int search(String word, TrieNode root) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (curr.children[index] != null) {
                curr = curr.children[index];
            } else {
                return -1;
            }
        }

        return curr.index;
    }

    public class TrieNode1 {
        // 逆序trie node
        TrieNode1[] children; // 子节点
        int index; // 如果到当前节点正好是一个word（的逆序），保存word在words中的id
        // 保存所有到当前节点为止，其之后剩余字符可以构成回文对的单词。用来获取所有长度大于x.length()，且可以和其构成回文对的单词y
        // |--x--|
        // |-----------y-----------|
        // 如果有目标y符合条件，那么一定有y和y'如下：
        // |-------p---------|--x'-|
        // |--x--|--------p--------|
        // 因此在逆序插入i后，如果剩余的部分是回文子串p，那么加入到i节点保存suffix，下次遍历寻找时，看是否有这样的x即可
        List<Integer> suffixes; // 若在该节点之后的部分能构成回文对，则加入suffix列表

        public TrieNode1() {
            this.children = new TrieNode1[26];
            this.index = -1;
            this.suffixes = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairsReverse(String[] words) {
        // 暴力匹配，时间复杂度O(N^2 * K)，超时
        // 优化，两个字符串匹配
        // 较长的字符串x，如果去掉长度为y的部分，
        // 剩下的部分x(i, xLen)还是回文串且长度x(0, i)的逆序等于y，
        // 那么两个字符串x,y可以拼接为回文串
        // 相应的，如果x(0, i)还是回文串且长度x(i, xLen)的逆序等于y,
        // 那么两个字符串y,x可以拼接为回文串
        List<List<Integer>> result = new ArrayList<>();

        // 构建字典树
        TrieNode1 root = new TrieNode1();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode1 curr = root;
            // 逆序word
            String reversed = new StringBuilder(word).reverse().toString();
            // 如果word自身是回文的，那么加入到根节点的suffix中（""空字符串也是回文的）
            if (isPalindrome(reversed.substring(0))) curr.suffixes.add(i);
            for (int j = 0; j < reversed.length(); j++) {
                char ch = reversed.charAt(j);
                if (curr.children[ch - 'a'] == null) {
                    curr.children[ch - 'a'] = new TrieNode1();
                }
                curr = curr.children[ch - 'a'];
                // word(j+1, wLen)回文，把i保存到j+1节点的suffix中
                if (isPalindrome(reversed.substring(j + 1))) {
                    curr.suffixes.add(i);
                }
            }
            // curr节点是word[i]单词逆序的结尾
            curr.index = i;
        }

        // 遍历字典树，查找回文串
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode1 curr = root;
            int j = 0;
            for (; j < word.length(); j++) {
                // |-----------x-----------|
                // |--y---|
                // 如果有目标x符合条件，那么一定有x如下：
                // |--y'--|-------p--------|
                // ex: x = "abc", y = "ba"
                if (isPalindrome(word.substring(j))) {
                    if (curr.index != -1) {
                        // 如果x中某个字符是y'的结尾，那么x+y可以构成回文子串
                        result.add(Arrays.asList(i, curr.index));
                    }

                }
                char ch = word.charAt(j);
                // 检查是否到叶子节点
                // 由于逆序插入，所以如果回文成立，则x的前yLen个数正好等于y的逆序插入y'
                // 即x[i] = y'[i]，否则的话字典树中不存在这种的逆序单词，则退出当前单词的内循环
                if (curr.children[ch - 'a'] == null) {
                    break;
                }
                // 移动到下一节点
                curr = curr.children[ch - 'a'];
            }

            // |--x--|
            // |-----------y-----------|
            // 如果有目标y符合条件，那么一定有y和y'如下：
            // |-------p---------|--x'-|
            // |--x--|--------p--------|
            // 如果x[i] = y'[i]一直成立，但是xLen < yLen，
            // 那么就要看y'(0, yLen - xLen)，也就是y(xLen, yLen)是否是回文
            // 而这个结果如果是回文的话，那么单词y的index就已经被存储在这个回文子串的第一个节点的suffixList中了，
            // 即字典树路径上xLen位置的节点
            // ex: x = "abc", y = "cccba"
            if (j == word.length()) {
                for (int k : curr.suffixes) {
                    if (k != i) {
                        result.add(Arrays.asList(i, k));
                    }
                }
            }
        }

        return result;
    }

    //  判断一个字符串是否是回文字符串
    private boolean isPalindrome(String w) {
        int i = 0, j = w.length()-1;
        while (i < j) {
            if (w.charAt(i) != w.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
}
