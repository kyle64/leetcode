package question;

/**
 * Created by ziheng on 2020/7/30.
 */
public class Q79WordSearch {
    /**
     * @Description: 79. 单词搜索
     *
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     *  
     *
     * 示例:
     *
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-search
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/30 下午2:02
     * @param board
     * @param word
     * @return boolean
     */
    private static boolean[][] visited;
    private static int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public static boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    // 优化dfs
    private static boolean dfs(char[][] board, String word, int i, int j, int curr) {
        if (curr == word.length() - 1) {
            return word.charAt(curr) == board[i][j];
        }

        if (board[i][j] == word.charAt(curr)) {
            visited[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int x = i + dirs[k][0];
                int y = j + dirs[k][1];
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y]) {
                    if (dfs(board, word, x, y, curr + 1)) {
                        return true;
                    }
                }
            }
            visited[i][j] = false;
        }

        return false;
    }


    // 递归，超时
    public static boolean exist1(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs1(board, word, i, j, 0)) {
                    return true;
                } else {
                    visited[i][j] = false;
                }
            }
        }

        return false;
    }

    private static boolean dfs1(char[][] board, String word, int i, int j, int curr) {
        if (i >= board.length || j >= board[0].length) return false;

        if (visited[i][j]) {
            return false;
        } else {
            visited[i][j] = true;
        }

        char boardChar = board[i][j];
        if (boardChar != word.charAt(curr)) {
            return false;
        } else {
            if (curr == word.length() -1) return true;

            boolean left = false;
            boolean right = false;
            boolean upper = false;
            boolean bottom = false;

            if (i - 1 >= 0 && !visited[i - 1][j]) {
                upper = dfs1(board, word, i - 1, j, curr + 1);
                visited[i - 1][j] = false;
            }
            if (i + 1 < board.length && !visited[i + 1][j]) {
                bottom = dfs1(board, word, i + 1, j, curr + 1);
                visited[i + 1][j] = false;
            }
            if (j - 1 >= 0 && !visited[i][j - 1]) {
                left = dfs1(board, word, i, j - 1, curr + 1);
                visited[i][j - 1] = false;
            }

            if (j + 1 < board[0].length && !visited[i][j + 1]) {
                right = dfs1(board, word, i, j + 1, curr + 1);
                visited[i][j + 1] = false;
            }

            return (upper || bottom || left || right);
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCB";
//        char[][] board = {{'A','A','A','A'},{'A','A','A','A'},{'A','A','A','B'}};
//        String word = "BAAAAAAAAAAA";

        System.out.println(exist(board, word));
    }
}
