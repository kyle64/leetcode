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

    public static void main(String[] args) {
        List<String> result = generateParenthesis(3);
        for (String s : result) {
            System.out.println(s);
        }
    }
}
