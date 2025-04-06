package question;

import structure.ListNode;

/**
 * Created by ziheng on 2020/11/20.
 */
public class Q147InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode lastNode = head;
        ListNode current = head.next;
        while (current != null) {
            if (current.val >= lastNode.val) {
                lastNode = lastNode.next;
            } else {
                ListNode prev = dummy.next;

                while (prev.next != null && current.val >= prev.next.val) {
                    prev = prev.next;
                }

                lastNode.next = current.next;
                current.next = prev.next;
                prev.next = current;
            }

            current = lastNode.next;
        }

        return dummy.next;
    }
}
