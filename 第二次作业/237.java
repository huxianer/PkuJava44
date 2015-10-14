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
        // 将要删除节点的后一个节点覆盖当前节点，然后删掉后一个节点
        node.val = node.next.val;
        node.next = node.next.next;
    }
}