package question;

import java.util.Arrays;

/**
 * Created by ziheng on 2020/1/17.
 */
public class Q64MinPathSum {
    /**
     * @Description: 64. 最小路径和
     *
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * 说明：每次只能向下或者向右移动一步。
     *
     * 示例:
     *
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 7
     * 解释: 因为路径 1→3→1→1→1 的总和最小
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/23 上午1:11
     * @param
     * @return int
     */
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
//        int[][] input = {{7, 3, 1, 5}, {1, 5, 3, 2}, {4, 2, 9, 8}};
        int[][] input = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        System.out.println(minPathSum(input));
    }
}
