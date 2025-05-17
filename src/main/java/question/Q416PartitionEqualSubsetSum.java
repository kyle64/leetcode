/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

/**
 * @author wuziheng
 * @description 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 *
 * @date 2025/5/15 17:33
 **/
public class Q416PartitionEqualSubsetSum {

    public boolean canPartitionDPOpt(int[] nums) {
        // 是否可以选取几个元素，使其总和等于数组和的一半
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 总和是奇数则肯定无法相等，直接返回false
        if (sum % 2 == 1) {
            return false;
        }

        int amount = sum / 2;
        // dp[i][j] 在nums[0:i]区间中，amount=j能否取到
        // dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]] if j >= nums[i]
        // 空间优化，dp[i][x] 只和dp[i-1][]有关，同时j需要依赖j - nums[i]，即比j小的值
        // 因此从后往前遍历
        boolean[] dp = new boolean[amount+1];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = amount; j >= 0; j--) {
                if (j >= nums[i]) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
            if (dp[amount]) {
                return true;
            }
        }
        return dp[amount];
    }

    public boolean canPartitionDP1(int[] nums) {
        // 是否可以选取几个元素，使其总和等于数组和的一半
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 总和是奇数则肯定无法相等，直接返回false
        if (sum % 2 == 1) {
            return false;
        }

        int amount = sum / 2;
        // dp[j][i] 在nums[0:j]区间中，amount=i能否取到
        // dp[j][i] = dp[j-1][i] or dp[j-1][i - nums[j]]
        boolean[][] dp = new boolean[n][amount+1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if (nums[0] <= amount) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                int rem = j - nums[i];
                if (rem == 0) {
                    dp[i][j] = true;
                } else if (rem > 0) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][rem];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // for (int i = 1; i <= amount; i++) {
        //     for (int j = 1; j < n; j++) {
        //         int rem = i - nums[j];
        //         if (rem == 0) {
        //             dp[j][i] = true;
        //         } else if (rem > 0) {
        //             dp[j][i] = dp[j - 1][i] || dp[j - 1][rem];
        //         } else {
        //             dp[j][i] = dp[j - 1][i];
        //         }
        //     }
        // }
        return dp[n-1][amount];
    }

    public boolean canPartitionDP2(int[] nums) {
        // 是否可以选取几个元素，使其总和等于数组和的一半
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 总和是奇数则肯定无法相等，直接返回false
        if (sum % 2 == 1) {
            return false;
        }

        int amount = sum / 2;
        // dp[i][j] 取nums[j]时i能否取道
        // dp[i][j] = dp[k][i - nums[j] - nums[k]]
        boolean[][] dp = new boolean[n][amount+1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if (nums[0] <= amount) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j < n; j++) {
                int rem = i - nums[j];
                if (rem == 0) {
                    dp[j][i] = true;
                } else if (rem > 0) {
                    dp[j][i] = dp[j - 1][i] || dp[j - 1][rem];
                } else {
                    dp[j][i] = dp[j - 1][i];
                }
            }
        }

        return dp[n-1][amount];

        // dfs 自顶而下，超时
        // dfs(nums, 0, amount);
        // return valid;
    }

    boolean valid = false;
    public boolean canPartitionDfs(int[] nums) {
        // 是否可以选取几个元素，使其总和等于数组和的一半
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 总和是奇数则肯定无法相等，直接返回false
        if (sum % 2 == 1) {
            return false;
        }

        int amount = sum / 2;
        // dfs 自顶而下，超时
        dfs(nums, 0, amount);
        return valid;
    }

    private void dfs(int nums[], int index, int amount) {
        if (amount == 0) {
            valid = true;
            return;
        }
        if (amount < 0) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            dfs(nums, i + 1, amount - nums[i]);
        }
    }

    public static void main(String[] args) {
        int[] input = {1,5,10,6};
        int[] input2 = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,
                100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        System.out.println(new Q416PartitionEqualSubsetSum().canPartitionDfs(input));
    }
}
