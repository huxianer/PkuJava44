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
        /*���ֽⷨ��˼·�ǣ�ͨ����һ������������ĳ��ȣ�Ȼ��������ǰ�벿����ջ�������벿�ֱȽϣ�
        ����ͬ���ջ�����ջΪ��˵��s�����ǻ��ĵģ������ǻ��ĵ�*/
        if(head == null){
            return true;
        }
        int length = 0;// ������ĳ���
        ListNode pointer = head; 
        while(pointer != null){
            length++;
            pointer = pointer.next;
        }
        pointer = head;
        LinkedList<ListNode> stack = new LinkedList<ListNode>();
        // ��ǰ�벿����ջ
        if(length%2 == 0){
            for(int i = 1; i <= length/2; i++){
                stack.push(pointer);
                pointer = pointer.next;
            }
        }else{
            // ����������������м����������ջ
            for(int i = 1; i <= length/2; i++){
                stack.push(pointer);
                pointer = pointer.next;
            }
            pointer = pointer.next; // �м�������ñȽ�
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