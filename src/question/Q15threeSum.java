package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ziheng on 2020/7/17.
 */
public class Q15threeSum {
    /**
     * @Description: 15. 三数之和
     *
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     *  
     *
     * 示例：
     *
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/17 上午2:01
     * @param
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums);
        int left, right;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > 0) break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            // skip identical nums
            if (i > 0 && nums[i - 1] == nums[i]) continue; // 前一个数相同的话，应该已经查找过了

            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                // skip identical nums
                if (left > i + 1 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }

                int tempSum = nums[i] + nums[left] + nums[right];
                if (tempSum == 0) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    // 无论如何先要进行加减操作
                    left++;
                    right--;
                } else if (tempSum > 0) {
//                    while (left < right && nums[right - 1] == nums[right]) {
//                        right--;
//                    }
                    right--;
                } else {
//                    while (left < right && nums[left + 1] == nums[left]) {
//                        left++;
//                    }
                    left++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Q15threeSum q15threeSum = new Q15threeSum();
        int[] input = {-2,0,1,1,2};
        List<List<Integer>> list = q15threeSum.threeSum(input);
        for (List<Integer> integers : list) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.print("\n");
        }
    }
}
