package question;

/**
 * Created by ziheng on 2020/7/13.
 */
public class Q238productExceptSelf {
    /**
     * @Description: 238. 除自身以外数组的乘积
     *
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     *
     *  
     *
     * 示例:
     *
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     *  
     *
     * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
     *
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     *
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/13 下午3:44
     * @param
     * @return int[]
     */
    public int[] productExceptSelf(int[] nums) {
        // res[i] 应该等于 [0, i)的乘积 * [i+1, length)的乘积
        int[] res = new int[nums.length];

        int[] dpForward = new int[nums.length];
        int[] dpBackward = new int[nums.length];
        dpForward[0] = 1;
        dpBackward[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; i++) {
            dpForward[i] = nums[i - 1] * dpForward[i - 1];
            dpBackward[nums.length - 1 - i] = nums[nums.length - i] * dpBackward[nums.length - i];
        }

        for (int i = 0; i < nums.length; i++) {
            res[i] = dpForward[i] * dpBackward[i];
        }

        return res;
    }

    // leetcode官方O(1)空间复杂度的解法。
    // 用R存储右边的乘积，从后往前遍历时，直接得出最终的乘积
    public int[] productExceptSelfOfficial(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];

        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }
        return answer;
    }

    public int[] productExceptSelf1(int[] nums) {
        // 不可以用除法，也有0的限制，因此记录每个位置左侧和右侧的乘积
        int n = nums.length;
        int[] res = new int[n];
        int[] L = new int[n];
        int[] R = new int[n];

        L[0] = 1;
        for (int i = 1; i < n; i++) {
            L[i] = L[i - 1] * nums[i - 1];
        }

        R[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            res[i] = L[i] * R[i];
        }

        return res;
    }

    public static void main(String[] args) {
        Q238productExceptSelf q238productExceptSelf = new Q238productExceptSelf();
        int[] input = {4, 3, 9, 6};
        int[] res = q238productExceptSelf.productExceptSelf(input);
        for (int re : res) {
            System.out.print(re + " ");
        }
    }
}
