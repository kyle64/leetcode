package question;

/**
 * Created by ziheng on 2020/7/10.
 */
public class Q235 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * @Description: 235. 二叉搜索树的最近公共祖先
     *
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 示例 1:
     *
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     * 示例 2:
     *
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     *  
     *
     * 说明:
     *
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/10 上午10:23
     * @param root
     * @param p
     * @param q
     * @return question.Q235.TreeNode
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        // swap p and q, 确保p.val < q.val
//        if (p.val > q.val) {
//            TreeNode temp = p;
//            p = q;
//            q = temp;
//        }

        // 我们来复习一下二叉搜索树（BST）的性质：
        //
        //节点 NN 左子树上的所有节点的值都小于等于节点 NN 的值
        //节点 NN 右子树上的所有节点的值都大于等于节点 NN 的值
        //左子树和右子树也都是 BST

        // 树的根节点的val是指定的值，直接返回
        if (root == p || root == q) {
            return root;
        }
        // p和q分别在当前的左边和右边，那么最小祖先一定是当前节点
        else if ((root.val > p.val && root.val < q.val) || (root.val < p.val && root.val > q.val)) {
            return root;
        } else if (root.val > p.val && root.val > q.val) {
            // 两个节点的值都比当前节点小，即存在左子树中
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // 两个节点的值都比当前节点大，即存在右子树中
            return lowestCommonAncestor(root.right, p, q);
        }

    }
}
