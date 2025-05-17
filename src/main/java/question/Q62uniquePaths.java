package question;

/**
 * Created by ziheng on 2020/7/14.
 */
public class Q62uniquePaths {
    /**
     * @Description: 62. 不同路径
     *
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 问总共有多少条不同的路径？
     *
     *
     *
     * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
     *
     *  
     *
     * 示例 1:
     *
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     * 示例 2:
     *
     * 输入: m = 7, n = 3
     * 输出: 28
     *  
     *
     * 提示：
     *
     * 1 <= m, n <= 100
     * 题目数据保证答案小于等于 2 * 10 ^ 9
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/15 上午3:52
     * @param m
     * @param n
     * @return int
     */
    public int uniquePaths(int m, int n) {
        // 每一个格子的路径等于左边和上边的格子中的路径和
        int[][] dp = new int[n][m];

        // m列 * n行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 第一个格子
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                }
                // 第一行的格子，走法只等于左边
                else if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                }
                // 第一列的格子，走法只等于上边
                else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[n - 1][m - 1];
    }

    public int uniquePathsDP(int m, int n) {
        // dp[i][j]: 走到board[i][j]的方案数
        // dp[i][j] = dp[i-1][j] + dp[i][j-1]
        int[][] dp = new int[m][n];
        // init
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];
    }

    public int uniquePathsDPOpt(int m, int n) {
        // dp[i][j]: 走到board[i][j]的方案数
        // dp[i][j] = dp[i-1][j] + dp[i][j-1]
        // 空间优化
        int[] dp = new int[n];
        // init
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n-1];
    }
}
