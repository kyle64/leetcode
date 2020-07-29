package question;

/**
 * Created by ziheng on 2020/1/19.
 */
public class Q343IntegerBreak {
    /**
     * @Description: 343. 整数拆分
     *
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     * 示例 2:
     *
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     * 说明: 你可以假设 n 不小于 2 且不大于 58。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/integer-break
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/30 上午2:39
     * @param
     * @return int
     */
    public int integerBreakGreedyAlgorithm(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        if (b == 1) return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }

    public static int integerBreakDP(int n) {
        // dp[i]表示i的最大乘积
        // dp[i] = max(max(j*dp[i-j], j*(i-j))), 1<=j<n)
        // j 表示减掉一个数j，那么乘积最大是j * (i - j) 或者 j * dp[i - j]，取决于哪个更大，j有i-1种值
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }

    public static int integerBreakDynamicProgramming(int n) {
        // dp[i]表示i的最大乘积
        // dp[i] = max(max(j*dp[i-j], j*(i-j))), 1<=j<n)
        // j 表示减掉一个数j，那么乘积最大是j * (i - j) 或者 j * dp[i - j]，取决于哪个更大，j有i-1种值
        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], (i - j) * Math.max(dp[j], j));
            }
        }

        return dp[n];
    }

    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = integerBreakHelper(i, dp);
        }

        return dp[n];
    }

    public static int integerBreakHelper(int n, int[] dp) {
        int temp = 0;
        for (int i = 1; i < n; i++) {
            temp = Math.max(temp, (n - i) * Math.max(dp[i], i));
//            temp = Math.max(temp, (n-i) * dp[i]);
        }

        return temp;
    }


    public static int integerBreak1(int n) {
        if (n <= 2) return 1;

        int curMax = 0;

        for (int i = 1; i < n; i++) {
            curMax = Math.max(curMax, i * (n - i));
            curMax = Math.max(curMax, i * integerBreak1(n - i));
        }

        return curMax;
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(8));
    }
}
