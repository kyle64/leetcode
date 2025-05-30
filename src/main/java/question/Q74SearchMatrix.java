/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

/**
 * @author wuziheng
 * @description
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 * @date 2025/4/6 17:01
 **/
public class Q74SearchMatrix {

    public boolean searchMatrixBinarySearch(int[][] matrix, int target) {
        // 二分查找，O(MlogN)
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            int left = 0, right = n;
            // 左闭右开区间[left, right), left = right时right无效，因此使用left < right 且右边界直接使用mid
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (matrix[i][mid] == target) {
                    return true;
                } else if (matrix[i][mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }

        return false;
    }

    public boolean searchMatrix2BinarySearch(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 找行
        int left = 0, right = m - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 特殊情况直接返回
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (right < 0) {
            return false;
        }

        int row = right;

        // 找列
        left = 0;
        right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 特殊情况直接返回
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public boolean searchMatrixBinarySearchOnce(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 完全升序，考虑一次遍历
        int left = 0, right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int row = mid / n, col = mid % n;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return false;
    }

    public boolean searchMatrixAbstractBST(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean searchMatrixBST2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 旋转45度，从右上角开始遍历
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }

}
