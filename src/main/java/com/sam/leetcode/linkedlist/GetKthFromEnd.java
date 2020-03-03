package com.sam.leetcode.linkedlist;

/**
 * @author sunyajun
 * @date 2020/3/1 8:34 PM
 */
public class GetKthFromEnd {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        
        // 第一个指针先走k步
        ListNode fast = head;
        ListNode slow = head;

        while (k > 0) {
            fast = fast.next;
            k--;
        }

        // 第二个指针从头开始，两个指针一起走；当第一个指针到头时，第二个指针对应的就是倒数第k个
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
