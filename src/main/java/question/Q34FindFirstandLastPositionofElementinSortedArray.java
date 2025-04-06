package question;

/**
 * Created by ziheng on 2020/7/24.
 */
public class Q34FindFirstandLastPositionofElementinSortedArray {

    /**
     * @Description: 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 如果数组中不存在目标值，返回 [-1, -1]。
     *
     * 示例 1:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/24 上午12:59
     * @param nums
     * @param target
     * @return int[]
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums.length == 0) return result;
        result[0] = searchLeftBound(nums, 0, nums.length - 1, target);
        // 必须是nums.length做比较，不然无法比较left = right = mid的情况，如只有一个数字的时候
        result[1] = searchRightBound(nums, 0, nums.length, target);

        return result;
    }

    private static int searchLeftBound(int[] nums, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                // 就算等于，也要缩小右边界
                right = mid;
            }
        }

        // 最后一次left = right左边界值可能未比较，需要再判断
        return nums[left] == target ? left : -1;
    }

    private static int searchRightBound(int[] nums, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                left = mid + 1;
            }
        }

        //
        return left >= 1 && nums[left - 1] == target ? left - 1 : -1;
    }

    public static void main(String[] args) {
        int[] input = {1};
        int target = 1;

//        int[] result = searchRange(input, target);
//        for (int i : result) {
//            System.out.print(i + " ");
//        }

        System.out.println(searchLeftBound(input, 0, input.length, target));
        System.out.println(searchRightBound(input, 0, input.length, target));
    }
}
