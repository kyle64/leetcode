package question;

/**
 * Created by ziheng on 2020/7/13.
 */
public class Q148sortList {
    public static class ListNode {
        int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    /**
     * @Description: 148. 排序链表
     *
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     *
     * 示例 1:
     *
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2:
     *
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/13 下午6:02
     * @param
     * @return question.Q148sortList.ListNode
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // 快慢指针找中点切割
        ListNode fast = head.next, slow = head;
        ListNode mid;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        mid = slow.next;
        slow.next = null;

        // merge sort
        head = sortList(head);
        mid = sortList(mid);
        return merge(head, mid);
    }

    public ListNode merge(ListNode start, ListNode mid) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        ListNode left = start, right = mid;
        while (left != null && right != null) {
            if (left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }

            current = current.next;
        }

        if (left != null) {
            current.next = left;
        }

        if (right != null) {
            current.next = right;
        }

        return dummy.next;
    }
}

