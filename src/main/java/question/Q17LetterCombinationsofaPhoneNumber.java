package question;

import java.util.*;

/**
 * Created by ziheng on 2020/7/22.
 */
public class Q17LetterCombinationsofaPhoneNumber {
    /**
     * @Description:
     *
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     *
     *
     * 示例:
     *
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * 说明:
     * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/22 上午11:39
     * @param
     * @return List<String>
     */

    private final String[] params = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        // 递归
        if (digits == null || digits.length() == 0) return new LinkedList<>();

        List<String> result = new ArrayList<>();

        letterCombinationsHelper(digits, 0, "", result);
        return result;
    }

    private void letterCombinationsHelper(String digits, int index, String path, List<String> result) {
        // 从前往后的递归，每次用string存储当前已写的字符串

        // 终止条件
        if (index == digits.length()) {
            result.add(path);
            return;
        }

        // 寻找输入的数字
        int num = digits.charAt(index) - '0';
        // 找到对应可能的字母的字符串
        String letters = params[num];

        for (int i = 0; i < letters.length(); i++) {
            letterCombinationsHelper(digits, index + 1, path + letters.charAt(i), result);
        }
    }


    // 参照dp，从后往前递归
    private List<String> letterCombinationsHelper1(String digits, int digitIndex, String[] params) {
        if (digitIndex < 0) return new ArrayList<String>() {{add("");}};

        // 寻找输入的数字
        int num = digits.charAt(digitIndex) - '0';
        // 找到对应可能的字母的字符串
        String possibleString = params[num];

        // 递归调用，获取前一个数字序列所对应的所有string
        // 假设都已经获取到了
        List<String> prevList = letterCombinationsHelper1(digits, digitIndex - 1, params);
        List<String> currentResult = new ArrayList<>();
        for (String s : prevList) {
            for (char c : possibleString.toCharArray()) {
                currentResult.add(s + c);
            }
        }

        return currentResult;
    }


    // 动态规划
    // 递归是自上而下的算法, 为了求解f(n)去调用f(n-1)
    // 动态规划是自下而上的算法， 已知f(n-1)去推到f(n)
    public List<String> letterCombinationsDP(String digits) {
        if (digits == null || digits.length() == 0) return new LinkedList<>();

        String[] params = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        // dp[i] 存储长度为i的所有组合
        // dp[i] = dp[i - 1] ** params[digits[i - 1]]
        // ex: digit = "23", dp[1] = {a, b ,c}, dp[2] = {ad,ae,af,bd,be,bf,cd,ce,cf}
        ArrayList<String>[] dp = new ArrayList[digits.length() + 1];

        dp[0] = new ArrayList<String>() {{add("");}};

        for (int i = 1; i <= digits.length(); i++) {
            dp[i] = new ArrayList<>();
            // 寻找输入的数字
            int num = digits.charAt(i - 1) - '0';
            // 找到对应可能的字母的字符串
            String possibleString = params[num];
            for (String savedString : dp[i - 1]) {
                for (char c : possibleString.toCharArray()) {
                    dp[i].add(savedString + c);
                }
            }
        }

        return dp[digits.length()];
    }
}
