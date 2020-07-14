package test;

import question.Q148sortList;

/**
 * Created by ziheng on 2020/7/14.
 */
public class Q148Test {
    public static void main(String[] args) {
        Q148sortList q148sortList = new Q148sortList();

        Q148sortList.ListNode node = new Q148sortList.ListNode(4);
        Q148sortList.ListNode head = node;
        node.next = new Q148sortList.ListNode(2);
        node = node.next;
        node.next = new Q148sortList.ListNode(1);
        node = node.next;
        node.next = new Q148sortList.ListNode(3);

        q148sortList.sortList(head);
    }
}
