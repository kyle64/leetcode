package question;

import java.util.Arrays;

/**
 * Created by ziheng on 2020/7/23.
 */
public class Q31NextPermutation {
    /**
     * @Description: 31. 下一个排列
     *
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     *
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * 必须原地修改，只允许使用额外常数空间。
     *
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-permutation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/23 上午11:31
     * @param
     * @return void
     */
    public static void nextPermutation(int[] nums) {
        // 从后往前遍历，如果找到前一个数比后一个数小的，那么就可以重排序了
        // 从后往前，找到第一个升序的数i，找到顺序上只比num[i]大的num[j]，交换i和j，然后对[i, len - 1]排序
        if (nums.length < 2) return;

        int next = -1;
        // 从后往前遍历，找到一个升序的数
        for (int i = nums.length - 1; i > 0 ; i--) {
            if (nums[i] > nums[i-1]) {
                next = i - 1;
                break;
            }
        }

        // 完全降序排列
        if (next < 0) {
            Arrays.sort(nums);
            return;
        }

        // 从i到最后一个元素，都是连续的降序，所以再次从后往前寻找 要交换的数，并交换
        for (int j = nums.length - 1; j > next; j--) {
            if (nums[j] > nums[next]) {
                swap(nums, j, next);
                break;
            }
        }

        // 将新的节点后面是数字重新排序
        Arrays.sort(nums, next + 1, nums.length);
    }

    public void nextPermutation1(int[] nums) {
        // 从后往前寻找第一个连续升序的相邻元素
        // 因为有升序的元素，就意味着可以通过交换得到下一个字段序
        int n = nums.length;
        int left = -1, right = -1;
        for (int i = n - 1; i > 0; i--) {
            // 出现相邻升序元素[a0, a1], a0是需要被替换的
            if (nums[i] > nums[i - 1]) {
                left = i - 1;
                break;
            }
        }

        // 完全非递增数列，直接排序
        if (left < 0) {
            Arrays.sort(nums);
            return;
        }

        // 从后往前寻找第一个大于nums[left]数，此为下一个字典序要修改的元素
        for (int i = n - 1; i > left; i--) {
            // 相等也跳过
            if (nums[i] > nums[left]) {
                right = i;
                break;
            }
        }
        swap(nums, left, right);
        // 排序（此时等价于反转）从nums[i:n-1]的剩余数组
        reverse(nums, left + 1, n - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while(l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] input = {1,2,7,4,6,5,3};
        nextPermutation(input);
        for (int i : input) {
            System.out.print(i + " ");
        }
    }
}
