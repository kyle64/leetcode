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
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 *
 * @date 2025/4/28 23:09
 **/
public class Q437PathSum {
    int res = 0;
    Map<Long, Integer> preSumMap = new HashMap<>();
    public int pathSum(TreeNode root, int targetSum) {
        // 构建路径的前缀和，记录前缀和出现的频率
        // 在node的路径上，出现过prefix sum = current prefix sum - targetSum就算一次
        preSumMap.put(0L, 1);
        dfs(root, 0, targetSum);
        return res;
    }

    private void dfs(TreeNode node, long pathSum, long targetSum) {
        if (node == null) return;

        long currentPrefixSum = pathSum + (long) node.val;
        res += preSumMap.getOrDefault(currentPrefixSum - targetSum, 0);

        // 缓存当前前缀和
        preSumMap.put(currentPrefixSum, preSumMap.getOrDefault(currentPrefixSum, 0) + 1);
        dfs(node.left, node.val + pathSum, targetSum);
        dfs(node.right, node.val + pathSum, targetSum);
        // 移除当前前缀和，回溯
        preSumMap.put(currentPrefixSum, preSumMap.getOrDefault(currentPrefixSum, 0) - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10,
                new TreeNode(5,
                        new TreeNode(3, new TreeNode(3), new TreeNode(2)),
                        new TreeNode(2, null, new TreeNode(1))),
                new TreeNode(-3,
                        null,
                        new TreeNode(11)));
        System.out.println(new Q437PathSum().pathSum(root, 8));
    }
}
