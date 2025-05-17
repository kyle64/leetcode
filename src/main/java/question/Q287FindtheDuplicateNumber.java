/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

/**
 * @author wuziheng
 * @description 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 示例 3 :
 *
 * 输入：nums = [3,3,3,3,3]
 * 输出：3
 *
 *
 *
 *
 * 提示：
 *
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *
 *
 * 进阶：
 *
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 *
 * @date 2025/5/17 23:50
 **/
public class Q287FindtheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        // 借鉴Q142环形列表的思想解题
        // 建立nums[i] -> i的映射
        // 如果没有重复的数，则下标越界时指向null，形成单向链表
        // 如果有重复的数，则一定有链表存在环
        // 问题转换为：找到链表环入口
        int fast = 0, slow = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (fast != slow);
        // 找到相遇点，从起始位置出发，两指针相遇即可
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    public int findDuplicateBinarySearch(int[] nums) {
        int n = nums.length;
        // 数组只读——不能去移动数组，不能排序
        // 常量空间——不能使用哈希表，不能新建数组再排序
        // 时间复杂度小于O(N^2)——不能暴力求解
        // 只有一个数字重复，但可能重复多次
        // 二分查找除了对索引二分，还有值域二分
        // 因为题目要找的是一个 整数，并且这个整数有明确的范围，所以可以使用「二分查找」。
        int res = -1;
        // 根据题意，目标数字取值在[1:n-1]之间
        // 二分，并统计<mid的个数count
        // 如果count < mid, 说明<mid数字出现的次数不足一半，重复数字一定在右半边，即：target > mid
        int left = 1, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            // 说明有超过mid个数落在[1, mid], 但该区间只有mid个坑
            // 根据抽屉原理，target一定在[mid+1, right]
            if (count > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int findDuplicateLocalHash(int[] nums) {
        int n = nums.length;
        // 如果允许修改数组，可以使用原地哈希，如Q41第一个缺失的数
        // nums[i] - 1 -> i
        for (int i = 0; i < n; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
            // 此时退出循环时预期应该有nums[i] - 1 = i
            // 但如果是重复元素，则无法满足
            if (nums[i] - 1 != i) {
                return nums[i];
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
