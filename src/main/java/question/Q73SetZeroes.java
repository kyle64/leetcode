/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wuziheng
 * @description 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * @date 2025/4/6 00:00
 **/
public class Q73SetZeroes {
    public void setZeroesExtraMNSpace(int[][] matrix) {
        // 使用额外的O(m*n)的空间
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] z = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    Arrays.fill(z[i], true);
                    for (int k = 0; k < n; k++) {
                        z[k][j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (z[i][j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroesMPlusN(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // 使用额外的O(m*n)的空间
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rowSet.contains(i) || colSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroesExtra2Variables(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // 使用matrix的第一行和第一列来记录填充0的标识，优先处理第一行、第一列，使用额外的两个变量, O(1)
        boolean row0, col0;
        row0 = col0 = false;

        // row0
        for (int j = 0; j < m; j++) {
            if (matrix[0][j] == 0) {
                row0 = true;
            }
        }

        // col0
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
            }
        }

        // 使用matrix的第一行和第一列来记录填充0的标识
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // 根据matrix的第一行和第一列来填充0
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 处理row0, col0
        if (row0) {
            for (int j = 0; j < m; j++) {
                matrix[0][j] = 0;
            }
        }

        if (col0) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }

    }
}
