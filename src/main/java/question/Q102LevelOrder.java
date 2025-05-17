/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import javafx.util.Pair;
import structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wuziheng
 * @description
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 *
 * @date 2025/4/27 01:01
 **/
public class Q102LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        // 因为需要区分深度，记录node和depth
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 1));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            Integer depth = pair.getValue();

            // 当前层数组未创建时先创建
            List<Integer> layerList;
            if (res.size() < depth) {
                layerList = new ArrayList<>();
                res.add(layerList);
            } else {
                layerList = res.get(depth - 1);
            }
            // 将当前节点值写入数组
            layerList.add(node.val);

            // 左子树放入队列
            if (node.left != null) {
                queue.offer(new Pair<>(node.left, depth + 1));
            }

            // 右子树放入队列
            if (node.right != null) {
                queue.offer(new Pair<>(node.right, depth + 1));
            }
        }
        return res;
    }
}
