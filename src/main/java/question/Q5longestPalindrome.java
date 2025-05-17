package question;

/**
 * Created by ziheng on 2020/7/16.
 */
public class Q5longestPalindrome {
    /**
     * @param
     * @return java.lang.String
     * @Description: 5. 最长回文子串
     * <p>
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @date 2020/7/16 上午11:35
     */
    public String longestPalindrome(String s) {
        // 中心扩散，找到最长的回文子串
        if (s.length() < 2) return s;
        // 所有的回文子串都等于更小的子串首尾加上相同的字符
        int length = s.length();
        String result = "";

        // 第一个和最后一个元素无法拓展
        for (int i = 0; i < length - 1; i++) {
            // 奇数长度的回文串
            String s1 = expandAroundCenterString(s, i, i);
            // 偶数长度的回文串
            String s2 = expandAroundCenterString(s, i, i + 1);
            String currentPalindrome = s1.length() > s2.length() ? s1 : s2;
            result = currentPalindrome.length() > result.length() ? currentPalindrome : result;
        }

        return result;
    }

    public String expandAroundCenterString(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // left和right刚好处于不符合要求的位置，需要去掉
        return s.substring(left + 1, right);
    }


    // leetcode manacher解放，添加分隔符#
    public String longestPalindromeManacher(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        String str = addBoundaries(s, '#');
        int sLen = 2 * len + 1;
        int maxLen = 1;

        int start = 0;
        for (int i = 0; i < sLen; i++) {
            int curLen = centerSpread(str, i);
            if (curLen > maxLen) {
                maxLen = curLen;
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int centerSpread(String s, int center) {
        int len = s.length();
        int i = center - 1;
        int j = center + 1;
        int step = 0;
        while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            step++;
        }
        return step;
    }


    /**
     * 创建预处理字符串
     *
     * @param s      原始字符串
     * @param divide 分隔字符
     * @return 使用分隔字符处理以后得到的字符串
     */
    private String addBoundaries(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(divide);
            stringBuilder.append(s.charAt(i));
        }
        stringBuilder.append(divide);
        return stringBuilder.toString();
    }


    public String longestPalindromeDP(String s) {
        // 时间复杂度O(n^2), 空间复杂度O(n^2)
        if (s.length() < 2) return s;

        // 所有的回文子串都等于更小的子串首尾加上相同的字符
        int length = s.length();
        // s(i, j)是否为回文子串的dp，always有i <= j，所以只填右上部分的三角
        // 状态转移方程：dp[i][j] = dp[i + 1][j - 1] && chars[j] == chars[j];
        boolean[][] dp = new boolean[length][length];
        char[] chars = s.toCharArray();
        String result = "";

        for (int j = 0; j < length; j++) {
            for (int i = 0; i <= j; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    // xax或者xaax的情况
                    if (i == j || i + 1 == j) {
                        dp[i][j] = true;
                    } else {
                        // 运行到这里的i一定小于j - 1，因此dp[i + 1][j - 1]已经被计算过
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }


    public String longestPalindrome1(String s) {
        // 时间复杂度O(n^3)
        if (s.length() < 2) return s;
        String[] dp = new String[s.length() + 1];
        dp[0] = "";
        dp[1] = String.valueOf(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            String current = dp[i - 1];
            for (int j = i - dp[i - 1].length(); j >= 0; j--) {
                current = isPalindrome(s, j, i) ? s.substring(j, i + 1) : current;
            }
            dp[i] = current;
        }

        return dp[s.length() - 1];
    }

    public boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }

        return true;
    }

    public String longestPalindromeDP1(String s) {
        // dp[i][j]: 表示s[i:j]是否回文
        // dp[i][j] = dp[i+1][j-1] if i < j - 1 and s[i] = s[j]
        char[] sArray = s.toCharArray();
        int len = sArray.length;
        boolean[][] dp = new boolean[len][len];
        int maxLen = 1;
        int start = 0;

        // init
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < len; j++) {
            for (int i = j - 1; i >= 0; i--) {
                // 1 2 3 4 5
                // 1 2 3 4 5

                if (sArray[i] == sArray[j]) {
                    // a, aa, aba
                    if (j - i <= 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                } else {
                    dp[i][j] = false;
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        Q5longestPalindrome q5longestPalindrome = new Q5longestPalindrome();
        String s = "abbad";
        System.out.println(q5longestPalindrome.longestPalindrome(s));
    }
}
