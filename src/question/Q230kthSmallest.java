package question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2020/7/13.
 */
public class Q230kthSmallest {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * @Description: 230. 二叉搜索树中第K小的元素
     *
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     *
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     *
     * 示例 1:
     *
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 1
     * 示例 2:
     *
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * 输出: 3
     * 进阶：
     * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/13 下午3:29
     * @param root
     * @param k
     * @return int
     */
    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历
        List<TreeNode> nodeList = new ArrayList<>();
        inOrderTraversal(root, nodeList);
        return nodeList.get(k - 1).val;
    }

    public void inOrderTraversal(TreeNode node, List<TreeNode> nodeList) {
        if (node != null) {
            inOrderTraversal(node.left, nodeList);
            nodeList.add(node);
            inOrderTraversal(node.right, nodeList);
        }
    }
}
