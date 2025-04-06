package question;

/**
 * Created by ziheng on 2020/8/11.
 */
public class Q130SurroundedRegions {
    /**
     * @Description: 130. 被围绕的区域
     *
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     *
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * 示例:
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * 解释:
     *
     * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/surrounded-regions
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/8/11 下午11:45
     * @param
     * @return
     */
    int[][] memo;
    int height;
    int width;
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        // 将所有边界上相邻的O保留，其他的变成X
        height = board.length;
        width = board[0].length;
        memo = new int[height][width];

        // 遍历最外层
        for (int i = 0; i < width; i++) {
            dfs(board, 0, i);
            dfs(board, height - 1, width - 1 - i);
        }

        for (int i = 1; i < height - 1; i++) {
            dfs(board, i, width - 1);
            dfs(board, height - 1 - i, 0);
        }

        // 遍历每一个，将未标记的O变成X
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'X') continue;
                if (memo[i][j] != 1) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= height || y < 0 || y >= width) return;
        if (memo[x][y] == 1) return;
        if (board[x][y] == 'X') return;
        else if (board[x][y] == 'O') {
            memo[x][y] = 1;
            dfs(board, x - 1, y);
            dfs(board, x + 1, y);
            dfs(board, x, y - 1);
            dfs(board, x, y + 1);
        }
    }
}
