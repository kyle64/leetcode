/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wuziheng
 * @description
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[["Q"]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 9
 *
 * @date 2025/5/3 15:57
 **/
public class Q51SolveNQueens {
    final List<List<String>> res = new ArrayList<>();
    int[] queens;
    Set<Integer> cols;
    Set<Integer> diagonals1;
    Set<Integer> diagonals2;
    public List<List<String>> solveNQueens(int n) {
        queens = new int[n];
        cols = new HashSet<>();
        diagonals1 = new HashSet<>();
        diagonals2 = new HashSet<>();

        dfs(n, 0, queens, cols, diagonals1, diagonals2);
        return res;
    }

    private void dfs(int n, int row, int[] queens, Set<Integer> cols, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            buildBoard(queens, n);
            return;
        }

        // 在row这一行，尝试在每一列上摆放Q
        for (int i = 0; i < n; i++) {
            if (cols.contains(i) || diagonals1.contains(i - row) || diagonals2.contains(i + row)) {
                continue;
            }
            queens[row] = i;
            cols.add(i);
            // 左上->右下方向的斜线，同一条线上的row - col值是一样的
            diagonals1.add(i - row);
            // 左下->右上方向的斜线，同一条线上的row + col值是一样的
            diagonals2.add(i + row);
            dfs(n, row + 1, queens, cols, diagonals1, diagonals2);
            diagonals2.remove(i + row);
            diagonals1.remove(i - row);
            cols.remove(i);
            // 覆盖，无需恢复，可省略
            queens[row] = -1;
        }
    }

    private void buildBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>(n);
        for (int col : queens) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[col] = 'Q';
            board.add(new String(row));
        }
        res.add(board);
    }

    public List<List<String>> solveNQueens1(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] chars : chessboard) {
            Arrays.fill(chars, '.');
        }
        backtracking(n, 0, chessboard);
        return res;
    }

    private void backtracking(int n, int row, char[][] chessboard) {
        if (row == n) {
            res.add(convertToList(chessboard));
            return;
        }

        for(int col = 0; col < n; col++) {
            if(isValid(row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                backtracking(n, row + 1, chessboard);
                chessboard[row][col] = '.';
            }
        }
    }

    public List<String> convertToList(char[][] chessboard){
        List<String> list = new ArrayList<>();
        for (char[] chars : chessboard) {
            list.add(String.copyValueOf(chars));
        }
        return list;
    }

    public boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 向上遍历列
        for(int i = 0; i < row; i++){
            if (chessboard[i][col] == 'Q') return false;
        }
        // 向左上遍历斜线
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--){
            if (chessboard[i][j] == 'Q') return false;
        }
        // 向右上遍历斜线
        for(int i = row - 1, j = col + 1; i>= 0 && j < chessboard.length; i--, j++){
            if (chessboard[i][j] == 'Q') return false;
        }

        return true;
    }
}
