/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

/**
 * @author wuziheng
 * @description 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 *
 * 提示：
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 *
 * @date 2025/5/16 23:38
 **/
public class Q1143LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j]: 表示以s[i]和t[j]为结尾的两个子串的最长公共子序列的长度
        // 由于可以不取，因此并非必须包含s[i]和t[j]
        // dp[i][j] = dp[i - 1][j - 1] + 1 if s[i] = t[j]
        // 如果s[i] != t[j]，则要么取s[i]不取t[j]，要么不取s[i]取t[j]
        // dp[i][j] = max(dp[i-1][j], dp[i][j-1]) if s[i] != t[j]
        char[] s = text1.toCharArray();
        char[] t = text2.toCharArray();
        int m = s.length, n = t.length;
        int[][] dp = new int[m+1][n+1];
        // 整体往右下平移一格，以便更好的初始化
        // dp[i+1][j+1] = dp[i][j] + 1 if s[i] = t[j] else max(dp[i][j+1], dp[i+1][j])
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s[i] == t[j]) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }
        return dp[m][n];
    }

    public int longestCommonSubsequenceDP1(String text1, String text2) {
        // dp[i][j]: 表示以s[i]和t[j]为结尾的两个子串的最长公共子序列的长度
        // dp[i][j] = dp[i - 1][j - 1] + 1 if s[i] = t[j]
        // 如果s[i] != t[j]，则要么取s[i]不取t[j]，要么不取s[i]取t[j]
        // dp[i][j] = max(dp[i-1][j], dp[i][j-1]) if s[i] != t[j]
        char[] s = text1.toCharArray();
        char[] t = text2.toCharArray();
        int m = s.length, n = t.length;
        int[][] dp = new int[m][n];
        dp[0][0] = s[0] == t[0] ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[i][0] = s[i] == t[0] ? 1 : dp[i-1][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = t[j] == s[0] ? 1 : dp[0][j-1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s[i] == t[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m-1][n-1];
    }
}
