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
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

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

    public static void main(String[] args) {
        int[] input1 = {1,2};
        int[] input2 = {3,4};

        System.out.println(findMedianSortedArrays(input1, input2));
    }

}
