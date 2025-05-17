package question;

/**
 * Created by ziheng on 2020/7/22.
 */
public class Q153FindMinimuminRotatedSortedArray {
    /**
     * @Description: 153. 寻找旋转排序数组中的最小值
     *
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 请找出其中最小的元素。
     *
     * 你可以假设数组中不存在重复元素。
     *
     * 示例 1:
     *
     * 输入: [3,4,5,1,2]
     * 输出: 1
     * 示例 2:
     *
     * 输入: [4,5,6,7,0,1,2]
     * 输出: 0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/22 上午10:49
     * @param
     * @return int
     */
    public static int findMin(int[] nums) {
        // 二分
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            // 由于是升序数组，所以只要发生了旋转，那么旋转之后最小值后面的数都小于nums[0]

            // 左边都是升序，旋转点一定在右边，移动左边界
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }


    public int findMin1(int[] nums) {
        // O(N)
        if (nums.length == 0) return -1;
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return nums[i];
            }
        }

        return res;
    }

    public int findMinCompareLeft(int[] nums) {
        int res = Integer.MAX_VALUE;
        // 数组内一定有一段有序的子数组
        int n = nums.length;
        int left = 0, right = n - 1;
        // 找最小值，一定是向左压缩
        while (left <= right) {
            // mid偏左，导致left + 1 = right时，会出现left = mid，此时left已经判断过，需要再次判断right是否为最小值
            int mid = left + (right - left) / 2;
            // nums[left, mid]升序
            if (nums[left] <= nums[mid]) {
                res = Math.min(res, nums[left]);
                left = mid + 1;
            } else {
                res = Math.min(res, nums[mid]);
                // 意味着旋转的pivot在[left, mid]之间, nums[mid + 1, right]有序
                right = mid - 1;
            }
        }

        return res;
    }

    public int findCompareLeft(int[] nums) {
        int res = Integer.MAX_VALUE;
        // 数组内一定有一段有序的子数组
        int n = nums.length;
        int left = 0, right = n - 1;

        // 1. nums[left] < nums[mid] < nums[right]
        // 没有旋转，左右区间都递增，最小值在左侧区间，收缩右边界
        // 2. nums[left] < nums[mid] > nums[right], nums[right] < nums[left]
        // 即：nums[right] < nums[left] < nums[mid]
        // 旋转点在右侧，左区间递增，最小值在右侧区间，收缩左边界
        // 3. nums[left] > nums[mid] < nums[right], nums[right] < nums[left]
        // 即：nums[mid] < nums[right] < nums[left]
        // 旋转点在左侧，右区间递增，最小值在左侧区间，此时可能包含mid，收缩右边界
        // 因此，只需要比较nums[mid]和nums[right]就能舍弃一半的区间

        // 找最小值，一定是向左压缩
        while (left < right) {
            // mid偏左，mid始终 < right，最终两个数的话left == mid == right - 1
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else { // else if (nums[mid] > nums[right])
                left = mid + 1;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        int[] inputs = {4,5,6,7,0,1,2};
        System.out.println(findMin(inputs));
    }
}
