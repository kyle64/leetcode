package question;

import java.util.Arrays;

/**
 * Created by ziheng on 2020/7/15.
 */
public class Q16threeSumClosest {
    /**
     * @Description: 16. 最接近的三数之和
     *
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     *  
     *
     * 示例：
     *
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     *  
     *
     * 提示：
     *
     * 3 <= nums.length <= 10^3
     * -10^3 <= nums[i] <= 10^3
     * -10^4 <= target <= 10^4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/15 上午11:33
     * @param nums
     * @param target
     * @return int
     */
    public static int threeSumClosest(int[] nums, int target) {
        int sum = nums[0] + nums[1] + nums[2], currentSum;
        int left, right;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 更新target，变成寻找两数最接近的和
            // 前i-1个应该已经尝试过，不需要继续寻找
            left = i + 1;
            right = nums.length - 1;

            while (left < right) {
                currentSum = nums[i] + nums[left] + nums[right];
                if (Math.abs(currentSum - target) < Math.abs(sum - target)) {
                    sum = currentSum;
                }

                if (currentSum == target)
                    return currentSum;
                else if (currentSum > target) {
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    right--;
                } else {
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    left++;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
//        int[] input = {-55,-24,-18,-11,-7,-3,4,5,6,9,11,23,33};
        int[] input = {-1,0,1,1,55};
        int target = 3;
        System.out.println(threeSumClosest(input, target));
    }
}
