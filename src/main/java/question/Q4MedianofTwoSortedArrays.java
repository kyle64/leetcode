package question;

/**
 * Created by ziheng on 2020/7/20.
 */
public class Q4MedianofTwoSortedArrays {
    /**
     * @Description: 4. 寻找两个正序数组的中位数
     *
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
     *
     * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     *
     * 你可以假设 nums1 和 nums2 不会同时为空。
     *
     *  
     *
     * 示例 1:
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * 则中位数是 2.0
     * 示例 2:
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * 则中位数是 (2 + 3)/2 = 2.5
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/20 上午9:21
     * @param nums1
     * @param nums2
     * @return double
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 二分法，因为要找中位数，即第k小的数，每次去掉两个数组中较小的前(k - i) / 2个数
        // 时间复杂度O(log(m + n))

        // k是第k小的数
        int k = (nums1.length + nums2.length + 1) / 2;
        // 偶数个数的话，需要求中间两个数
        int midLeft = findKthSmallest(nums1, nums2, 0, 0, k);
        if ((nums1.length + nums2.length) % 2 == 0) {
            int midRight = findKthSmallest(nums1, nums2, 0, 0, k + 1);
            return (double) (midLeft + midRight) / 2;
        }

        return midLeft;
    }

    public static int findKthSmallest(int[] nums1, int[] nums2, int l1, int l2, int k) {
        // 如果nums1的所有元素都已经剔除，那么要找的数就是nums2中剩下的第k-1个数
        if (l1 >= nums1.length) {
            return nums2[l2 + k - 1];
        }
        // nums2同上
        if (l2 >= nums2.length) {
            return nums1[l1 + k - 1];
        }

        // 如果k = 1，则已经有中位数m-1的数被cut掉了，取当前两个指针中小的那个
        if (k == 1) {
            return Math.min(nums1[l1], nums2[l2]);
        }

        // 从当前有效的数组中，找到第k/2的位置，即前面有k/2 - 1个数的位置
        int p1 = Math.min(l1 + k / 2 - 1, nums1.length - 1);
        int p2 = Math.min(l2 + k / 2 - 1, nums2.length - 1);

        // 如果nums1中p1位置的数小于nums2中p2的数，则说明p1前的k/2 - 1个数都不可能是第k小的数，
        // 包括p1自身，小于p1的最多只可能有(k/2 - 1) + (k/2 - 1) = k - 2个数，
        // 所以p1最多是第k - 1小的数（甚至更小），因此cut掉所有在p1之前的序列（包括p1）
        if (nums1[p1] <= nums2[p2]) {
            // 因为nums1中剩余的长度可能会小于k/2，因此只减去此次cut掉的数目
            k = k - (p1 - l1 + 1);
            return findKthSmallest(nums1, nums2, p1 + 1, l2, k);
        } else {
            k = k - (p2 - l2 + 1);
            return findKthSmallest(nums1, nums2, l1, p2 + 1, k);
        }
    }


    public static double findMedianSortedArraysTwoPointers(int[] nums1, int[] nums2) {
        // 双指针，时间复杂度O(m+n)
        boolean isEven = (nums1.length + nums2.length) % 2 == 0;
        int mid = (nums1.length + nums2.length) / 2;

        int a = 0, b = 0, count = 0;
        int midLeft = Integer.MIN_VALUE, midRight = midLeft;
        while (count <= mid) {
            if (isEven && count == mid) {
                midLeft = midRight;
            }

            if (a == nums1.length) {
                midRight = nums2[b];
                b++;
            } else if (b == nums2.length) {
                midRight = nums1[a];
                a++;
            } else {
                if (nums1[a] < nums2[b]) {
                    midRight = nums1[a];
                    a++;
                } else {
                    midRight = nums2[b];
                    b++;
                }
            }

            count++;
        }

        return isEven ? (double) (midLeft + midRight) / 2 : midRight;
    }

    // 官方，划分数组，根据中位数定义，i,j分别切分nums1和nums2
    // 符合中位数定义的划分条件：Max(nums1[i - 1], nums2[j - 1]) <= Min(nums1[i], nums2[j])
    // 时间复杂度O(log(min(m,n)))
    public double findMedianSortedArraysOfficial(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArraysOfficial(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m, ansi = -1;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            // 找一个分界，左右两边的数字个数一样
            // i + j = m - i + n - j or i + j = m - i + n - j + 1
            // i + j = (m + n + 1) / 2
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            // B[j−1] <= A[i] 以及 A[i−1] <= B[j]，即前一部分的最大值小于等于后一部分的最小值
            // 当 i 从 0∼m 递增时，A[i−1] 递增，B[j] 递减，所以一定存在一个最大的i满足A[i−1] <= B[j]；
            //
            // 如果 i 是最大的，那么说明i+1不满足。将i+1带入可以得到A[i] > B[j−1]，也就是B[j−1]<A[i]，
            // 就和我们进行等价变换前i的性质一致了（甚至还要更强）。

            // 因此我们可以对 i 在[0,m]的区间上进行二分搜索，找到最大的满足A[i−1]<=B[j]的i值，就得到了划分的方法。
            // 此时，划分前一部分元素中的最大值，以及划分后一部分元素中的最小值，才可能作为就是这两个数组的中位数。
            if (nums_im1 <= nums_j) {
                ansi = i;
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            }
            else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    public double findMedianSortedArraysFindKthNum(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int totalLen = m + n;
        // 找中位数就是找第(m + n)/2 and (m + n)/2 + 1一两个数（奇偶时需要）
        // 转化为用二分法找第k个数
        // 二分法核心思想：每次剔除一半
        // 尝试剔除k/2个数，i = k/2 - 1时，比较A[i]和B[i]，
        // 如果A[i] < B[i]，就说明A中有k/2 - 1个小于A[i]的数，B中最多也只有k/2 - 1个小于A[i]的数，也就是总共最多只有k-2个数小于A[i]，因此A[i]及之前的数都可以排除，反之亦然
        int mid1 = totalLen / 2;
        int mid2 = totalLen / 2 + 1;

        if (totalLen % 2 == 1) {
            return (double) findKthNum(nums1, nums2, mid2);
        } else {
            return (findKthNum(nums1, nums2, mid1) + findKthNum(nums1, nums2, mid2)) / 2.0;
        }
    }

    private int findKthNum(int[] nums1, int[] nums2, int k) {
        // 三种情况需要特殊处理
        // 1. 如果 A[k/2−1] 或者 B[k/2−1] 越界，那么我们可以选取对应数组中的最后一个元素。在这种情况下，我们必须根据排除数的个数减少 k 的值，而不能直接将 k 减去 k/2。
        // 2. 如果一个数组为空，说明该数组中的所有元素都被排除，我们可以直接返回另一个数组中第 k 小的元素。
        // 3. 如果 k=1，我们只要返回两个数组首元素的最小值即可。
        int m = nums1.length, n = nums2.length;
        int l1 = 0, l2 = 0;
        while (true) {
            // 数组为空，直接返回另一数组的第k个元素
            if (l1 == m) {
                return nums2[l2 + k - 1];
            }
            if (l2 == n) {
                return nums1[l1 + k - 1];
            }

            // k=1，返回当前两个指针中较小的元素即可
            if (k == 1) {
                return Math.min(nums1[l1], nums2[l2]);
            }

            int mid = k/2;
            // A[k/2−1] 或者 B[k/2−1] 越界，那么我们可以选取对应数组中的最后一个元素
            int i = Math.min(l1 + mid, m) - 1;
            int j = Math.min(l2 + mid, n) - 1;

            if (nums1[i] < nums2[j]) {
                // 剔除nums1中下标小于等于i的数
                k -= (i - l1 + 1);
                l1 = i + 1;
            } else {
                k -= (j - l2 + 1);
                l2 = j + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] input1 = {1,2};
        int[] input2 = {3,4};

        System.out.println(findMedianSortedArrays(input1, input2));
    }

}
