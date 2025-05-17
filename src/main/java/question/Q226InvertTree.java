/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import structure.TreeNode;

/**
 * @author wuziheng
 * @description
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * 示例 2：
 *
 *
 *
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点数目范围在 [0, 100] 内
 * -100 <= Node.val <= 100
 * @date 2025/4/26 22:46
 **/
public class Q226InvertTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        // 递归，假设左右子树已经swap过，仅需要反转当前的left和right即可
        TreeNode tmp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = tmp;
        return root;
    }


}
