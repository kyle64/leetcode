package question;

import java.util.Arrays;

/**
 * Created by ziheng on 2020/8/20.
 */
public class Q647PalindromicSubstrings {
    /**
     * @Description: 647. 回文子串
     *
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     *
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     *
     *  
     *
     * 示例 1：
     *
     * 输入："abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     * 示例 2：
     *
     * 输入："aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     *  
     *
     * 提示：
     *
     * 输入的字符串长度不会超过 1000 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindromic-substrings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/8/20 上午12:25
     * @param
     * @return int
     */
    public int countSubstrings(String s) {
        // 动态规划
        // 判断s[i, j]回文，首先s[i] = s[j]
        // 然后有三种情况，单个字母：i = j，两个字母i + 1 = j，以及三个字母及以上
        // dp[i][j] = dp[i+1][j-1]
        // 保证dp[i+1][j-1]已经填充，遍历方向需考虑
        int count = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], false);
        }

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(j) == s.charAt(i)) {
                    // 一个字母
                    if (i == j) {
                        dp[i][j] = true;
                        count++;
                    } else if (i + 1 == j) {
                        dp[i][j] = true;
                        count++;
                    } else {
                        // j - 1 > i
                        if (dp[i + 1][j - 1]) {
                            dp[i][j] = true;
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    public int countSubstringsOfficial(String s) {
        // 中心拓展
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }
}
