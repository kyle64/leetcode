/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.Arrays;

/**
 * @author wuziheng
 * @description 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * 进阶：
 *
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 *
 * @date 2025/5/15 13:57
 **/
public class Q300LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // 动态规划 O(N^2)
        // dp[i]:以nums[i]为结尾的最长子序列长度
        // dp[i] = dp[j] + 1 if nums[j] < nums[i] where 0 <= j < i
        int res = 1;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLISBinarySearch(int[] nums) {
        // 考虑一个简单的贪心，如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小。
        // 维护一个数组 d[i] ，表示长度为 i 的最长上升子序列的末尾元素的最小值
        int len = 1;
        int n = nums.length;
        int[] d = new int[n + 1];
        d[len] = nums[0];

        for (int i = 1; i < n; i++) {
            // nums[i] > 当前最长子序列的末尾元素，直接添加到数组d
            if (nums[i] > d[len]) {
                len++;
                d[len] = nums[i];
            } else {
                // 二分查找，找到第一个比 nums[i] 小的数 d[k]，并更新d[k+1]
                int left = 1, right = len;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (d[mid] > nums[i]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                d[left] = nums[i];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] input = {10,9,2,5,3,7,101,18};
        System.out.println(new Q300LongestIncreasingSubsequence().lengthOfLISBinarySearch(input));
    }
}
