package question;

/**
 * Created by ziheng on 2020/1/19.
 */
public class Q343 {
    public int integerBreakGreedyAlgorithm(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        if (b == 1) return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }

    public static int integerBreakDynamicProgramming(int n) {
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
