/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import structure.ListNode;

/**
 * @author wuziheng
 * @description
 * @date 2025/4/19 22:45
 **/
public class Q25ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast, slow;
        fast = slow = dummy;

        while (fast != null) {
            for (int i = 0; i < k; i++) {
                if (fast != null) {
                    fast = fast.next;
                }
            }

            if (fast == null) break;

            // reverse [slow.next, fast]
            ListNode newTail = slow.next;
            slow.next = reverse(slow.next, fast);

            slow = fast = newTail;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode current = head;
        ListNode tailNext = tail.next;
        ListNode prev = tailNext;

        while (current != tailNext) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return tail;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 4, 5};
        ListNode head = ListNode.constructList(nums);

        ListNode newHead = new Q25ReverseKGroup().reverseKGroup(head, 3);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
}
