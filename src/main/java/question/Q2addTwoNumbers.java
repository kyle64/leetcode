package question;

import structure.ListNode;

/**
 * Created by ziheng on 2018/3/1.
 */
public class Q2addTwoNumbers {

    /**
     * @param l1
     * @param l2
     * @return question.Q2addTwoNumbers.ListNode
     * @Description: 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @date 2020/7/9 上午11:39
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        dummy.next = res;

        int carrier = 0;
        while (l1 != null && l2 != null) {
            int sum = (l1.val + l2.val + carrier) % 10;
            carrier = (l1.val + l2.val + carrier) / 10;
            res.next = new ListNode(sum);

            l1 = l1.next;
            l2 = l2.next;
            res = res.next;
        }

        while (l1 != null) {
            int sum = (l1.val + carrier) % 10;
            carrier = (l1.val + carrier) / 10;
            res.next = new ListNode(sum);

            l1 = l1.next;
            res = res.next;
        }

        while (l2 != null) {
            int sum = (l2.val + carrier) % 10;
            carrier = (l2.val + carrier) / 10;
            res.next = new ListNode(sum);

            l2 = l2.next;
            res = res.next;
        }

        if (carrier > 0) res.next = new ListNode(1);

        return dummy.next;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        int carry = 0;
        int sum = 0;
        while (l1 != null || l2 != null) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;

            sum = (val1 + val2 + carry) % 10;
            carry = (val1 + val2 + carry) / 10;
            current.next = new ListNode(sum);
            current = current.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry > 0) {
            current.next = new ListNode(1);
        }

        return dummy.next;
    }
}
