package question;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ziheng on 2020/7/23.
 */
public class Q22GenerateParentheses {

    /**
     * @Description: 22. 括号生成
     *
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     *  
     *
     * 示例：
     *
     * 输入：n = 3
     * 输出：[
     *        "((()))",
     *        "(()())",
     *        "(())()",
     *        "()(())",
     *        "()()()"
     *      ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/generate-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/23 上午10:04
     * @param
     * @return java.util.List<java.lang.String>
     */
    static List<String> result = new ArrayList<>();
    public static List<String> generateParenthesis(int n) {
        // 递归
        // 每次新增的括号都是在上次n-1的可能性的后面加一个括号
        // 当path中左括号数量大于右括号时，字符串才合法

        if (n == 0) return new LinkedList<>();

        int leftParenthesisCount = 0;
        int rightParenthesisCount = 0;
        generateParenthesisHelper(n, leftParenthesisCount, rightParenthesisCount, "");

        return result;
    }

    private static void generateParenthesisHelper(int n, int leftParenthesisCount, int rightParenthesisCount, String path) {
        // 左右括号都达到n个
        if (leftParenthesisCount == n && rightParenthesisCount == n) {
            result.add(path);
            return;
        }

        // 右括号比左括号多，无论如何都不合法
        if (leftParenthesisCount < rightParenthesisCount) return;

        if (leftParenthesisCount < n) {
            generateParenthesisHelper(n, leftParenthesisCount + 1, rightParenthesisCount, path + "(");
        }

        if (rightParenthesisCount < n) {
            generateParenthesisHelper(n, leftParenthesisCount, rightParenthesisCount + 1, path + ")");
        }
    }

    final List<String> res = new ArrayList<>();
    public List<String> generateParenthesisDFS(int n) {
        // 往两侧分别添加'('和')'，直到左右括号数量都等于n
        // 如果遇到右括号多于左括号时，提前剪枝
        dfs(n, new StringBuilder(), 0, 0);
        return res;
    }

    private void dfs(int n, StringBuilder path, int leftCount, int rightCount) {
        if (path.length() == 2 * n) {
            // 等效于leftCount == n && rightCount == n
            res.add(path.toString());
            return;
        }

        if (leftCount < n) {
            path.append('(');
            dfs(n, path, leftCount + 1, rightCount);
            path.deleteCharAt(path.length() - 1);
        }

        // 因为是按顺序的话是先添加左括号，再添加右括号
        // 所以如果出现右括号数量多于左括号就说明是非法的情况
        if (leftCount > rightCount) {
            path.append(')');
            dfs(n, path, leftCount, rightCount + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public List<String> generateParenthesisDP(int n) {
        List<List<String>> dp = new ArrayList<>();
        dp.add(new ArrayList<>() {{
            add("");
        }});
        // dp.add(new ArrayList<>() {{
        //     add("()");
        // }});

        for (int i = 1; i <= n; i++) {
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                // 将dp[0:i-1]分为dp[0:j]和dp[j:i-1], 保证first+second=i-1
                // dp[i] = "(" + first + ")" + second
                List<String> first = dp.get(j);
                List<String> second = dp.get(i - 1 - j);
                for (String s1 : first) {
                    for (String s2 : second) {
                        tmp.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp.add(tmp);
        }
        return dp.get(n);
    }

    public static void main(String[] args) {
        List<String> result = generateParenthesis(3);
        for (String s : result) {
            System.out.println(s);
        }
    }
}
