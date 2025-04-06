package question;

/**
 * Created by ziheng on 2020/7/15.
 */
public class Q61rotateRight {
  public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    /**
     * @Description: 61. 旋转链表
     *
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     *
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/15 下午3:13
     * @param head
     * @param k
     * @return ListNode
     */
    public ListNode rotateRight(ListNode head, int k) {
        // 遍历到tail节点，计算出链表长度，然后指向head节点，再移动len - (k % len)步，然后cut，最后返回下一个节点
        if (head == null || head.next == null) return head;
        ListNode current = head;
        int len = 1;
        while (current.next != null) {
            current = current.next;
            len++;
        }
        // 到这一步的话，current应该指向当前tail，len计算完成

        // 指向head
        current.next = head;
        // 移动到新的tail节点
        for (int steps = len - (k % len); steps > 0; steps--) {
            current = current.next;
        }

        ListNode res = current.next;
        current.next = null;

        return res;
    }
}
