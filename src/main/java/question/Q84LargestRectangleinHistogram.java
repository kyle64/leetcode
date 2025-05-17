package question;

import java.util.ArrayDeque;
import java.util.Deque;
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

    public int largestRectangleArea2(int[] heights) {
        int res = Integer.MIN_VALUE;
        int n = heights.length;
        // 对于i，如果heights[j] > heights[i]，则矩形高度依旧为heights[i]; 但如果heights[j] < heights[i], 就能够确定heights[i]的最大宽度
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 严格小于heights[stk.peek()]才可以
            while (!stk.isEmpty() && heights[i] < heights[stk.peek()]) {
                // 此时heights[stk.peek()]的最大宽度的右边界已确定是i
                // 寻找第一次出现heights[stk.peek()]的下标，因为可能高度相同
                int idx = stk.pop();
                while (!stk.isEmpty() && heights[idx] == heights[stk.peek()]) {
                    idx = stk.pop();
                }
                // 组成最大的矩形面积那就是要找左边第一个小于当前高度的下标left，
                // 再找右边第一个小于当前高度的下标right 那宽度就是这两个下标之间的距离了
                // 但是要排除这两个下标 所以是right - left - 1
                int width = stk.isEmpty() ? i : i - stk.peek() - 1;
                res = Math.max(res, heights[idx] * width);
            }
            stk.push(i);
        }
        // 处理栈中的剩余元素
        while (!stk.isEmpty()) {
            int height = heights[stk.pop()];
            // 处理heights[idx]相同的元素
            while (!stk.isEmpty() && height == heights[stk.peek()]) {
                stk.pop();
            }

            int width = stk.isEmpty() ? n : n - stk.peek() - 1;

            res = Math.max(res, height * width);
        }
        return res;
    }

    public int largestRectangleArea3(int[] heights) {
        int res = Integer.MIN_VALUE;
        int n = heights.length;
        // 对于i，如果heights[j] > heights[i]，则矩形高度依旧为heights[i]; 但如果heights[j] < heights[i], 就能够确定heights[i]的最大宽度
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 寻找左侧第一个小于heights[stk.peek()]的下标
            // 等于时也出栈，因为当前元素压栈后又会再次判断
            while (!stk.isEmpty() && heights[i] <= heights[stk.peek()]) {
                // 此时heights[stk.peek()]的最大宽度的右边界已确定是i
                // 寻找第一次出现heights[stk.peek()]的下标，因为可能高度相同
                int idx = stk.pop();
                int width = stk.isEmpty() ? i : i - stk.peek() - 1;
                res = Math.max(res, heights[idx] * width);
            }
            stk.push(i);
        }
        // 处理栈中的剩余元素
        while (!stk.isEmpty()) {
            int height = heights[stk.pop()];
            int width = stk.isEmpty() ? n : n - stk.peek() - 1;
            res = Math.max(res, height * width);
        }
        return res;
    }

    public int largestRectangleAreaSentinel(int[] heights) {
        // 哨兵优化
        // 针对两种特殊情况处理：
        // 1。 弹栈的时候，栈为空；
        // 2。 遍历完成以后，栈中还有元素
        // 在两头各加一个哨兵，都比所有元素小
        int res = 0;
        int len = heights.length;
        int newLen = len + 2;
        int[] newHeights = new int[newLen];
        // 原heights拷贝到newHeights[1:n]
        System.arraycopy(heights, 0, newHeights, 1, len);
        Deque<Integer> stk = new ArrayDeque<>();
        // 初始化两头的柱子
        newHeights[0] = newHeights[newLen - 1] = 0;
        stk.push(0);
        for (int i = 1; i < newLen; i++) {
            while (newHeights[i] < newHeights[stk.peek()]) {
                int idx = stk.pop();
                int curHeight = newHeights[idx];
                int curWidth = i - stk.peek() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stk.push(i);
        }
        return res;
    }
}
