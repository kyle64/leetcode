package question;

import java.util.Arrays;

/**
 * Created by ziheng on 2020/7/25.
 */
public class Q410SplitArrayLargestSum {
    /**
     * @param nums
     * @param m
     * @return int
     * @Description: 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
     * <p>
     * 注意:
     * 数组长度 n 满足以下条件:
     * <p>
     * 1 ≤ n ≤ 1000
     * 1 ≤ m ≤ min(50, n)
     * 示例:
     * <p>
     * 输入:
     * nums = [7,2,5,10,8]
     * m = 2
     * <p>
     * 输出:
     * 18
     * <p>
     * 解释:
     * 一共有四种方法将nums分割为2个子数组。
     * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
     * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/split-array-largest-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @date 2020/7/25 下午3:58
     */

    // 官方。二分 + 贪心
    // 思路及算法
    //
    // 「使……最大值尽可能小」是二分搜索题目常见的问法。
    //
    // 本题中，我们注意到：当我们选定一个值 xx，我们可以线性地验证是否存在一种分割方案，满足其最大分割子数组和不超过 xx。策略如下：
    //
    // 贪心地模拟分割的过程，从前到后遍历数组，用 \textit{sum}sum 表示当前分割子数组的和，\textit{cnt}cnt 表示已经分割出的子数组的数量（包括当前子数组），那么每当 \textit{sum}sum 加上当前值超过了 xx，我们就把当前取的值作为新的一段分割子数组的开头，并将 \textit{cnt}cnt 加 11。遍历结束后验证是否 \textit{cnt}cnt 不超过 mm。
    //
    // 这样我们可以用二分查找来解决。二分的上界为数组 \textit{nums}nums 中所有元素的和，下界为数组 \textit{nums}nums 中所有元素的最大值。通过二分查找，我们可以得到最小的最大分割子数组和，这样就可以得到最终的答案了。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/split-array-largest-sum/solution/fen-ge-shu-zu-de-zui-da-zhi-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public static int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        // 初始化上下边界，上边界是数组和，下边界是最大值
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
            if (left < nums[i]) {
                left = nums[i];
            }
        }

        while (left < right) {
            int mid = (right - left) / 2 + left;
            // 如果 cnt>m，说明划分的子数组多了，即我们找到的 mid 偏小，故 l=mid+1l=mid+1；
            // 否则，说明划分的子数组少了，即 mid 偏大(或者正好就是目标值)，故 h=midh=mid。
            //
            // 作者：coder233
            // 链接：https://leetcode-cn.com/problems/split-array-largest-sum/solution/er-fen-cha-zhao-by-coder233-2/
            // 来源：力扣（LeetCode）
            // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static boolean check(int[] nums, int x, int m) {
        int count = 1;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > x) {
                // 超过x，重置
                sum = nums[i];
                count++;
            } else {
                // 累加
                sum += nums[i];
            }
        }

        // 划分的子数组数量是否小于等于m，
        // 如果多了，则说明x找的小了，left = mid + 1
        // 如果少了／等于，则说明x找的大了／正好，right = mid
        return count <= m;
    }


    public static int splitArrayDP(int[] nums, int m) {
        // 官方
        // f[i][j] 表示将数组的前 i 个数分割为 jj 段所能得到的最大连续子数组和的最小值
        // 在进行状态转移时，我们可以考虑第 jj 段的具体范围，即我们可以枚举 kk，其中前 kk 个数被分割为 j-1j−1 段，而第 k+1k+1 到第 ii 个数为第 jj 段。此时，这 jj 段子数组中和的最大值，就等于 f[k][j-1]f[k][j−1] 与 \textit{sub}(k+1, i)sub(k+1,i) 中的较大值，其中 \textit{sub}(i,j)sub(i,j) 表示数组 \textit{nums}nums 中下标落在区间 [i,j][i,j] 内的数的和。
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }

        // 用一个sub数组表示当前长度的和，sub[1] = nums[0]
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }


    public static int splitArrayDP1(int[] nums, int m) {
        // 动态规划
        // dp[i][j]表示到nums[i]为止，分割为j个数组时最大数组和的最小值
        int[][] dp = new int[nums.length + 1][m + 1];

        for (int i = 0; i <= nums.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }


        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][1] = dp[i - 1][1] + nums[i];
        }

        if (m == 1) return dp[nums.length - 1][1];

        for (int i = 1; i < nums.length; i++) {
            for (int j = 2; j <= m; j++) {
                int currentSum = Integer.MAX_VALUE;
                for (int k = 0; k < i; k++) {
                    int left = dp[k][j - 1];
                    int right = 0;
                    for (int l = k + 1; l <= i; l++) {
                        right += nums[l];
                    }
                    currentSum = Math.min(currentSum, Math.max(left, right));
                }

                dp[i][j] = currentSum;
            }
        }

        return dp[nums.length - 1][m];
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;

        System.out.println(splitArray(nums, m));
    }
}
