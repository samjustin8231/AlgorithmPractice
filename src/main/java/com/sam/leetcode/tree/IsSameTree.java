package com.sam.leetcode.tree;

import com.sam.leetcode.linkedlist.SortedListToBST;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sunyajun
 * @date 2020/3/1 10:26 PM
 */
public class IsSameTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 深度优先
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        boolean ifLeftSame = isSameTree(p.left, q.left);
        boolean ifRightSame = isSameTree(p.right, q.right);

        return p.val == q.val && ifLeftSame && ifRightSame;
    }

    /**
     * 广度优先遍历
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            TreeNode p1 = queue.poll();
            TreeNode q1 = queue.poll();

            if (p1 == null && q1 == null) {
                continue;
            }
            if (p1 == null || q1 == null) {
                return false;
            }
            if (p1.val != q1.val) {
                return false;
            }
            queue.offer(p1.left);
            queue.offer(q1.left);
            queue.offer(p1.right);
            queue.offer(q1.right);
        }

        return true;
    }
}
