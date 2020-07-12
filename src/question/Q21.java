package question;

/**
 * Created by ziheng on 2020/7/10.
 */
public class Q21 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * @param l1
     * @param l2
     * @return question.Q21.ListNode
     * @Description: 21. 合并两个有序链表
     * <p>
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @date 2020/7/10 上午11:39
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 类似归并排序
        ListNode dummy = new ListNode();
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

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可。来自leetcode
        current.next = l1 == null ? l2 : l1;


//        while (l1 != null) {
//            current.next = l1;
//            l1 = l1.next;
//            current = current.next;
//        }
//
//        while (l2 != null) {
//            current.next = l2;
//            l2 = l2.next;
//            current = current.next;
//        }

        return dummy.next;
    }

}
