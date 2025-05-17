/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import structure.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuziheng
 * @description
 * @date 2025/4/12 17:15
 **/
public class Q234IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        // 遍历链表后，反转链表，再次遍历链表，最后判断输出，时间复杂度O(3N) = O(N)，空间复杂度O(N)
        if (head == null || head.next == null) return true;

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        ListNode current = head;
        while (current != null) {
            sb1.append(current.val);
            current = current.next;
        }

        ListNode newHead = reverseList(head);
        current = newHead;
        while (current != null) {
            sb2.append(current.val);
            current = current.next;
        }

        return sb1.toString().equals(sb2.toString());
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }
        return prev;
    }

    public boolean isPalindromeArray(ListNode head) {
        // 转成数组。时间复杂度O(N)，空间复杂度O(N)
        List<Integer> valList = new ArrayList<>();
        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            valList.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int left = 0, right = valList.size() - 1;
        while (left < right) {
            if (valList.get(left) != valList.get(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] inputs = new int[] {1, 2, 2, 1};
        ListNode head = ListNode.constructList(inputs);

        System.out.println(new Q234IsPalindrome().isPalindrome(head));;
    }
}
