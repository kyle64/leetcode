package question;

/**
 * Created by ziheng on 2020/7/19.
 */
public class Q124BinaryTreeMaximumPathSum {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    /**
     * @Description: 124. 二叉树中的最大路径和
     *
     * 给定一个非空二叉树，返回其最大路径和。
     *
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * 输出: 6
     * 示例 2:
     *
     * 输入: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 输出: 42
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/19 上午1:41
     * @param
     * @return int
     */

    int result;
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return result;
    }

    private int maxPathSumHelper(TreeNode root) {
        // 子树中的最大值
        // 一个节点的最大值判断时可以是自身+左右子树的递归，但递归时只能返回较大的子树的和
//        if (root.left == null && root.right == null) return root.val;
        if (root == null) return 0;

        int l = maxPathSumHelper(root.left);
        int r = maxPathSumHelper(root.right);

        // 一个节点的最大值有几种可能：
        // 1) l < 0, r < 0, 自身node.val
        // 2) l > 0, r > 0, node.val + l + r
        // 3) l > 0, r < 0, node.val + l
        // 4) l < 0, r > 0, node.val + r
        if (l <= 0 && r <= 0) {
            result = Math.max(root.val, result);
            return root.val;
        } else if (l > 0 && r > 0) {
            result = Math.max(root.val + l + r, result);
        } else {
            result = Math.max(root.val + Math.max(l, r), result);
        }

        return root.val + Math.max(l, r);
    }


    // 官方解答，更加简洁
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSumOfficial(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;

        // 如果小于0则路径不包含子树节点
        int leftPathSum = Math.max(0, dfs(root.left));
        int rightPathSum = Math.max(0, dfs(root.right));

        // 比较更新最大路径和，过当前节点的左右子树都可以取
        maxSum = Math.max(maxSum, root.val + leftPathSum + rightPathSum);
        // 过当前节点的最大单路径和, 左右子树只能取一边，或者都不取
        return root.val + Math.max(leftPathSum, rightPathSum);
    }
}
