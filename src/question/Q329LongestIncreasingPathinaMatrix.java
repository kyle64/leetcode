package question;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ziheng on 2020/7/26.
 */
public class Q329LongestIncreasingPathinaMatrix {
    /**
     * @Description: 329. 矩阵中的最长递增路径
     *
     * 给定一个整数矩阵，找出最长递增路径的长度。
     *
     * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
     *
     * 示例 1:
     *
     * 输入: nums =
     * [
     *   [9,9,4],
     *   [6,6,8],
     *   [2,1,1]
     * ]
     * 输出: 4
     * 解释: 最长递增路径为 [1, 2, 6, 9]。
     * 示例 2:
     *
     * 输入: nums =
     * [
     *   [3,4,5],
     *   [3,2,6],
     *   [2,2,1]
     * ]
     * 输出: 4
     * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/26 下午8:58
     * @param
     * @return int
     */

    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int rows, columns;

    // 官方，dp + 拓扑排序
    public int longestIncreasingPathOfficial(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        rows = matrix.length;
        columns = matrix[0].length;
        // 将矩阵看成一个有向图，计算每个单元格对应的出度，即有多少条边从该单元格出发。
        // 对于作为边界条件的单元格，该单元格的值比所有的相邻单元格的值都要大，因此作为边界条件的单元格的出度都是 0
        int[][] outdegrees = new int[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                for (int[] dir : dirs) {
                    int newRow = i + dir[0], newColumn = j + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[i][j]) {
                        // 在矩阵范围内，且比相邻的单元格小的，出度+1
                        ++outdegrees[i][j];
                    }
                }
            }
        }

        // 拓扑排序
        // 出度为0，加入队列
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (outdegrees[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            ++ans;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cell = queue.poll();
                int row = cell[0], column = cell[1];
                for (int[] dir : dirs) {
                    int newRow = row + dir[0], newColumn = column + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] < matrix[row][column]) {
                        --outdegrees[newRow][newColumn];
                        // 判断相邻节点出度是否为0，即：是否还有相邻的更大的数
                        if (outdegrees[newRow][newColumn] == 0) {
                            queue.offer(new int[]{newRow, newColumn});
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        // 记忆化深度有限搜索，用一个memo数组记录已经计算过的节点
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] memo = new int[height][width];

        int longestPath = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                longestPath = Math.max(longestIncreasingPathHelper(matrix, i, j, memo), longestPath);
            }
        }

        return longestPath;
    }

    private static int longestIncreasingPathHelper(int[][] matrix, int i, int j, int[][] memo) {
        // 已经访问过，直接返回
        if (memo[i][j] != 0) return memo[i][j];

        int height = matrix.length;
        int width = matrix[0].length;

        int current = matrix[i][j];

        int upper = i > 0 && current > matrix[i - 1][j] ? longestIncreasingPathHelper(matrix,i - 1, j, memo) : 0;
        int bottom = i + 1 < height && current > matrix[i + 1][j] ? longestIncreasingPathHelper(matrix,i + 1, j, memo) : 0;
        int left = j > 0 && current > matrix[i][j - 1] ? longestIncreasingPathHelper(matrix, i, j - 1, memo) : 0;
        int right = j + 1 < width && current > matrix[i][j + 1] ? longestIncreasingPathHelper(matrix, i, j + 1, memo) : 0;

        memo[i][j] = Math.max(upper, Math.max(bottom, Math.max(left, right))) + 1;
        return memo[i][j];
    }

    // 递归，超时
    public static int longestIncreasingPath1(int[][] matrix) {
        if (matrix.length == 0) return 0;

        int height = matrix.length;
        int width = matrix[0].length;
        // dp[i][j]表示matrix[i][j]结尾的最大长度
        int[][] dp = new int[height][width];

        int longestPath = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                longestPath = Math.max(longestIncreasingPathHelper1(matrix, i, j), longestPath);
            }
        }

        return longestPath;
    }

    private static int longestIncreasingPathHelper1(int[][] matrix, int i, int j) {
        int height = matrix.length;
        int width = matrix[0].length;

        int current = matrix[i][j];

        int upper = i > 0 && current > matrix[i - 1][j] ? longestIncreasingPathHelper1(matrix,i - 1, j) : 0;
        int bottom = i + 1 < height && current > matrix[i + 1][j] ? longestIncreasingPathHelper1(matrix,i + 1, j) : 0;
        int left = j > 0 && current > matrix[i][j - 1] ? longestIncreasingPathHelper1(matrix, i, j - 1) : 0;
        int right = j + 1 < width && current > matrix[i][j + 1] ? longestIncreasingPathHelper1(matrix, i, j + 1) : 0;

        return Math.max(upper, Math.max(bottom, Math.max(left, right))) + 1;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };

        System.out.println(longestIncreasingPath(matrix));
    }
}
