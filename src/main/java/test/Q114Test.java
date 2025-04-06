package test;

import question.Q114FlattenBinaryTreetoLinkedList;

/**
 * Created by ziheng on 2020/8/2.
 */
public class Q114Test {
    public static void main(String[] args) {
        Q114FlattenBinaryTreetoLinkedList.TreeNode root = new Q114FlattenBinaryTreetoLinkedList.TreeNode(1);
        root.left = new Q114FlattenBinaryTreetoLinkedList.TreeNode(2);
        root.left.left = new Q114FlattenBinaryTreetoLinkedList.TreeNode(3);
        root.left.right = new Q114FlattenBinaryTreetoLinkedList.TreeNode(4);
        root.right = new Q114FlattenBinaryTreetoLinkedList.TreeNode(5);
        root.right.right = new Q114FlattenBinaryTreetoLinkedList.TreeNode(6);

        Q114FlattenBinaryTreetoLinkedList.flatten(root);

        Q114FlattenBinaryTreetoLinkedList.TreeNode curr = root;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
    }
}
