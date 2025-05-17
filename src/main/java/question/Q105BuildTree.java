/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuziheng
 * @description
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 *
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *
 *
 * 提示:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 *
 * @date 2025/4/28 16:34
 **/
public class Q105BuildTree {
    Map<Integer, Integer> inorderMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 前序遍历 root -> left -> right
        // 中序遍历 left -> root -> right
        // 因此preorder[0]一定是root，而根据root的值，可以在确认inorder数组中确认左子树和右子树的范围
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[preLeft]);
        int rootIdx = inorderMap.get(node.val);
        // 左子树的长度
        int leftChileLen = rootIdx - inLeft;

        node.left = buildTreeHelper(preorder, inorder, preLeft + 1, preLeft + leftChileLen, inLeft, rootIdx - 1);

        node.right = buildTreeHelper(preorder, inorder, preLeft + leftChileLen + 1, preRight, rootIdx + 1, inRight);

        return node;
    }

    public static void main(String[] args) {
        int[] preorder = new int[] {3,9,20,15,7};
        int[] inorder = new int[] {9,3,15,20,7};
        System.out.println(new Q105BuildTree().buildTree(preorder, inorder));
    }
}
