/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wuziheng
 * @description
 * @date 2025/4/30 16:16
 **/
public class Q200NumIslands {
    // 方向数组
    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int res = 0;

    public int numIslandsDfs(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (dfs(grid, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean dfs(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return false;
        }

        if (grid[row][col] != '1') {
            return false;
        }

        // 标记成!='1'的值，避免循环遍历
        grid[row][col] = '0';
        for (int[] dir : dirs) {
            dfs(grid, row + dir[0], col + dir[1]);
        }
        return true;
    }

    public int numIslandsBfs(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void bfs(char[][] grid, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {row, col});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int i = node[0];
            int j = node[1];

            // 越界
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
                continue;
            }
            // 非目标值'1'
            if (grid[i][j] != '1') {
                continue;
            }

            // 设置已访问的标记
            grid[i][j] = '2';
            // 将相邻节点放入队列
            for (int[] dir : dirs) {
                queue.offer(new int[] {i + dir[0], j + dir[1]});
            }
        }
    }

    public int numIslands1(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check(grid, i, j, visited)) {
                    res++;
                }
            }
        }

        return res;
    }

    private boolean check(char[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }

        if (visited[i][j]) {
            return false;
        }

        if (grid[i][j] == '1') {
            visited[i][j] = true;
            for (int[] dir : dirs) {
                check(grid, i + dir[0], j + dir[1], visited);
            }
            return true;
        } else {
            return false;
        }
    }

    class UnionFind {
        int count;
        int[] parent;
        int[] rank; // 树的高度，用于将小树合入大树中，本题意义不大

        public UnionFind(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            // 压缩成一维数组
            parent = new int[m * n];
            rank = new int[m * n];
            count = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        count++;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            // 每连通一次，count减一
            int rootx = find(x);
            int rooty = find(y);

            // 未连通状态
            if (rootx != rooty) {
                // 将节点高度rank较小的合入大的节点
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx]++;
                }

                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslandsUnionFind(char[][] grid) {
        // 查并集，计算连通分量
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int res = 0;
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    for (int[] dir : dirs) {
                        // 相邻节点
                        int r = i + dir[0], c = j + dir[1];
                        if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == '1') {
                            uf.union(i * n + j, r * n + c);
                        }
                    }
                }
            }
        }
        return uf.getCount();

    }
}
