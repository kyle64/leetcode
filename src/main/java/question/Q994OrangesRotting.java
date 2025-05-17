/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wuziheng
 * @description 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * <p>
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
 * 示例 3：
 * <p>
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 * @date 2025/4/30 23:43
 **/
public class Q994OrangesRotting {
    public int orangesRotting(int[][] grid) {
        // 记录层数的bfs
        // 方向数组
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0, timer = 0;

        // init
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (freshCount > 0 && !queue.isEmpty()) {
            int layerLength = queue.size();

            for (int i = 0; i < layerLength; i++) {
                int[] node = queue.poll();
                int row = node[0], col = node[1];
                for (int[] dir : dirs) {
                    int x = row + dir[0], y = col + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;

                        freshCount--;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            timer++;
        }

        return freshCount > 0 ? -1 : timer;
    }

    public static void main(String[] args) {
        int[][] input = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        System.out.println(new Q994OrangesRotting().orangesRotting(input));
    }
}
