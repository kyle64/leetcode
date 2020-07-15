package question;

/**
 * Created by ziheng on 2020/7/15.
 */
public class Q33search {
    /**
     * @Description:
     *
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 你可以假设数组中不存在重复的元素。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 示例 1:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/15 下午5:00
     * @param nums
     * @param target
     * @return int
     */
    public static int search(int[] nums, int target) {
        if (nums.length == 0) return -1;

        // 由于要求O(logN)的时间复杂度，所以不可以遍历比较
        // 使用二分查找，如果不是升序的，则找到；不然就舍弃升序的部分，继续找
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2; // 闭区间，奇数个元素mid在左。[left, mid] [mid + 1, right]
            if (nums[mid] == target) return mid;
            // 从left到mid单调递增，处理只剩两个元素的情况
            if (nums[left] <= nums[mid]) {
                if (target <= nums[mid] && target >= nums[left]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            // 从mid到right单调递增，处理只剩两个元素的情况
            else if (nums[mid] <= nums[right]) {
                if (target <= nums[right] && target >= nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }

        return target == nums[left] ? left : -1;
    }

    public static void main(String[] args) {
        int[] input = {3, 1};
        int target = 1;

        System.out.println(search(input, target));
    }
}
