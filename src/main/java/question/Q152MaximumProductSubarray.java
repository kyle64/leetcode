/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

/**
 * @author wuziheng
 * @description 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 测试用例的答案是一个 32-位 整数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * nums 的任何子数组的乘积都 保证 是一个 32-位 整数
 *
 * @date 2025/5/15 15:48
 **/
public class Q152MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        // 贪心 + 数学
        // 一个不包含0的整数序列的连续乘积最大值，一定以起点开始或者以终点结束。
        // 包含0则一定是数组两端 or 不包含0的两端
        // 因此，只需要从两侧分别遍历，计算前缀积即可（nums[i] = 0时重置pre product）

        // 反证：如果不包含端点，中间乘积max，
        // 如果max > 0, 说明两侧异号，则可以向一侧延伸; 矛盾
        // 如果max < 0, 则说明两侧同正/同负，无论哪个都会有>0的更大值；矛盾
        int n = nums.length;
        // init
        int res = nums[0];
        int product = 1;

        // 从前往后
        for (int i = 0; i < n; i++) {
            product *= nums[i];
            res = Math.max(res, product);
            if (nums[i] == 0) {
                product = 1;
            }
        }

        product = 1;

        // 反向遍历后缀积，避免[-,+,+,...,+]这种场景在前缀积场景被忽略
        // 从后往前
        for (int i = n - 1; i >= 0; i--) {
            product *= nums[i];
            res = Math.max(res, product);
            if (nums[i] == 0) {
                product = 1;
            }
        }

        return res;
    }

    public int maxProductDPOpt(int[] nums) {
        // dp[i]: 包含nums[i]的最大乘积
        // 如果都是非负整数，则dp[i] = max(dp[i-1] * nums[i], nums[i])
        // 但由于负数存在，导致最大值可能变为最小值，最小值会变为最大值，因此需要同时维护最大/最小两个dp
        // dpmax[i]和dpmin[i]只和i-1有关，可以用变量代替，优化空间
        int n = nums.length;
        // 包含nums[i]的最大乘积
        int curMax;
        // 包含nums[i]的最小乘积
        int curMin;
        // init
        int res = nums[0];
        curMax = nums[0];
        curMin = nums[0];

        // 负的时候负的更多，所以同时维护最小值
        for (int i = 1; i < n; i++) {
            // 优化一下，直接在dpmax[i - 1] * nums[i], dpmax[i - 1] * nums[i], nums[i]中更新
            // 滚动数组优化
            int temp = curMax; // dpmax[i - 1]
            curMax = Math.max(nums[i], Math.max(temp * nums[i], curMin * nums[i]));
            curMin =  Math.min(nums[i], Math.min(temp * nums[i], curMin * nums[i]));

            res = Math.max(res, curMax);
        }
        return res;
    }

    public int maxProductDP(int[] nums) {
        // dp[i]: 包含nums[i]的最大乘积
        // 如果都是非负整数，则dp[i] = max(dp[i-1] * nums[i], nums[i])
        // 但由于负数存在，导致最大值可能变为最小值，最小值会变为最大值，因此需要同时维护最大/最小两个dp
        int n = nums.length;
        // 包含nums[i]的最大乘积
        int[] dpmax = new int[n];
        // 包含nums[i]的最小乘积
        int[] dpmin = new int[n];
        // init
        int res = nums[0];
        dpmax[0] = nums[0];
        dpmin[0] = nums[0];

        // 负的时候负的更多，所以同时维护最小值
        for (int i = 1; i < n; i++) {
            // 优化一下，直接在dpmax[i - 1] * nums[i], dpmax[i - 1] * nums[i], nums[i]中更新
            dpmax[i] = Math.max(nums[i], Math.max(dpmax[i - 1] * nums[i], dpmin[i - 1] * nums[i]));
            dpmin[i] = Math.min(nums[i], Math.min(dpmax[i - 1] * nums[i], dpmin[i - 1] * nums[i]));
            res = Math.max(res, Math.max(dpmax[i], dpmin[i]));
        }
        return res;
    }

    public int maxProductDP1(int[] nums) {
        // dp[i]: 包含nums[i]的最大乘积
        // 如果都是非负整数，则dp[i] = max(dp[i-1] * nums[i], nums[i])
        // 但由于负数存在，导致最大值可能变为最小值，最小值会变为最大值，因此需要同时维护最大/最小两个dp
        int n = nums.length;
        // 包含nums[i]的最大乘积
        int[] dpmax = new int[n];
        // 包含nums[i]的最小乘积
        int[] dpmin = new int[n];
        // init
        int res = nums[0];
        dpmax[0] = nums[0];
        dpmin[0] = nums[0];

        // 负的时候负的更多，所以同时维护最小值
        for (int i = 1; i < n; i++) {
            if (nums[i] >= 0) {
                dpmax[i] = Math.max(nums[i], dpmax[i - 1] * nums[i]);
                dpmin[i] = Math.min(nums[i], dpmin[i - 1] * nums[i]);
            } else {
                dpmax[i] = Math.max(nums[i], dpmin[i - 1] * nums[i]);
                dpmin[i] = Math.min(nums[i], dpmax[i - 1] * nums[i]);
            }
            res = Math.max(res, Math.max(dpmax[i], dpmin[i]));
        }
        return res;
    }
}
