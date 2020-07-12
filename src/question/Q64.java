package question;

import java.util.Arrays;

/**
 * Created by ziheng on 2020/1/17.
 */
public class Q64 {
    public static int minPathSum(int[][] grid) {
        if (grid.length == 0) return 0;
        int len = grid[0].length;

        // create dp array
        int[][] dp = new int[grid.length][len];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < len; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[0][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][0];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }

            }
        }

        return dp[grid.length - 1][len - 1];
    }

    public static void main(String[] args) {
        int[][] input = {{7, 3, 1, 5}, {1, 5, 3, 2}, {4, 2, 9, 8}};

        System.out.println(minPathSum(input));
    }
}
