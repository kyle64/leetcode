package question;

/**
 * Created by ziheng on 2020/7/11.
 */
public class Q53MaximumSubarray {
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

    //方法二：分治
    //思路和算法
    //
    //这个分治方法类似于「线段树求解 LCIS 问题」的 pushUp 操作。 也许读者还没有接触过线段树，没有关系，方法二的内容假设你没有任何线段树的基础。当然，如果读者有兴趣的话，推荐看一看线段树区间合并法解决 多次询问 的「区间最长连续上升序列问题」和「区间最大子段和问题」，还是非常有趣的。
    //
    //我们定义一个操作 get(a, l, r) 表示查询 aa 序列 [l, r][l,r] 区间内的最大子段和，那么最终我们要求的答案就是 get(nums, 0, nums.size() - 1)。如何分治实现这个操作呢？对于一个区间 [l, r][l,r]，我们取 m = \lfloor \frac{l + r}{2} \rfloorm=⌊
    //2
    //l+r
    //​
    // ⌋，对区间 [l, m][l,m] 和 [m + 1, r][m+1,r] 分治求解。当递归逐层深入直到区间长度缩小为 11 的时候，递归「开始回升」。这个时候我们考虑如何通过 [l, m][l,m] 区间的信息和 [m + 1, r][m+1,r] 区间的信息合并成区间 [l, r][l,r] 的信息。最关键的两个问题是：
    //
    //我们要维护区间的哪些信息呢？
    //我们如何合并这些信息呢？
    //对于一个区间 [l, r][l,r]，我们可以维护四个量：
    //
    //lSum 表示 [l, r][l,r] 内以 ll 为左端点的最大子段和
    //rSum 表示 [l, r][l,r] 内以 rr 为右端点的最大子段和
    //mSum 表示 [l, r][l,r] 内的最大子段和
    //iSum 表示 [l, r][l,r] 的区间和
    //以下简称 [l, m][l,m] 为 [l, r][l,r] 的「左子区间」，[m + 1, r][m+1,r] 为 [l, r][l,r] 的「右子区间」。我们考虑如何维护这些量呢（如何通过左右子区间的信息合并得到 [l, r][l,r] 的信息）？对于长度为 11 的区间 [i, i][i,i]，四个量的值都和 a_ia
    //i
    //​
    //  相等。对于长度大于 11 的区间：
    //
    //首先最好维护的是 iSum，区间 [l, r][l,r] 的 iSum 就等于「左子区间」的 iSum 加上「右子区间」的 iSum。
    //对于 [l, r][l,r] 的 lSum，存在两种可能，它要么等于「左子区间」的 lSum，要么等于「左子区间」的 iSum 加上「右子区间」的 lSum，二者取大。
    //对于 [l, r][l,r] 的 rSum，同理，它要么等于「右子区间」的 rSum，要么等于「右子区间」的 iSum 加上「左子区间」的 rSum，二者取大。
    //当计算好上面的三个量之后，就很好计算 [l, r][l,r] 的 mSum 了。我们可以考虑 [l, r][l,r] 的 mSum 对应的区间是否跨越 mm——它可能不跨越 mm，也就是说 [l, r][l,r] 的 mSum 可能是「左子区间」的 mSum 和 「右子区间」的 mSum 中的一个；它也可能跨越 mm，可能是「左子区间」的 rSum 和 「右子区间」的 lSum 求和。三者取大。
    //这样问题就得到了解决。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    class Status {
        int lSum; // 以 l 为左端点的最大子段和
        int rSum; // 以 r 为右端点的最大子段和
        int mSum; // [l,r] 内的最大子段和
        int iSum; // [l,r] 的区间和

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    // 官方，分治（线段树）
    public int maxSubArrayDivide(int[] nums) {
        return get(nums, 0, nums.length - 1).mSum;
    }

    private Status get(int[] nums, int l, int r) {
        if (l == r) return new Status(nums[l], nums[l], nums[l], nums[l]);

        int m = (l + r) >> 1;
        // 分治
        Status lSub = get(nums, l, m);
        Status rSub = get(nums, m + 1, r);
        return pushUp(lSub, rSub);
    }

    private Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    public int maxSubArrayDPOpt(int[] nums) {
        // 动态规划，dp[i] 表示下标i结尾的最大连续子数组和
        // dp[i] = dp[i - 1] < 0 ? nums[i] : dp[i - 1] + nums[i]
        // dp[i]只和dp[i - 1]有关，空间优化

        int dp = nums[0];
        int res = dp;

        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp, 0) + nums[i];
            res = Math.max(res, dp);
        }
        return res;
    }

    public int maxSubArrayDPArray(int[] nums) {
        // 动态规划，dp[i] 表示下标i结尾的最大连续子数组和
        // dp[i] = dp[i - 1] < 0 ? nums[i] : dp[i - 1] + nums[i]

        int res = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] < 0 ? nums[i] : dp[i - 1] + nums[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int maxSubArrayPreSumOpt(int[] nums) {
        // 对于下标i的位置上，最大的总和为前缀和减去之前的前缀和的最小值
        // maxSum(i) = preSum(i) - min(preSum(0, i - 1))
        // 前缀和方式优化

        // init
        int res = Integer.MIN_VALUE;
        int minPreSum = 0; // 初始化为0，以保证可以只选nums[i]
        int preSum = 0;
        for (int num : nums) {
            preSum += num;
            res = Math.max(res, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
        }
        return res;
    }

    public int maxSubArrayPreSum(int[] nums) {
        // 对于下标i的位置上，最大的总和为前缀和减去之前的前缀和的最小值 or 0
        // maxSum(i) = preSum(i) - min(preSum(0, i - 1))

        // init prefix sum array, 初始化前缀和数组
        int[] preSums = new int[nums.length];
        preSums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSums[i] = preSums[i - 1] + nums[i];
        }

        int res, curMinPreSum;
        res = curMinPreSum = preSums[0];
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, preSums[i] - Math.min(0, curMinPreSum));
            curMinPreSum = Math.min(curMinPreSum, preSums[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new Q53MaximumSubarray().maxSubArray(nums));
    }
}
