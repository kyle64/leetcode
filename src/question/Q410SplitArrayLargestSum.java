package question;

/**
 * Created by ziheng on 2020/7/25.
 */
public class Q410SplitArrayLargestSum {
    /**
     * @Description:
     *
     * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
     *
     * 注意:
     * 数组长度 n 满足以下条件:
     *
     * 1 ≤ n ≤ 1000
     * 1 ≤ m ≤ min(50, n)
     * 示例:
     *
     * 输入:
     * nums = [7,2,5,10,8]
     * m = 2
     *
     * 输出:
     * 18
     *
     * 解释:
     * 一共有四种方法将nums分割为2个子数组。
     * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
     * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/split-array-largest-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/25 下午3:58
     * @param nums
     * @param m
     * @return int
     */
    public int splitArray(int[] nums, int m) {
        // 动态规划
        // dp[i][j]表示到nums[i]为止，分割为j个数组时最大数组和的最小值
        int[][] dp = new int[nums.length][m];
        dp[0][0] = 0;
        int sum = 0;

        for (int j = 1; j <= m; j++) {
            for (int i = m; i < nums.length; i++) {
                int currentSum = Integer.MAX_VALUE;
                for (int k = j - 1; k < i; k++) {
                    int left = dp[k][j];
                    int right = 0;
                    for (int l = k; l < i; l++) {
                        right += nums[l];
                    }
                    currentSum = Math.min(currentSum, Math.max(left, right));
                }

                dp[i][j] = currentSum;
            }
        }
        // TODO: 2020/7/25 修改边界

        return dp[nums.length - 1][m];
    }
}
