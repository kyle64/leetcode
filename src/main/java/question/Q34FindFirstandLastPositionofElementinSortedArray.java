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

    public int[] searchRange1(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        int n = nums.length;
        int left = 0, right = n;

        // looking for left
        // 左开右闭
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 范围缩小到 [mid+1, right)
            } else {
                // 等于target时也继续往左收拢右边界
                right = mid; // 范围缩小到 [left, mid)
            }
        }
        // 退出循环时，left = right;
        // 如果存在目标，则nums[left] >= target且nums[left - 1] < target
        // left最终值可能是[0, n], left = n意味着所有元素都小于target，即：nums[n - 1] < target;
        // left = 0意味着所有元素都大于等于target，即：nums[0] >= target, 直接判断nums[left]即可
        // 总结：left就是第一个 >= target 的元素下标
        if (left == n || nums[left] != target) {
            return res;
        }

        res[0] = left;

        left = 0;
        right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                // 等于target时也继续往右收拢左边界
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 同理，left = 0时意味着所有元素都 > target
        // 同上：left就是第一个 > target 的元素下标，因此判断nums[left - 1] == target
        if (left > 0 && nums[left - 1] == target) {
            res[1] = left;
        }

        return res;
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
