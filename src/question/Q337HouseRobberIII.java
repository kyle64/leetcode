package question;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by ziheng on 2020/8/5.
 */
public class Q337HouseRobberIII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * @Description: 337. 打家劫舍 III
     *
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 示例 1:
     *
     * 输入: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     *
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     * 示例 2:
     *
     * 输入: [3,4,5,1,3,null,1]
     *
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     *
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/8/5 上午1:21
     * @param
     * @return int
     */
    private Map<TreeNode, Integer> robNodeMap = new HashMap<>();
    private Map<TreeNode, Integer> unrobNodeMap = new HashMap<>();
    public int rob(TreeNode root) {
        // 动态规划
        // 任何一个节点能偷到的最大钱的状态可以定义为
        //
        // 当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
        // 当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
        //
        // 作者：reals
        // 链接：https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        dfs(root);
        return Math.max(robNodeMap.getOrDefault(root, 0), unrobNodeMap.getOrDefault(root, 0));
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        dfs(root.right);

        // 如果当前节点累加
        // rob(node) = node.val + unrob(left) + unrob(right)
        robNodeMap.put(root, unrobNodeMap.getOrDefault(root.left, 0)
                + unrobNodeMap.getOrDefault(root.right, 0)
                + root.val);
        // 如果不抢当前节点
        // 那么就是左子树和右子树的最大结果之和，子节点又有抢和不抢两种情况
        // unrob(node) = max(rob(left), unrob(left)) + max(rob(right), unrob(right))
        unrobNodeMap.put(root,
                Math.max(robNodeMap.getOrDefault(root.left, 0), unrobNodeMap.getOrDefault(root.left, 0))
                        + Math.max(robNodeMap.getOrDefault(root.right, 0), unrobNodeMap.getOrDefault(root.right, 0)));
    }
}
