/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

/**
 * @author wuziheng
 * @description 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 * @date 2025/5/14 16:33
 **/
public class Q198HouseRobber {

    public int rob(int[] nums) {
        // 如果nums[i]偷，就意味着在i-1时不能偷，则此时的最大值就=dp[i-2]+nums[i]
        // 如果nums[i-1]偷，则nums[i] = nums[i-1]
        // 因此，dp[i] = max(dp[i-2] + nums[i], dp[i-1])
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

    public int robDPSpaceOpt(int[] nums) {
        // 如果nums[i]偷，就意味着在i-1时不能偷，则此时的最大值就=dp[i-2]+nums[i]
        // 如果nums[i-1]偷，则nums[i] = nums[i-1]
        // 因此，dp[i] = max(dp[i-2] + nums[i], dp[i-1])
        // 又因为dp[i]只和dp[i-1]和dp[i-2]有关，可以优化空间
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int p = 0; // temp
        int q = nums[0]; // dp[0]
        int r = Math.max(nums[0], nums[1]); // dp[1]
        for (int i = 2; i < n; i++) {
            p = q; // dp[i-2]
            q = r; // dp[i-1]
            r = Math.max(p + nums[i], q);
        }
        return r;
    }

    public int robDP2D(int[] nums) {
        // dp[i][j]: 在当前房屋能获得的最高金额, dp[i][1]取当前的, dp[i][0]不偷
        // dp[i][1] = dp[i - 1][0] + nums[i], dp[i][0] = max(dp[i - 1][1], dp[i - 1][0])
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][1] = dp[i - 1][0] + nums[i];
            // 不偷的最高金额是上次偷 or 不偷中高的那个
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][0]);
    }
}
