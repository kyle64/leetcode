package question;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ziheng on 2020/7/13.
 */
public class Q14 {
    /**
     * @param
     * @return java.lang.String
     * @Description: 14. 最长公共前缀
     * <p>
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 示例 1:
     * <p>
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     * <p>
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * 说明:
     * <p>
     * 所有输入只包含小写字母 a-z 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-common-prefix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @date 2020/7/13 上午12:50
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];

        for (int i = 0; i < strs.length; i++) {
            while (prefix.length() > 0 && !strs[i].startsWith(prefix)) {
                if (!strs[i].startsWith(prefix)) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                }
            }
        }

        return prefix;
    }

    class TrieNode {
        char character;
        Map<Character, TrieNode> children;

        public TrieNode() {
            this.children = new HashMap<>();
        }

        public TrieNode(char character) {
            this.character = character;
            this.children = new HashMap<>();
        }
    }

    class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insertWord(String word) {
            TrieNode currentNode = root;
            for (int i = 0; i < word.length(); i++) {
                if (!currentNode.children.containsKey(word.charAt(i))) {
                    currentNode.children.put(word.charAt(i), new TrieNode(word.charAt(i)));
                }

                currentNode = currentNode.children.get(word.charAt(i));
            }
        }

        public int findNonBranchingDepth(String word) {
            int count = 0;
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                if (current.children.containsKey(word.charAt(i)) && current.children.size() == 1) {
                    current = current.children.get(word.charAt(i));
                    count++;
                } else break;
            }
            return count;
        }
    }

    public String longestCommonPrefixTrie(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        Trie trie = new Trie();
        for (String str : strs) {
            trie.insertWord(str);
        }

        // 需要遍历取最小值，以防[aa,a]这种情况出现
        int count = Integer.MAX_VALUE;
        for (String str : strs) {
            count = Math.min(count, trie.findNonBranchingDepth(str));
        }
        return strs[0].substring(0, count);
    }

    // 官方分治思路
    public String longestCommonPrefixDivision(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }
    }

    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }

    public static void main(String[] args) {
        Q14 q14 = new Q14();

        String[] input = {"flower","flow","flight"};
        System.out.println(q14.longestCommonPrefixTrie(input));
    }
}
