package question;

/**
 * Created by ziheng on 2020/7/21.
 */
public class Q189RotateArray {

    /**
     * @Description: 189. 旋转数组
     *
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     *
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * 说明:
     *
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 要求使用空间复杂度为 O(1) 的 原地 算法。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/21 上午2:16
     * @param nums
     * @param k
     * @return void
     */

    // 每次从被替代的k开始向后移动，如果回到了开始的位置，i++，然后继续迭代
    public void rotate(int[] nums, int k) {
        int count = 0;
        for (int i = 0; count < nums.length; i++) {
            int curr = i; int prev = nums[i];
            do {
                curr = (curr + k) % nums.length;
                int nextVal = nums[curr];
                nums[curr] = prev;
                prev = nextVal;
                count++;
            } while (curr != i);
        }
    }

    // 使用额外的数组空间
    public void rotate2(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }

    // 旋转数组，超时
    public void rotate1(int[] nums, int k) {
        if (nums.length == 0) return;

        for (int j = 0; j < k; j++) {
            int prev = nums[0];
            int current;
            for (int i = 0; i < nums.length; i++) {
                current = nums[(i + 1) % nums.length];
                nums[(i + 1) % nums.length] = prev;
                prev = current;
            }
        }
    }
}
