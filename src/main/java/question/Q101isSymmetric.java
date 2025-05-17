/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wuziheng
 * @description
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * @date 2025/4/27 00:20
 **/
public class Q101isSymmetric {
    public boolean isSymmetric(TreeNode root) {
        // 递归
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null && q != null) {
            return false;
        }

        if (p != null && q == null) {
            return false;
        }

        boolean symmetricLeft = dfs(p.left, q.right);
        boolean symmetricRight = dfs(p.right, q.left);
        return symmetricLeft && symmetricRight && p.val == q.val;
    }

    public boolean isSymmetricIterator(TreeNode root) {
        // 迭代
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            // 同时为null
            if (left == null && right == null) {
                continue;
            }

            // 仅一侧为null
            if (left == null || right == null) {
                return false;
            }

            // val不相等
            if (left.val != right.val) {
                return false;
            }

            // p.left和q.right加入队列
            queue.offer(left.left);
            queue.offer(right.right);
            // p.right和q.left加入队列
            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }
}
