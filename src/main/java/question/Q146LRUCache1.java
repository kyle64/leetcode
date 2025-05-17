/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuziheng
 * @description
 * @date 2025/4/22 11:48
 **/
class Q146LRUCache1 {
    public static class Node {
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public Node() {
        }
    }

    private Map<Integer, Node> cache;
    private int capacity;
    private Node dummyHead;
    private Node dummyTail;

    public Q146LRUCache1(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        dummyHead = new Node();
        dummyTail = new Node();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;

        Node node = cache.get(key);
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = value;
            moveToHead(node);
            return;
        }

        Node node = new Node(key, value);
        node.prev = dummyHead;
        node.next = dummyHead.next;
        dummyHead.next.prev = node;
        dummyHead.next = node;
        if (cache.size() >= capacity) {
            removeTail();
        }
        cache.put(key, node);
    }

    private void moveToHead(Node node) {
        // 删除目标节点
        Node nextNode = node.next;
        node.prev.next = nextNode;
        nextNode.prev = node.prev;

        // 移至头节点
        // 设置目标节点的prev
        node.prev = dummyHead;
        // 设置目标节点的next
        node.next = dummyHead.next;
        // 设置原头节点的后继节点的prev，即后续节点的prev为目标节点
        dummyHead.next.prev = node;
        // 设置头节点的next为目标节点
        dummyHead.next = node;
    }

    private void removeTail() {
        Node node = dummyTail.prev;
        if (node != dummyHead) {
            node.prev.next = dummyTail;
            dummyTail.prev = node.prev;
            cache.remove(node.key);
        }
    }

    public static void main(String[] args) {
        Q146LRUCache1 lruCache = new Q146LRUCache1(2);
        lruCache.put(1, 0);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
