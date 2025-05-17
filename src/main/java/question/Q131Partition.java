/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author wuziheng
 * @description
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 *
 * @date 2025/5/3 00:44
 **/
public class Q131Partition {
    final List<List<String>> res = new ArrayList<>();
    public List<List<String>> partition(String s) {
        dfs(s, 0, new ArrayDeque<>());
        return res;
    }

    private void dfs(String s, int startIndex, Deque<String> path) {
        if (startIndex >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            // 截取字符串判断子串是否回文
            if (isPalindrome(s, startIndex, i)) {
                path.addLast(s.substring(startIndex, i + 1));
                dfs(s, i + 1, path);
                path.pollLast();
            }
        }
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }

        return true;
    }

    boolean[][] dp;
    public List<List<String>> partitionDP(String s) {
        // dp[i][j]表示s[i:j]的子串是否回文, dp[i][j] = dp[i-1][j+1] && (s[i] == s[j])
        int n = s.length();
        dp = new boolean[n][n];

        // init dp
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
//            Arrays.fill(dp[i], true);
             dp[i][i] = true;
             dp[i][i - 1] = true;
        }
        // 不用考虑i > j的情况，即：只需考虑dp数组的右上半部分
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }

        dfsDP(s, 0, new ArrayDeque<>());
        return res;
    }

    private void dfsDP(String s, int startIndex, Deque<String> path) {
        if (startIndex >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            // 字符串是否回文的判断可以用dp优化
            if (dp[startIndex][i]) {
                path.addLast(s.substring(startIndex, i + 1));
                dfs(s, i + 1, path);
                path.pollLast();
            }
        }
    }

    public static void main(String[] args) {
        String input = "aab";
        List<List<String>> result = new Q131Partition().partitionDP(input);
        System.out.println(Arrays.deepToString(result.toArray()));
    }
}
