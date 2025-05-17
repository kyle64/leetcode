/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import structure.ListNode;

/**
 * @author wuziheng
 * @description 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *
 * @date 2025/4/19 16:59
 **/
public class Q24SwapPairs {
    public ListNode swapPairs(ListNode head) {
        // 递归，假设nextNode已交换，返回交换后的firstNode(head)
        // 时间复杂度O(N), 空间复杂度O(N)，递归栈的深度
        if (head == null || head.next == null) return head;

        ListNode nextNode = head.next.next;
        ListNode second = head.next;
        second.next = head;
        head.next = swapPairs(nextNode);
        return second;
    }

    public ListNode swapPairsIterator(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = head;

        while (current != null && current.next != null) {
            // swap current & current next node
            prev.next = current.next;

            ListNode nextNode = current.next.next;
            current.next.next = current;
            current.next = nextNode;

            prev = current;
            current = nextNode;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 4};
        ListNode head = ListNode.constructList(nums);

        ListNode newHead = new Q24SwapPairs().swapPairsIterator(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
}
