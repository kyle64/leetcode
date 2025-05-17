/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author wuziheng
 * @description
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,2,3,null,5,null,4]
 *
 * 输出：[1,3,4]
 *
 * 解释：
 *
 *
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,4,null,null,null,5]
 *
 * 输出：[1,3,4,5]
 *
 * 解释：
 *
 *
 *
 * 示例 3：
 *
 * 输入：root = [1,null,3]
 *
 * 输出：[1,3]
 *
 * 示例 4：
 *
 * 输入：root = []
 *
 * 输出：[]
 *
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 *
 * @date 2025/4/27 16:56
 **/
public class Q199RightSideView {
    public List<Integer> rightSideViewIterator(TreeNode root) {
        // 层序遍历的最右值
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 当前层节点数
            int layerLength = queue.size();
            for (int i = 0; i < layerLength; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                // 保存当前层最右节点
                if (i == layerLength - 1) {
                    res.add(node.val);
                }
            }
        }

        return res;
    }

    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideViewDfs(TreeNode root) {
        // 采用先序遍历的镜像，按照root -> right -> left的顺序遍历
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) return;

        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
        if (depth == res.size()) {
            res.add(root.val);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }
}
