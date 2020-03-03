package com.sam.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunyajun
 * @date 2020/3/1 8:56 PM
 */
public class SortedListToBST {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        // 转化为有序数组构建二叉搜索树
        // 链表转为数组
        ListNode p = head;
        List<Integer> nodeList = new ArrayList<Integer>();
        while (p != null) {
            nodeList.add(p.val);
            p = p.next;
        }

        // 二分法递归构建二叉搜索树
        return build(nodeList, 0, nodeList.size() - 1);
    }

    public TreeNode build(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = build(list, left, mid - 1);
        node.right = build(list, mid + 1, right);
        return node;
    }

}
