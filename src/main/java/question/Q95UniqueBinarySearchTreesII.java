package question;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by ziheng on 2020/7/21.
 */
public class Q95UniqueBinarySearchTreesII {
    public static class TreeNode {
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
     * @param
     * @return java.util.List<question.Q95UniqueBinarySearchTreesII.TreeNode>
     * @Description: 95. 不同的二叉搜索树 II
     * <p>
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：3
     * 输出：
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * 解释：
     * 以上的输出对应以下 5 种不同结构的二叉搜索树：
     * <p>
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= n <= 8
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @date 2020/7/21 下午5:26
     */
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 0) return res;

        // 递归，利用BST的特性，每次取一个数作为root，小于他的所有可能性组合在左边，大于的在右边
        // 1/2-N, 1-2/3-N, 1-3/4-N...
        res = generateTreesHelper(1, n);
        return res;
    }

    private static List<TreeNode> generateTreesHelper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        // 此时没有数字，将 null 加入结果中
        if (start > end) {
            res.add(null);
            return res;
        }

//        if (start == end) {
//            res.add(new TreeNode(start));
//            return res;
//        }

        for (int i = start; i <= end; i++) {
            // 假设都已经计算完毕
            List<TreeNode> resL = generateTreesHelper(start, i - 1);
            List<TreeNode> resR = generateTreesHelper(i + 1, end);

            for (TreeNode lnode : resL) {
                for (TreeNode rnode : resR) {
                    // 将左右的可能性拼接起来，形成最终的树
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    res.add(root);
                }
            }
        }

        return res;
    }


    // 依次求解从1到n的结果
    // 设当前在求解k的结果
    // 则可以设根值r为1到k分别的情况
    // 左子树的所有可能情况在dp[r - 1]中
    // 右子树的所有可能情况在dp[k - r]中
    // 备注: 右子树最终拷贝的时候启示值需要从r + 1开始
    //
    // 作者：infinite-15
    // 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/c-dong-tai-gui-hua-by-infinite-15-2/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public List<TreeNode> generateTreesDP(int n) {
        if (n == 0) return new ArrayList<>();

        // dp存储长度为i的所有情况
        // dp[i] 的值为所有左子树和右子树的组合
        // dp[i] = dp[0] ** dp[i - 1] + dp[1] ** (dp[i - 2] + offset1) + ... + dp[i - 1] ** (dp[0] + offsetN)
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<TreeNode>() {{
            add(null);
        }};

        // 举个例子。n = 100，此时我们求把 98 作为根节点的所有情况，根据之前的推导，我们需要长度是 97 的 [ 1 2 ... 97 ] 的所有情况作为左子树，长度是 2 的 [ 99 100 ] 的所有情况作为右子树。
        //
        // [ 1 2 ... 97 ] 的所有情况刚好是 [ 1 2 ... len ] ，已经求出来了。但 [ 99 100 ] 怎么办呢？我们只求了 [ 1 2 ] 的所有情况。答案很明显了，在 [ 1 2 ] 的所有情况每个数字加一个偏差 98，即加上根节点的值就可以了。
        //
        // 作者：windliang
        // 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-7/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        // 长度1 - n
        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<TreeNode>();
            // 将不同的数字作为根节点，只需要考虑到 len, i = root
            for (int i = 1; i <= len; i++) {
                int left = i - 1; // 左子树长度
                int right = len - i; // 右子树长度

                for (TreeNode leftTree : dp[left]) {
                    for (TreeNode rightTree : dp[right]) {
                        TreeNode root = new TreeNode(i);
                        // 共用左子树
                        root.left = leftTree;
                        // 右子树 = 相同长度的dp[right]中的子树 + offset偏差的拷贝
                        root.right = clone(rightTree, i);
                        dp[len].add(root);
                    }
                }
            }
        }

        return dp[n];
    }

    private TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }

    // 回溯会有重复解等问题，放弃
//    private static void dfs(int[] inputs, Deque<Integer> stack, TreeNode dummy, List<TreeNode> res) {
//        if (stack.size() == inputs.length) {
//            res.add(copyTree(dummy.right));
//            return;
//        }
//
//        for (int i = 0; i < inputs.length; i++) {
//            if (stack.contains(inputs[i])) continue;
//
//            stack.add(inputs[i]);
//            dummy.right = insertNode(dummy.right, new TreeNode(inputs[i]));
//            dfs(inputs, stack, dummy, res);
//            removeNode(dummy, inputs[i]);
//            stack.pollLast();
//        }
//    }
//
//    public static TreeNode insertNode(TreeNode root, TreeNode newNode) {
//        if (root == null) return newNode;
//
//        if (root.val > newNode.val) {
//            root.left = insertNode(root.left, newNode);
//        } else {
//            root.right = insertNode(root.right, newNode);
//        }
//
//        return root;
//    }
//
//    public static void removeNode(TreeNode root, int target) {
//        if (root != null) {
//            if (root.right != null && target == root.right.val) {
//                root.right = null;
//                return;
//            } else if (root.left != null && root.left.val == target) {
//                root.left = null;
//                return;
//            }
//
//            if (root.right != null && target > root.right.val) {
//                removeNode(root.right, target);
//            } else if (root.left != null && target < root.left.val) {
//                removeNode(root.left, target);
//            } else {
//                root = null;
//            }
//        }
//    }
//
//    public static TreeNode copyTree(TreeNode root) {
//        TreeNode res = new TreeNode(root.val);
//        if (root.left != null) {
//            res.left = copyTree(root.left);
//        }
//
//        if (root.right != null) {
//            res.right = copyTree(root.right);
//        }
//
//        return res;
//    }


    public static void main(String[] args) {
        List<TreeNode> list = generateTrees(3);
    }
}
