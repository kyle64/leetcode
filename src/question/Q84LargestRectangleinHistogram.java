package question;

import java.util.Stack;

/**
 * Created by ziheng on 2020/8/1.
 */
public class Q84LargestRectangleinHistogram {
    /**
     * @Description: 84. 柱状图中最大的矩形
     *
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     *
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     *  
     *
     *
     *
     * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
     *
     *  
     *
     *
     *
     * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
     *
     *  
     *
     * 示例:
     *
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/8/1 上午2:41
     * @param
     * @return int
     */
    public int largestRectangleArea(int[] heights) {
        // 单调栈 O(N)

        int length = heights.length;
        // 每个height[i]往左找到的第一个比他小的位置的下标
        int[] left = new int[length];
        // 每个height[i]往右找到的第一个比他小的位置的下标
        int[] right = new int[length];
        Stack<Integer> stack = new Stack<>();

        // 从左往右遍历
        for (int i = 0; i < length; i++) {
            // 如果左侧，即栈中有大于peek的柱子，出栈
            // 只要有比height[i]大的就行，因为更大的已经出栈
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            // 左边元素都大于height[i]，那么第一个小于height[i]的位置为-1
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        // 清空stack
        stack.clear();

        // 从右往左遍历
        for (int i = length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // 右边元素都大于height[i]，那么第一个小于height[i]的位置为length
            left[i] = stack.isEmpty() ? length : stack.peek();
            stack.push(i);
        }

        // 遍历比较每个i的左右第一个小于height[i]的差，即：以height[i]为高的矩阵
        int ans = 0;
        for (int i = 0; i < length; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    // 暴力，O(N^2)
    public int largestRectangleArea1(int[] heights) {
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                area = Math.max(area, (j - i + 1) * minHeight);
            }
        }

        return area;
    }
}
