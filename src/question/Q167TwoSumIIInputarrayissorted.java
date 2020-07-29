package question;

/**
 * Created by ziheng on 2020/7/30.
 */
public class Q167TwoSumIIInputarrayissorted {
    /**
     * @Description: 167. 两数之和 II - 输入有序数组
     *
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     *
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     *
     * 说明:
     *
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     * 示例:
     *
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/30 上午2:28
     * @param numbers
     * @param target
     * @return int[]
     */
    public int[] twoSum(int[] numbers, int target) {
        // 升序数组，双指针
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                // 找到
                return new int[] {left + 1, right + 1};
            } else if (numbers[left] + numbers[right] > target) {
                // 升序数组，大于target，一定是right太大了（更小的left已经查找过了）
                right--;
            } else {
                // 升序数组，小于target，一定是left太小了
                left++;
            }
        }

        return new int[]{-1, -1};
    }
}
