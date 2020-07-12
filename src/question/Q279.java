package question;

import java.util.Arrays;

/**
 * Created by ziheng on 2020/1/19.
 */
public class Q279 {
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = i; // worst case

            for (int j = 1; i - j*j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }


//            int base = (int) Math.sqrt(i);
//
//            if (base * base == i) {
//                dp[i] = 1;
//                continue;
//            }
//
//            for (int j = 1; j <= i / 2; j++) {
//                dp[i] = Math.min(dp[i], dp[i - j] + dp[j]);
//            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(13));
    }
}
