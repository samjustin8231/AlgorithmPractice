package com.sam.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sunyajun
 * @date 2020/3/1 7:57 PM
 */
public class LinkedListRing {


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 用一个set存放走过的节点，时间复杂度O(n)
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        // set
        Set<ListNode> set = new HashSet<ListNode>();

        ListNode p = head.next;
        while (p != null) {
            // 如果set没有，则加入；否则有环
            if (set.contains(p)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 快慢指针法，快慢指针从起点处一起走，如果有环必然相遇
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {

        return null;
    }
}
