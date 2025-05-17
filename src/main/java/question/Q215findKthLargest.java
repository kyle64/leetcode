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

    public int findKthLargest1(int[] nums, int k) {
        // 快排分区，quick select
        // 第k大 = 下标(n - k)的元素
        return quickSelect1(nums, nums.length - k, 0, nums.length - 1);
    }

    private int quickSelect1(int[] nums, int k, int left , int right) {
        if (left == right) {
            return nums[k];
        }

        int pivotIdx = partition(nums, left, right);
//        int pivotIdx = partition1(nums, left, right);
        if (pivotIdx == k) {
            return nums[k];
        } else if (k <= pivotIdx) {
            // kth元素在pivot轴点左侧
            return quickSelect1(nums, k, left, pivotIdx);
        } else {
            // kth元素在pivot轴点右侧
            return quickSelect1(nums, k, pivotIdx + 1, right);
        }
    }

    private int partition(int[] nums, int left, int right) {
        // 可以random，这边选中点
        int mid = left + (right - left) / 2;
        int pivot = nums[mid];
        // 交换至头部
        swap(nums, left, mid);
        int start = left;
        // left++;
        // 此处不能用left < right, 避免left + 1 = right的场景
        // 当左侧是<=pivot，右侧是>=pivot的元素时，会忽略跳过pivot，因此无需提前移动一次left的位置
        while (left < right) {
            // 由于left是找大于pivot的数，right是找小于pivot的数；
            // 如果先移动left，就会导致最终left停在>pivot的地方，从而在和头部位置的pivot交换后发生矛盾
            // 因此要么先移动right，要么将判断条件改为left <= right

            // 从right开始忽略大于pivot的元素
            while (left < right && nums[right] >= pivot) {
                right--;
            }

            // 从left开始忽略小于pivot的元素
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            swap(nums, left, right);
        }
        // right的位置处于最后一个<=pivot的下标处，和pivot交换后返回pivot的下标
        swap(nums, start, right);
        return right;
    }

    private int partition1(int[] nums, int left, int right) {
        // 可以random，这边选中点
        int mid = left + (right - left) / 2;
        int pivot = nums[mid];
        // 交换至头部
        swap(nums, left, mid);
        int start = left;
        left++;
        // 此处不能用left < right, 避免left + 1 = right的场景
        while (left <= right) {
            // 由于left是找大于pivot的数，right是找小于pivot的数；
            // 如果先移动left，就会导致最终left停在>pivot的地方，从而在和头部位置的pivot交换后发生矛盾
            // 因此要么先移动right，要么将判断条件改为left <= right

            // 从left开始忽略小于pivot的元素
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            // 从right开始忽略大于pivot的元素
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            // FATAL: 交换时包含pivot，
            // 可能导致此处left=right时的nums[left]=nums[right] < pivot，
            // 从而无法正确返回pivot的位置
            if (left >= right) {
                break;
            }

            swap(nums, left, right);
            left++;
            right--;
        }
        swap(nums, start, right);
        return right;
    }

    public int findKthLargest2(int[] nums, int k) {
        // 快排分区，quick select
        // 第k大 = 下标(n - k)的元素
        return quickSelect2(nums, nums.length - k, 0, nums.length - 1);
    }

    private int quickSelect2(int[] nums, int k, int left , int right) {
        if (left == right) {
            return nums[k];
        }

        int pivotIdx = hoarePartition(nums, left, right);
        if (k <= pivotIdx) {
            // kth元素在pivot轴点左侧
            return quickSelect2(nums, k, left, pivotIdx);
        } else {
            // kth元素在pivot轴点右侧
            return quickSelect2(nums, k, pivotIdx + 1, right);
        }
    }

    int hoarePartition(int[] arr, int low, int high) {
        int pivot = arr[low];  // 通常选择第一个元素（可优化为随机）
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do { i++; } while (arr[i] < pivot); // 找左边 >=pivot 的元素
            do { j--; } while (arr[j] > pivot); // 找右边 <=pivot 的元素

            if (i >= j) return j; // 交叉时返回分割点

            swap(arr, i, j);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public int findKthLargestPQ1(int[] nums, int k) {
        // 维护小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                // 可不写，默认即可
                (o1, o2) -> o1 - o2
        );

        // init
        // 初始化一个大小为k的小顶堆
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        // 遍历剩余的元素，依次和堆顶元素比较，大于root的插入新元素堆中，弹出root，小于的忽略
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

    public int findKthLargestPQ3(int[] nums, int k) {
        // 维护小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                // 可不写，默认即可
                (o1, o2) -> o1 - o2
        );

        // 维护一个大小为k的小顶堆
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    public int findKthLargestHeapify(int[] nums, int k) {
        int n = nums.length;
        // 构建大顶堆
        // 此时最大的元素应该在根节点，也就是nums[0]
        buildMaxHeap(nums, n);
        // 逐个提取堆顶元素（最大值）并调整堆
        for (int i = n - 1; i >= n - (k - 1); i--) {
            // 将当前堆顶元素（最大值）与末尾元素交换
            swap(nums, 0, i);
            // 从root开始，调整剩余元素使其满足最大堆性质
            // 忽略已经移到末尾的元素nums[i]，剩余堆大小正好是i
            maxHeapify(nums, 0, i);
        }

        return nums[0];
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        // 堆是一个完全二叉树，可以用数组表示。对于长度为 n 的数组：
        // - 叶子节点：所有索引满足 i ≥ ⌊n/2⌋ 的节点（即后一半元素）。
        // - 非叶子节点：所有索引 i < ⌊n/2⌋ 的节点（即前一半元素）。
        // 寻找最后一个非叶子节点
        for (int i = heapSize/2 - 1; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    private void maxHeapify(int[] nums, int i, int len) {
        // i是当前节点, left、right为左右子树节点
        int largest = i;
        int left = i * 2 + 1;
        int right = left + 1;
        // left,mid,right取最大值作为root
        // 左子树存在 且 left.value > root.value
        if (left < len && nums[left] > nums[largest]) {
            largest = left;
        }
        // 右子树存在 且 right.value > root.value/left.value
        if (right < len && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(nums, i, largest);
            maxHeapify(nums, largest, len);
        }
    }

    public static void main(String[] args) {
        Q215findKthLargest q215findKthLargest = new Q215findKthLargest();
        int[] input = {5,2,4,1,3,6,0};
        int res = q215findKthLargest.findKthLargest2(input, 4);
        System.out.println(res);
    }
}
