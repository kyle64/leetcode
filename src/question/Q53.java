package question;

/**
 * Created by ziheng on 2020/7/11.
 */
public class Q53 {
    /**
     * @Description: 53. 最大子序和
     *
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:
     *
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/11 上午3:30
     * @param
     * @return int
     */

    public int maxSubArrayDP(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i- 1] + nums[i], nums[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        int left = 0, right = 0;
        int maxSum = nums[0], sum = 0;
        while (right < nums.length) {
            if (sum < 0) {
                sum = nums[right];
            } else {
                sum += nums[right];
            }
            maxSum = Math.max(maxSum, sum);
            right++;
        }

        return maxSum;
    }
}
