package question;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ziheng on 2020/7/21.
 */
public class Q41FirstMissingPositive {
    /**
     * @Description: 41. 缺失的第一个正数
     *
     * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [1,2,0]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [3,4,-1,1]
     * 输出: 2
     * 示例 3:
     *
     * 输入: [7,8,9,11,12]
     * 输出: 1
     *  
     *
     * 提示：
     *
     * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/first-missing-positive
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/21 上午10:20
     * @param
     * @return int
     */

    // 置换方法
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 如果x = nums[i]在1到N之间，那么x应该在nums[x-1]位置
            // 交换完成后如果还在范围中则继续交换
            while (nums[i] >= 1 && nums[i] <= n) {
                // 判断是否相同，以免死循环
                if (nums[i] == nums[nums[i] - 1]) break;

                // swap
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }

        // 第一个nums[i]不正确的i+1即为缺失的正整数
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        return n + 1;
    }


    // 用大小为N的数组代替哈希表
    public int firstMissingPositiveOfficial(int[] nums) {
        int n = nums.length;
        // 将非正整数的值设为n+1
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        // 再次遍历，用负号标记原数组内在1-n范围内的数，nums[i-1]意味着nums[i]在1-N范围中
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // 找到第一个正数，即：未被标记的数
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }


    // 哈希表
    public int firstMissingPositive1(int[] nums) {
        int res = 1;
        int length = nums.length;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < length; i++) {
            set.add(nums[i]);
        }

        while (set.contains(res)) {
            res++;
        }

        return res;
    }

    public int firstMissingPositiveHashSet(int[] nums) {
        // 哈希表，时间复杂度O(N)，但是空间复杂度也为O(N)
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        for (int i = 1; i <= nums.length; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        return nums.length + 1;
    }

    public int firstMissingPositiveSort(int[] nums) {
        // 排序，空间复杂度也为O(1)，但时间复杂度O(NLogN)

        int res = 1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1) continue;

            if (nums[i] == res) {
                res++;
            }
        }
        return res;
    }

    public int firstMissingPositiveMarkNegative(int[] nums) {
        // 核心，答案只可能在[1, N + 1]之间
        // 因此，将符合条件的nums[i] 映射到i的下标上；即1 -> nums[0], 2 -> nums[1], ...

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1 || nums[i] > nums.length) {
                nums[i] = nums.length + 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num > nums.length) continue;
            nums[num - 1] = -Math.abs(nums[num - 1]); // 标记成相对数，即负数（官解一）
        }

        for (int i = 0; i < nums.length; i++) {
            // 意味着没有对应的数将该下表位置标记成负数
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public int firstMissingPositiveSwap(int[] nums) {
        // 核心，答案只可能在[1, N + 1]之间
        // 因此，将符合条件的nums[i] 映射到i的下标上；即1 -> nums[0], 2 -> nums[1], ...

        for (int i = 0; i < nums.length; i++) {
            // nums[i] > 0 && nums[i] <= N意味着nums[i]的值在[1, N]之间，才交换
            // nums[nums[i] - 1] != nums[i]避免nums交换前后的value是一样的而陷入死循环
            // while而不是if因为要避免目标位置nums[nums[i] - 1]的值也是[1, N]之间的值
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    private void swap(int nums[], int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
