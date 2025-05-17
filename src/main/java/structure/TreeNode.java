/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package structure;

/**
 * @author wuziheng
 * @description
 * @date 2025/4/26 16:50
 **/
public class TreeNode {
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
