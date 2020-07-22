package question;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by ziheng on 2020/7/23.
 */
public class Q19RemoveNthNodeFromEndofList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // n is valid
        if (head == null) return null;

        // 双指针
        // fast始终保持n + 1个节点的领先, 这样fast到达尾部的时候slow正好在slow.next是要删除的节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除节点
        slow.next = slow.next.next;
        return dummy.next;
    }


    public ListNode removeNthFromEndOfficial(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        int length = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            length++;
        }

        // 正数的位置, 找到被删除的前一个的位置
        length -= n;
        current = dummy;
        while (length > 0) {
            current = current.next;
            length--;
        }
        // 删除current.next
        current.next = current.next.next;

        return dummy.next;
    }

    public ListNode removeNthFromEndStack(ListNode head, int n) {
        // n is valid
        if (head == null) return null;

        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode current = head;
        while (current != null) {
            stack.add(current);
            current = current.next;
        }

        for (int i = 1; i <= n; i++) {
            current = stack.pollLast();
        }

        // remove current
        if (stack.isEmpty()) {
            // current是head
            return current.next;
        }

        // 取出前一个节点
        ListNode prev = stack.pollLast();
        if (prev != null) {
            prev.next = current.next;
        }

        return head;
    }
}
