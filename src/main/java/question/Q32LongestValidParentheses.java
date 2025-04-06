package question;

import java.util.*;

/**
 * Created by ziheng on 2020/7/23.
 */
public class Q32LongestValidParentheses {

    /**
     * @Description: 32. 最长有效括号
     *
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     *
     * 示例 1:
     *
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     * 示例 2:
     *
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/23 下午2:39
     * @param
     * @return int
     */
    public static int longestValidParenthesesDP(String s) {
        // dp[i]表示以下标 i 字符结尾的最长有效括号的长度
        // 如果是s[i]是左括号，那么肯定非法，dp[i] = 0
        // dp[i] = dp[i - 2] + 2 if s[i-1, i] = "()", 正好加了一组括号
        // dp[i] = dp[i - 1] + dp[i - dp[i-1] - 2] + 2
        // s[i]和s[i-1]都是右括号，那么要判断s[i - dp[i-1] - 1]是否为左括号，如果是那么+2，然后再加上这之前的最长子串，即dp[i - dp[i-1] - 2]
        if (s == null || s.length() < 2) return 0;

        int count = 0;
        // 初始化
        int[] dp = new int[s.length()];
        dp[0] = 0;
        // s[0,1]要么是"()"，要么非法
        if (s.charAt(0) == '(' && s.charAt(1) == ')') {
            dp[1] = 2;
            count = 2;
        }

        for (int i = 2; i < s.length(); i++) {
            // 只考虑右括号，因为左括号肯定非法，默认值0
            if (s.charAt(i) == ')') {
                // s[i-1, i]正好添加了一对括号
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else {
                    // s[i-1]是左括号

                    // 减去dp[i-1]的长度，在这之前也有左括号的话，那么正好和s[i]凑成一对，然后再加上与dp[i - dp[i-1] - 2]的字符串相连
                    if (i - dp[i-1] - 1 >= 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                        // 判断i - dp[i-1] - 2是否越界
                        if (i - dp[i-1] - 2 > 0) {
                            dp[i] = dp[i-1] + dp[i - dp[i-1] - 2] + 2;
                        } else {
                            dp[i] = dp[i-1] + 2;
                        }
                    }
                }
                // 更新最大长度
                count = Math.max(count, dp[i]);
            }
        }

        return count;
    }


    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;

        // 用一个栈保存左括号的下标，如果最后栈中还有数字，那就是未被匹配的左括号的位置
        // 栈中的元素代表着当前还未被匹配的最后一个下标
        // 栈底保存一个当前未匹配的最后一个右括号的下标，开始时默认-1
        int count = 0;
        int result = 0;
        int lastUnmatchedRight = -1;

        // 初始化栈。栈底是上一个右括号的下标
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(lastUnmatchedRight);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 左括号下标入栈
            if (c == '(') {
                stack.push(i);
                continue;
            }
            // 右括号出栈
            if (c == ')') {
                stack.pop();

                if (stack.isEmpty()) {
                    count = 0;
                    // 意味着右括号多出来了，把当前长度截断，将当前右括号下标入栈
                    stack.add(i);
                } else {
                    count = i - stack.peek();
                    result = Math.max(result, count);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String input = "((()))())";
        System.out.println(longestValidParenthesesDP(input));
    }
}
