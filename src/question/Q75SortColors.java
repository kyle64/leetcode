package question;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ziheng on 2020/7/29.
 */
public class Q75SortColors {
    /**
     * @Description: 75. 颜色分类
     *
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * 注意:
     * 不能使用代码库中的排序函数来解决这道题。
     *
     * 示例:
     *
     * 输入: [2,0,2,1,1,0]
     * 输出: [0,0,1,1,2,2]
     * 进阶：
     *
     * 一个直观的解决方案是使用计数排序的两趟扫描算法。
     * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
     * 你能想出一个仅使用常数空间的一趟扫描算法吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-colors
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/29 下午5:15
     * @param
     * @return void
     */
    public static void sortColors(int[] nums) {
        // 官方
        // 我们用三个指针（p0, p2 和curr）来分别追踪0的最右边界，2的最左边界和当前考虑的元素。
        // p0从0开始往右，p2从n-1开始往左，
        // nums[curr] = 2时，跟p2交换，p2左移（跟p2交换后curr不马上右移，因为p2原来位置的数没有扫描过）
        // nums[curr] = 0时，跟p0交换，p0右移，current正常右移（跟p0交换后curr右移，因为curr之前的数都已经扫描过了）
        // nums[curr] = 1时，curr正常右移
        // curr > p2时问题解决
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        int curr = 0;

        // 一次遍历 O(N)
        while (curr <= p2) {
            if (nums[curr] == 2) {
                swap(nums, curr, p2--);
            } else if (nums[curr] == 0) {
                swap(nums, curr, p0++);
                curr++;
            } else if (nums[curr] == 1) {
                curr++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void sortColorsSort(int[] nums) {
        // 排序
        quickSort(nums, 0 , nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pivotLocation = partition(nums, left, right);
            quickSort(nums, left, pivotLocation);
            quickSort(nums, pivotLocation + 1, right);
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];

        while (left < right) {
            // 从right开始找第一个小于pivot的数
            while (left < right && nums[right] >= pivot) right--;
            // 交换left 和 right
            nums[left] = nums[right];

            // 从left开始找到第一个大于pivot的数
            while (left < right && nums[left] <= pivot) left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;

        return left;
    }


    public static void sortColorsCount(int[] nums) {
        // 先计数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Integer num : nums) {
            Integer count = countMap.get(num);
            if (count == null) {
                countMap.put(num, 1);
            } else {
                countMap.put(num, count + 1);
            }
        }

        // 再排序
        int cur = 0;
        for (int i = 0; i < 3; i++) {
            Integer count = countMap.get(i);
            if (count != null) {
                for (int j = 0; j < count; j++) {
                    nums[cur] = i;
                    cur++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] inputs = {2,0,2,1,1,0};
        sortColors(inputs);

        for (int input : inputs) {
            System.out.println(input + " ");
        }
    }
}
