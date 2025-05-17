/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import structure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wuziheng
 * @description
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * @date 2025/4/26 22:11
 **/
public class Q94InorderTraversal {
    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return res;
        inorderTraversal(root.left);
        res.add(root.val);
        inorderTraversal(root.right);
        return res;
    }

    public List<Integer> inorderTraversalStk(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // 使用栈对root进行压栈
        Stack<TreeNode> stk = new Stack<>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }

            TreeNode node = stk.pop();
            res.add(node.val);
            // 指向当前右子树
            root = node.right;
        }

        return res;
    }
}
