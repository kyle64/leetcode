package question;

/**
 * Created by ziheng on 2020/7/15.
 */
public class Q11maxArea {
    /**
     * @Description: 11. 盛最多水的容器
     *
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/15 上午3:58
     * @param
     * @return int
     */
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int area = Integer.MIN_VALUE;

        while (left < right) {
            // 比较记录当前最大面积
            if (Math.min(height[left], height[right]) * (right - left) > area) {
                area = Math.min(height[left], height[right]) * (right - left);
            }

            // 移动小的指针
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return area;
    }

    public static void main(String[] args) {
        int[] input = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(input));
    }
}
