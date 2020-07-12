package question;

import java.util.Arrays;

/**
 * Created by ziheng on 2020/7/9.
 */
public class Q136 {
    /**
     * @Description: 136. 只出现一次的数字
     *
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     *
     * 输入: [4,1,2,1,2]
     * 输出: 4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/single-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/9 下午5:11
     * @param
     * @return int
     */
    public static int singleNumber(int[] nums) {
        int single = 0;
        // 异或位运算
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    public static int singleNumber1(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; ) {
            sum += nums[i++];
            if (i < nums.length) {
                sum -= nums[i++];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] inputs = {2,2,1};
        System.out.println(singleNumber(inputs));
    }
}
