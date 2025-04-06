package question;

/**
 * Created by ziheng on 2020/7/28.
 */
public class Q42TrappingRainWater {
    /**
     * @Description: 42. 接雨水
     *
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     *
     *
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     *
     * 示例:
     *
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/28 下午11:57
     * @param
     * @return int
     */
    public static int trap(int[] height) {
        if (height.length < 3) return 0;
        int result = 0;
        // 两个一维dp数组，分别存储height[i]所处的leftMax和rightMax
        // O(N)
        int length = height.length;
        int[] dpMaxLeft = new int[length];
        int[] dpMaxRight = new int[length];

        // 初始化
        dpMaxLeft[0] = height[0];
        dpMaxRight[length - 1] = height[length - 1];

        for (int i = 1; i < length - 1; i++) {
            // dp[i]等于左边的最高点和当前高度的较大值
            dpMaxLeft[i] = Math.max(dpMaxLeft[i - 1], height[i]);
        }

        for (int i = length - 2; i >= 0 ; i--) {
            // dp[i]等于右边的最高点和当前高度的较大值
            dpMaxRight[i] = Math.max(dpMaxRight[i + 1], height[i]);
        }

        for (int i = 1; i < length - 1; i++) {
            result += (Math.min(dpMaxLeft[i], dpMaxRight[i]) - height[i]);
        }

        return result;
    }

    // 暴力解
    public static int trapBrutalForce(int[] height) {
        // 对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值
        // 每个元素i，都往左扫描找最大值，再往右扫描找最大值，min(maxLeft, maxRight) - height[i]就是这个位置的雨水高度
        if (height.length < 3) return 0;
        int result = 0;

        for (int i = 1; i < height.length - 1; i++) {
            int maxLeft = 0;
            int maxRight = 0;

            // 左边最高的柱子(包括当前i，否则如果height[i]是最高的，那么会产生负数)
            for (int j = 0; j <= i; j++) {
                maxLeft = Math.max(maxLeft, height[j]);
            }

            // 右边最高的柱子(包括当前i，否则如果height[i]是最高的，那么会产生负数)
            for (int j = i; j < height.length; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }

            // 两端较小值 - height[i]就是积水高度
            result += (Math.min(maxLeft, maxRight) - height[i]);
        }

        return result;
    }


    // 错误解法
    public static int trap1(int[] height) {
        if (height.length < 3) return 0;

        int left = 0, right = 1;
        int result = 0;
        int lsum = 0; // 当前left到right所存储的水

        while (height[left] == 0) {
            right++;
            left++;
        }

        while (right < height.length) {
            // 右边柱子比前一个高，才可能接住水
            if (height[right] >= height[right - 1]) {
                int vol = 0;
                // 左右低的柱子作为height
                int trapHeight = Math.min(height[left], height[right]);
                for (int i = left; i < right; i++) {
                    // height[i]小于当前区间的高度，接水
                    if (height[i] < trapHeight) {
                        vol += (trapHeight - height[i]);
                    }
                }
                lsum = Math.max(lsum, vol);

                if (height[right] >= height[left]) {
                    result += lsum;
                    lsum = 0;
                    left = right;
                }
            }
            right++;
        }

        result += lsum;
        return result;
    }

    public static void main(String[] args) {
//        int[] input = {9,6,8,8,5,6,3};
        int[] input = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(input));
    }
}
