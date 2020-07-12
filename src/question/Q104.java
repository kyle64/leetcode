package question;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by ziheng on 2020/7/9.
 */
public class Q104 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // dfs 递归
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 层序遍历 思路
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;

        int depth = 0;
        Deque<Pair<TreeNode, Integer>> stk = new ArrayDeque<>();
        stk.add(new Pair<>(root, 1));

        while (!stk.isEmpty()) {
            Pair<TreeNode, Integer> pair = stk.poll();
            assert pair != null;
            depth = Math.max(depth, pair.getValue());

            if (pair.getKey() != null) {
                if (pair.getKey().left != null)
                    stk.add(new Pair<>(pair.getKey().left, pair.getValue() + 1));
                if (pair.getKey().right != null)
                    stk.add(new Pair<>(pair.getKey().right, pair.getValue() + 1));
            }
        }

        return depth;
    }
}
