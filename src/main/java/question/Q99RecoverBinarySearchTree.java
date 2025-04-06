package question;

import java.util.*;

/**
 * Created by ziheng on 2020/8/8.
 */
public class Q99RecoverBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * @Description: 99. 恢复二叉搜索树
     *
     * 二叉搜索树中的两个节点被错误地交换。
     *
     * 请在不改变其结构的情况下，恢复这棵树。
     *
     * 示例 1:
     *
     * 输入: [1,3,null,null,2]
     *
     *    1
     *   /
     *  3
     *   \
     *    2
     *
     * 输出: [3,1,null,null,2]
     *
     *    3
     *   /
     *  1
     *   \
     *    2
     * 示例 2:
     *
     * 输入: [3,1,4,null,null,2]
     *
     *   3
     *  / \
     * 1   4
     *    /
     *   2
     *
     * 输出: [2,1,4,null,null,3]
     *
     *   2
     *  / \
     * 1   4
     *    /
     *   3
     * 进阶:
     *
     * 使用 O(n) 空间复杂度的解法很容易实现。
     * 你能想出一个只使用常数空间的解决方案吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/8/9 上午12:12
     * @param
     * @return void
     */
    public void recoverTree(TreeNode root) {
        if (root == null) return;

        // 优化，隐式中序遍历
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pred = null;
        TreeNode currNode = root;

        // 中序遍历，迭代
        while (!stack.isEmpty() || currNode != null) {
            if (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            } else {
                currNode = stack.pop();
                if (pred != null && currNode.val < pred.val) {
                    // 将较小的值curr赋给y
                    // 必须这里就赋值，因为可能相邻，只有一次小于的情况
                    y = currNode;
                    if (x == null) {
                        // 第一次找到，存储偏大的节点pred
                        x = pred;
                    } else {
                        // 第二次找到，退出
                        break;
                    }
                }
                pred = currNode;
                currNode = currNode.right;
            }
        }

        swap(x, y);
    }

    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }


    private List<Integer> list = new ArrayList<>();
    private int curr = 0;

    public void recoverTree1(TreeNode root) {
        // 中序遍历
        if (root == null) return;

        inOrderAdd(root);
        Collections.sort(list);
        inOrderSwap(root);
    }

    private void inOrderAdd(TreeNode root) {
        if (root == null) return;
        inOrderAdd(root.left);
        list.add(root.val);
        inOrderAdd(root.right);
    }

    private void inOrderSwap(TreeNode root) {
        if (root == null) return;
        inOrderSwap(root.left);
        if (root.val != list.get(curr)) {
            root.val = list.get(curr);
        }
        curr++;
        inOrderSwap(root.right);
    }
}
