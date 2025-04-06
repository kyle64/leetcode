package question;

import java.util.List;

/**
 * Created by ziheng on 2020/1/15.
 */
public class Q120 {
    public static int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();
        int length = triangle.get(height-1).size();

        int[][] dp = new int[length+1][height+1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> row = triangle.get(i);

            for (int j = 0; j < row.size(); j++) {
                if (i == triangle.size() - 1) {
                    dp[i][j] = row.get(j);
                } else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + row.get(j);
                }
            }

        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] a = new int[]{3,4};
    }
}
