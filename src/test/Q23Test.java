package test;

import question.Q23MergeKSortedLists;

/**
 * Created by ziheng on 2020/7/17.
 */
public class Q23Test {
    public static void main(String[] args) {
        Q23MergeKSortedLists q23MergeKSortedLists = new Q23MergeKSortedLists();

        Q23MergeKSortedLists.ListNode l1 = new Q23MergeKSortedLists.ListNode(-2);
        Q23MergeKSortedLists.ListNode head1 = l1;
        l1.next = new Q23MergeKSortedLists.ListNode(-1);
        l1 = l1.next;
        l1.next = new Q23MergeKSortedLists.ListNode(-1);
        l1 = l1.next;
        l1.next = new Q23MergeKSortedLists.ListNode(-1);

        Q23MergeKSortedLists.ListNode[] listNodes = {head1, null};
        Q23MergeKSortedLists.ListNode res = q23MergeKSortedLists.mergeKLists(listNodes);

        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
