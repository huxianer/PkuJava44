/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        // ��Ҫɾ���ڵ�ĺ�һ���ڵ㸲�ǵ�ǰ�ڵ㣬Ȼ��ɾ����һ���ڵ�
        node.val = node.next.val;
        node.next = node.next.next;
    }
}