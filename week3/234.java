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
        //当链表为空或者只有一个元素，肯定为null
        if(head==null || head.next==null) return true;
        ListNode slow=head;
        ListNode fast=head;
        ListNode mid=slow;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        if(fast!=null){
            slow.next=reverse(slow.next);
            slow=slow.next;
        }else{
            slow = reverse(slow);
        }
        while(slow!=null){
            if(head.val!=slow.val){
                return false;
            }
            slow=slow.next;
            head=head.next;
        }
        return true;
    }
      public  ListNode reverse(ListNode node){
        ListNode prev = null;

        while(node != null){
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
          
      }
    
}