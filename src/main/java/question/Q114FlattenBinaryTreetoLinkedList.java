package question;

import java.util.Stack;

/**
 * Created by ziheng on 2020/8/2.
 */
public class Q114FlattenBinaryTreetoLinkedList {
    /**
     * @Description: 114. 二叉树展开为链表
     *
     * 给定一个二叉树，原地将它展开为一个单链表。
     *
     *  
     *
     * 例如，给定二叉树
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * 将其展开为：
     *
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/8/2 上午12:29
     * @param
     * @return
     */
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void flatten(TreeNode root) {
        flattenHelper(root);
    }

    private static TreeNode flattenHelper(TreeNode root) {
        if (root == null) return null;

        // 暂存右子树
        TreeNode prevRight = root.right;
        // 将转换好的左子树赋值给右子树
        root.right = flattenHelper(root.left);
        // 置空左子树
        root.left = null;

        // 遍历当前右子树的最右节点
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        // 将当前最右节点的right指向整理好的右子树
        curr.right = flattenHelper(prevRight);
        return root;
    }

    public void flatten1(TreeNode root) {
        if (root == null) return;

        TreeNode dummy = new TreeNode();
        TreeNode current = dummy;

        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode node = stk.pop();
            current.right = new TreeNode(node.val);
            current = current.right;

            if (node.right != null) {
                stk.push(node.right);
            }
            if (node.left != null) {
                stk.push(node.left);
            }
        }

        root.left = null;
        root.right = dummy.right.right;
    }

    public void flattenMorris(TreeNode root) {
        if (root == null) return;

        TreeNode current = root;
        while (current != null) {
            if (current.left != null) {
                // 先继节点：寻找左子树中的最右节点
                TreeNode predecessor = current.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                // 按照前序遍历的修改，将当前节点的右子树接在predecessor的right
                predecessor.right = current.right;
                // 将当前的left接到right上
                current.right = current.left;
                current.left = null;
            }

            // 处理下一个节点，直到所有左子树都处理完毕
            current = current.right;
        }
    }
}
