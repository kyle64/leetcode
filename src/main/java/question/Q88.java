package question;

import java.util.Arrays;

/**
 * Created by ziheng on 2020/7/11.
 */
public class Q88 {
    /**
     * @Description: 88. 合并两个有序数组
     *
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     *
     *  
     *
     * 说明:
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *  
     *
     * 示例:
     *
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/11 上午12:30
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     * @return void
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = 0, right = 0;
        int[] res = new int[m + n];
        int current = 0;

        // 归并排序，然后再System.arraycopy复制数组
        while (left < m && right < n) {
            if (nums1[left] < nums2[right]) {
                res[current++] = nums1[left++];
            } else {
                res[current++] = nums2[right++];
            }
        }

        while (left < m) {
            res[current++] = nums1[left++];
        }

        while (right < n) {
            res[current++] = nums2[right++];
        }

        System.arraycopy(res, 0, nums1, 0, m + n);
    }

    // 双指针 / 从后往前
    //直觉
    //
    //方法二已经取得了最优的时间复杂度O(n + m)O(n+m)，但需要使用额外空间。这是由于在从头改变nums1的值时，需要把nums1中的元素存放在其他位置。
    public void mergeFromLast(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
