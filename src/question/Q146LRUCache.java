package question;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ziheng on 2020/7/17.
 */
public class Q146LRUCache {
    /**
     * @param
     * @Description: 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
     * <p>
     * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
     * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     * <p>
     *  
     * <p>
     * 进阶:
     * <p>
     * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lru-cache
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @date 2020/7/17 下午5:08
     * @return
     */

    // 示例:
    //
    // LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
    //
    // cache.put(1, 1);
    // cache.put(2, 2);
    // cache.get(1);       // 返回  1
    // cache.put(3, 3);    // 该操作会使得关键字 2 作废
    // cache.get(2);       // 返回 -1 (未找到)
    // cache.put(4, 4);    // 该操作会使得关键字 1 作废
    // cache.get(1);       // 返回 -1 (未找到)
    // cache.get(3);       // 返回  3
    // cache.get(4);       // 返回  4
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/lru-cache
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    public class ListNode {
        private Integer key;
        private Integer value;

        ListNode prev, next;

        public ListNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    // 使用map + 链表的数据结构，map存储数据，list保存使用顺序
    final Map<Integer, ListNode> map;
    // 容量
    final int capacity;
    // 双向链表头尾节点
    ListNode head, tail;


    public Q146LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        ListNode node = this.map.get(key);
        if (node == null) return -1;

        // 移动到队头
        moveToHead(remove(node));
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.value = value;

            // 移动到队头
            moveToHead(remove(node));
        } else {
            if (map.size() >= this.capacity) {
                // 移除tail节点
                map.remove(tail.key);
                remove(tail);
            }
            ListNode node = new ListNode(key, value);
            map.put(key, node);
            moveToHead(node);
        }
    }

    private void moveToHead(ListNode node) {
        if (head == null) {
            head = tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
    }

    private ListNode remove(ListNode node) {
        // node是有效节点
        ListNode prevNode = node.prev, nextNode = node.next;

        // node是head节点
        if (prevNode == null) {
            head = nextNode;
        } else {
            prevNode.next = nextNode;
        }

        // node是tail节点
        if (nextNode == null) {
            tail = prevNode;
        } else {
            nextNode.prev = prevNode;
        }

        node.prev = null;
        node.next = null;

        return node;
    }
}
