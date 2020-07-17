package question;

import java.util.PriorityQueue;

/**
 * Created by ziheng on 2020/7/17.
 */
public class Q23MergeKSortedLists {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    /**
     * @Description: 23. 合并K个排序链表
     *
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * 示例:
     *
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/17 下午3:54
     * @param
     * @return question.Q23MergeKSortedLists.ListNode
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 分治O(kn * log(n))
        ListNode result = mergeKListsHelper(lists, 0, lists.length - 1);
        return result;
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;

        int mid = (left + right) / 2;
        // 分治
        // 认为left，mid和mid+1，right已经归并完成
        ListNode l1 = mergeKListsHelper(lists, left, mid);
        ListNode l2 = mergeKListsHelper(lists, mid + 1, right);

        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 == null) current.next = l2;
        if (l2 == null) current.next = l1;

        return dummy.next;
    }

    public ListNode mergeKListsPQ(ListNode[] lists) {
        // 构建一个priority queue
        // 优化pq，只存储list的头节点，然后依次pop并比较
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                ((o1, o2) -> o1.val - o2.val)
        );

        for (ListNode list : lists) {
            // 把整个list的head存进pq
            if (list != null) {
                pq.add(list);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            current.next = min;
            current = current.next;
            if (min.next != null) {
                pq.add(min.next);
            }
        }

        return dummy.next;
    }


    public ListNode mergeKListsPQ1(ListNode[] lists) {
        // 构建一个priority queue
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                ((o1, o2) -> o1.val - o2.val)
        );

        ListNode savedNext;
        for (ListNode head : lists) {
            while (head != null) {
                savedNext = head.next;
                head.next = null;
                pq.add(head);
                head = savedNext;
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (!pq.isEmpty()) {
            current.next = pq.poll();
            current = current.next;
        }

        return dummy.next;
    }
}
