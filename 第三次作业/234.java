/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        /*此种解法的思路是，通过第一遍遍历获得链表的长度，然后把链表的前半部分入栈，再与后半部分比较，
        若相同则出栈，最后栈为空说明s链表是回文的，否则不是回文的*/
        if(head == null){
            return true;
        }
        int length = 0;// 单链表的长度
        ListNode pointer = head; 
        while(pointer != null){
            length++;
            pointer = pointer.next;
        }
        pointer = head;
        LinkedList<ListNode> stack = new LinkedList<ListNode>();
        // 把前半部分入栈
        if(length%2 == 0){
            for(int i = 1; i <= length/2; i++){
                stack.push(pointer);
                pointer = pointer.next;
            }
        }else{
            // 如果长度是奇数，中间的数不用入栈
            for(int i = 1; i <= length/2; i++){
                stack.push(pointer);
                pointer = pointer.next;
            }
            pointer = pointer.next; // 中间的数不用比较
        }
        while(pointer != null){
            if(stack.peek().val == pointer.val){
                stack.pop();
            }
            pointer = pointer.next;
        }
        if(stack.size() == 0){
            return true;
        }
        return false;
        
    }
}