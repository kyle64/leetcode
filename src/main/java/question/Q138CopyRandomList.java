/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuziheng
 * @description 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 *
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 *
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 *
 * 返回复制链表的头节点。
 *
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 *
 *
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 *
 *
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 *
 *
 * 提示：
 *
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random 为 null 或指向链表中的节点。
 *
 * @date 2025/4/19 23:46
 **/
public class Q138CopyRandomList {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 维护旧链表节点 -> 新链表节点的映射
    Map<Node, Node> nodeMap = new HashMap<>();
    public Node copyRandomList(Node head) {
        // 难点在于遍历时next和random指向的节点可能未创建

        if (head == null) return head;

        // 先遍历一遍创建映射
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.val);
            nodeMap.put(current, newNode);
            current = current.next;
        }

        // 再次遍历设置next和random
        current = head;
        while (current != null) {
            Node newNode = nodeMap.get(current);
            newNode.next = nodeMap.get(current.next);
            newNode.random = nodeMap.get(current.random);

            current = current.next;
        }

        return nodeMap.get(head);
    }

    // 维护旧链表节点 -> 新链表节点的映射
    public Node copyRandomListRecursion(Node head) {
        if (head == null) return head;

        if (nodeMap.containsKey(head)) {
            return nodeMap.get(head);
        }

        Node newNode = new Node(head.val);
        nodeMap.put(head, newNode);
        newNode.next = copyRandomListRecursion(head.next);
        newNode.random = copyRandomListRecursion(head.random);

        return newNode;
    }
}
