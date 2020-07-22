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

    public static void main(String[] args) {
        int[] inputs = {4,5,6,7,0,1,2};
        System.out.println(findMin(inputs));
    }
}
