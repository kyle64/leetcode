package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ziheng on 2020/7/29.
 */
public class Q72EditDistance {
    /**
     * @Description: 72. 编辑距离
     *
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     *
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *  
     *
     * 示例 1：
     *
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * 示例 2：
     *
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/edit-distance
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/29 上午9:48
     * @param word1
     * @param word2
     * @return int
     */

    // 插入一个字符，相当于比较word1和word2.substr(1)
    // 删除一个字符，相当于比较word1.substr(1)和word2
    // 替换一个字符，相当于比较word1.substr(1)和word2.substr(1)

    public static int minDistance(String word1, String word2) {
        // DP
        // 二维数组dp[i][j]代表到w1的前i个字母转换成w2的前j个字母的步数
        // dp[i - 1][j]代表上一次删除之前的状态
        // dp[i][j - 1]代表上一次插入之前的状态
        // dp[i - 1][j - 1]代表上一次替换之前的状态
        // dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // 初始化dp
        // w2是空字符串，w1需要全删
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        // w1是空字符串，w1需要全加上w2
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }


    private static int[][] memo;
    // 记忆化dfs
    public static int minDistanceMemoDfs(String word1, String word2) {
        memo = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        return minDistanceMemoDfsHelper(word1, word2, 0 ,0);
    }

    public static int minDistanceMemoDfsHelper(String word1, String word2, int i, int j) {
        // 记忆数组中有w1[i,len)转换成w2[j,len2)的步数
        if (memo[i][j] >= 0) {
            return memo[i][j];
        }

        // word1遍历到最后一个元素，意味着剩下的word2都需要insert
        if (i == word1.length()) {
            return word2.length() - j;
        }
        // word2遍历到最后一个元素，意味着剩下的word1都需要remove
        if (j == word2.length()) {
            return word1.length() - i;
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            return minDistanceMemoDfsHelper(word1, word2, i + 1, j + 1);
        } else {
            int insertSteps = minDistanceMemoDfsHelper(word1, word2, i, j + 1);
            int removeSteps = minDistanceMemoDfsHelper(word1, word2, i + 1, j);
            int replaceSteps = minDistanceMemoDfsHelper(word1, word2, i + 1, j + 1);

            memo[i][j] = Math.min(insertSteps, Math.min(removeSteps, replaceSteps)) + 1;
            return memo[i][j];
        }
    }


    // 暴力递归
    // 参考 https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-cban-ben-cong-bao-li-fa-xiang-dao-s/
    // 超时 O(3^N)
    public static int minDistanceBrutalForce(String word1, String word2) {
        return minDistanceBrutalRecursion(word1, word2);
    }

    private static int minDistanceBrutalRecursion(String word1, String word2) {
        // 插入一个字符，相当于比较word1和word2.substr(1)
        // 删除一个字符，相当于比较word1.substr(1)和word2
        // 替换一个字符，相当于比较word1.substr(1)和word2.substr(1)
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }

        if (word1.charAt(0) == word2.charAt(0)) {
            return minDistanceBrutalRecursion(word1.substring(1), word2.substring(1));
        }

        int insertSteps = minDistanceBrutalRecursion(word1.substring(1), word2);
        int removeSteps = minDistanceBrutalRecursion(word1, word2.substring(1));
        int replaceSteps = minDistanceBrutalRecursion(word1.substring(1), word2.substring(1));

        return Math.min(insertSteps, Math.min(removeSteps, replaceSteps)) + 1;
    }

    public static void main(String[] args) {
        String word1 = "dinitrophenylhydrazine";
        String word2 = "acetylphenylhydrazine";
        System.out.println(minDistance(word1, word2));
    }
}
