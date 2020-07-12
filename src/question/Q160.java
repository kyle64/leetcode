package question;

/**
 * Created by ziheng on 2020/7/11.
 */
public class Q160 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 方法二: 哈希表法
    //遍历链表 A 并将每个结点的地址/引用存储在哈希表中。然后检查链表 B 中的每一个结点 b_ib
    //i
    //​
    //  是否在哈希表中。若在，则 b_ib
    //i
    //​
    //  为相交结点。
    //
    //复杂度分析
    //
    //时间复杂度 : O(m+n)O(m+n)。
    //空间复杂度 : O(m)O(m) 或 O(n)O(n)。
    //
    //作者：LeetCode
    //链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。




    // 官方思路：A + B = B + A，双指针同时进行，a的tail指向b的head，b的tail指向a的head
    // 方法三：双指针法
    //创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
    //当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B); 类似的，当 pBpB 到达链表的尾部时，将它重定位到链表 A 的头结点。
    //若在某一时刻 pApA 和 pBpB 相遇，则 pApA/pBpB 为相交结点。
    //想弄清楚为什么这样可行, 可以考虑以下两个链表: A={1,3,5,7,9,11} 和 B={2,4,9,11}，相交于结点 9。 由于 B.length (=4) < A.length (=6)，pBpB 比 pApA 少经过 22 个结点，会先到达尾部。将 pBpB 重定向到 A 的头结点，pApA 重定向到 B 的头结点后，pBpB 要比 pApA 多走 2 个结点。因此，它们会同时到达交点。
    //如果两个链表存在相交，它们末尾的结点必然相同。因此当 pApA/pBpB 到达链表结尾时，记录下链表 A/B 对应的元素。若最后元素不相同，则两个链表不相交。
    //复杂度分析
    //
    //时间复杂度 : O(m+n)O(m+n)。
    //空间复杂度 : O(1)O(1)。
    //
    //作者：LeetCode
    //链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pointerA = headA, pointerB = headB;
        while (pointerA != pointerB) {
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }

        // 相交则为交点，不相交则为null
        return pointerA;
    }

    public ListNode getIntersectionNodeCycle(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // 记录headA的tail，然后指向headB；如果相交，则应该会成环，找到进入环的节点，不相交的话则会遍历到headB的tail
        ListNode pointerA = headA, pointerB = headB;
        ListNode tailA = null;

        while (pointerA.next != null) {
            pointerA = pointerA.next;
        }

        // 记录tail，最后需要将tail.next置为null
        tailA = pointerA;
        tailA.next = headB;

        // 快慢指针看是否相遇
        ListNode fast = headA, slow = headA;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                // 双指针相遇，有环
                break;
            }
        }

        // 无环
        if (fast == null || fast.next == null) {
            tailA.next = null;
            return null;
        }

        // 环的长度是c
        // 判断环的入口，假设slow走过的距离是s = a + x + mc，a是head到入口的距离，x是相遇的位置，m是圈数
        // fast指针走过的是 2s = s + nc
        // s = nc = a + x + mc, a = (m-n)c - x = （m - n - 1)c + (c - x)
        // 也就是说slow指针从head走到入口的距离，等于从相遇点走到入口的距离加k圈环的长度
        // 因此只需要head的指针和相遇点的指针相遇，那一定是在入口
        ListNode start = headA;
        ListNode meet = fast;
        while (start != meet) {
            start = start.next;
            meet = meet.next;
        }

        tailA.next = null;
        return start;
    }
}
