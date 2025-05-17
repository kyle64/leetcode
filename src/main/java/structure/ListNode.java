package structure;

/**
 * Created by ziheng on 2020/11/20.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode constructList(int[] inputs) {
        ListNode dummy = new ListNode();
        ListNode prev = dummy;

        ListNode current;
        for (int num : inputs) {
            current = new ListNode(num);
            prev.next = current;
            prev = current;
        }
        return dummy.next;
    }
}
