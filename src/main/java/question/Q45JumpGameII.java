/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.Arrays;

/**
 * @author wuziheng
 * @description
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 *
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * 题目保证可以到达 nums[n-1]
 *
 * @date 2025/5/14 01:32
 **/
public class Q45JumpGameII {

    public int jump(int[] nums) {
        // 贪心
        int n = nums.length;
        int res = 0;
        int nextRight = 0;
        int curRight = 0;

        // 当 i=n−2 时，如果 i<curRight，说明可以到达 n−1；如果 i=curRight，我们会造桥，这样也可以到达 n−1。所以无论是何种情况，都只需要遍历到 n−2
        // 因为是已经遍历到i 且 i = curRight时才会考虑更远的地方并增加次数
        // f[n - 1]的次数，在第一次nextRight = i+nums[i] >= n-1时已经包括了
        for (int i = 0; i < n - 1; i++) {
            nextRight = Math.max(nextRight, i + nums[i]);
            if (i == curRight) {
                // 已经在上一次跳的最远的范围内了，必须增加次数
                curRight = nextRight;
                res++;
            }
        }
        return res;
    }

    public int jumpDP(int[] nums) {
        // dp[i]表示到达nums[i]的最小跳跃次数
        // dp[i] = if j + nums[j] >= i, min(dp[j]) + 1 where j in [0, i-1]
        int n = nums.length;
        int[] jumps = new int[n];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    jumps[i] = Math.min(jumps[j] + 1, jumps[i]);
                }
            }
        }
        return jumps[n - 1];
    }

    public int jumpDPOPT(int[] nums) {
        // dp + 双指针 + 贪心优化
        // dp[i]表示到达nums[i]的最小跳跃次数
        // dp[i] = if j + nums[j] >= i, min(dp[j]) + 1 where j in [0, i-1]
        int n = nums.length;
        int[] jumps = new int[n];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;

        for (int i = 1, j = 0; i < n; i++) {
            // 我们需要找到最早能够经过一步到达 i 点的 j 点。f[i]=f[j]+1
            // 其实此时一定有j < i
            while (j + nums[j] < i) {
                j++;
            }
            jumps[i] = jumps[j] + 1;
        }
        return jumps[n - 1];
    }
}
