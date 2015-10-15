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
          vector<int> v;
        while(head)
        {
            v.push_back(head->val);
            head = head->next;
        }
        for(int i = 0, j = v.size()-1; i < j; i ++, j --)
        {
            if(v[i] != v[j])
                return false;
        }
        return true;
        
    }
}