package question;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by ziheng on 2020/7/14.
 */
public class Q215findKthLargest {
    /**
     * @Description: 215. 数组中的第K个最大元素
     *
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 示例 1:
     *
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     *
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 说明:
     *
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/14 上午10:07
     * @param nums
     * @param k
     * @return int
     */
    public int findKthLargest(int[] nums, int k) {
        // 堆排序，构建maxHeap
        for (int i = nums.length / 2 - 1; i >= 0 ; i--) {
            adjustHeap(nums, i, nums.length);
        }

        // 弹出堆顶元素，确定当前位置的最大值
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            // swap堆顶到最小的子节点
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            // 调整长度为i的大顶堆，确定i-1的最大值
            adjustHeap(nums, 0, i);
        }

        return nums[0];
    }

    private void adjustHeap(int[] nums, int i, int length) {
        while (i < length) {
            int k = 2 * i + 1; // 子节点
            if (k < length - 1 && nums[k + 1] > nums[k]) {
                k++;
            }

            if (k < length && nums[i] < nums[k]) {
                // swap
                int temp = nums[k];
                nums[k] = nums[i];
                nums[i] = temp;

                i = k;
            } else {
                break;
            }
        }
    }


    public int findKthLargestQuickSort(int[] nums, int k) {
        // 利用快速排序的partition，找到num[length - k]，使其右边有k - 1个数大于它。
        return quickSelect(nums, k, 0, nums.length - 1);
    }

    public int quickSelect(int[] nums, int k, int left, int right) {
        if (left == right) return nums[left];

        int pivotLocation = partition(nums, k, left, right);

        if (pivotLocation == nums.length - k) {
            return nums[pivotLocation];
        } else if (pivotLocation < nums.length - k) {
            // 目标在pivot轴点右边
            return quickSelect(nums, k,pivotLocation + 1, nums.length - 1);
        } else {
            // 目标在pivot轴点左边
            return quickSelect(nums, k,0, pivotLocation - 1);
        }
    }

    public int partition(int[] nums, int k, int left, int right) {
        int pivot = nums[left];

        while (left < right) {
            while (left < right && nums[right] >= pivot) right--;
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    public int findKthLargestPQ(int[] nums, int k) {
        int result = nums[0];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                }
        );

        for (int num : nums) {
            priorityQueue.add(num);
        }

        while (k >= 1) {
            result = priorityQueue.poll();
            k--;
        }

        return result;
    }

    public static void main(String[] args) {
        Q215findKthLargest q215findKthLargest = new Q215findKthLargest();
        int[] input = {3,2,1,5,6,4};
        int res = q215findKthLargest.findKthLargest(input, 2);
    }
}
