package question;

/**
 * Created by ziheng on 2020/7/15.
 */
public class Q236lowestCommonAncestor {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * @Description: 236. 二叉树的最近公共祖先
     *
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
     *
     *
     *
     *  
     *
     * 示例 1:
     *
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出: 3
     * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     * 示例 2:
     *
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出: 5
     * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
     *  
     *
     * 说明:
     *
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/15 上午4:00
     * @param root
     * @param p
     * @param q
     * @return question.Q236lowestCommonAncestor.TreeNode
     */

    TreeNode res = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        // dfs判断一个子树是否包含p或者q
        if (root == null) return false;

        // 递归假设已完成子树判断
        boolean isInLeftChild = dfs(root.left, p, q);
        boolean isInRightChild = dfs(root.right, p, q);

        if ((isInLeftChild && isInRightChild)
                || ((isInLeftChild || isInRightChild) && (root == p || root == q)) ) {
            res = root;
        }

        return isInLeftChild || isInRightChild || ((root == p || root == q));
    }

    //当我们用递归去做这个题时不要被题目误导，应该要明确一点
    //这个函数的功能有三个：给定两个节点 pp 和 qq
    //
    //如果 pp 和 qq 都存在，则返回它们的公共祖先；
    //如果只存在一个，则返回存在的一个；
    //如果 pp 和 qq 都不存在，则返回NULL
    //本题说给定的两个节点都存在，那自然还是能用上面的函数来解决
    //
    //具体思路：
    //（1） 如果当前结点 rootroot 等于 NULL，则直接返回 NULL
    //（2） 如果 rootroot 等于 pp 或者 qq ，那这棵树一定返回 pp 或者 qq
    //（3） 然后递归左右子树，因为是递归，使用函数后可认为左右子树已经算出结果，用 leftleft 和 rightright 表示
    //（4） 此时若leftleft为空，那最终结果只要看 rightright；若 rightright 为空，那最终结果只要看 leftleft
    //（5） 如果 leftleft 和 rightright 都非空，因为只给了 pp 和 qq 两个结点，都非空，说明一边一个，因此 rootroot 是他们的最近公共祖先
    //（6） 如果 leftleft 和 rightright 都为空，则返回空（其实已经包含在前面的情况中了）
    //
    //时间复杂度是 O(n)O(n)：每个结点最多遍历一次或用主定理，空间复杂度是 O(n)O(n)：需要系统栈空间
    //
    //作者：Wilson79
    //链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/c-jing-dian-di-gui-si-lu-fei-chang-hao-li-jie-shi-/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // 需要找到一个节点，p和q分别在两侧的子树中
        if (root == null) return null;
        if (p == root || q == root) return root;

        // 左子树中有p或者q节点
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        // 左右子树中p、q的最近公共祖先
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        // 左右各包含一个，返回当前节点
        // (left == p && right == q) || (left == q && right == p)
        if (left != null && right != null) {
            return root;
        }

        // 都在右子树中
        if (left == null) {
            return right;
        }

        // 都在左子树中
        if (right == null) {
            return left;
        }
        return root;
    }
}
