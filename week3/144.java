/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorderR=new ArrayList<Integer>();
        if(root==null) return preorderR;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        //preorderR.add(root);
        while(!stack.isEmpty())
          {
             TreeNode temp = stack.pop();
             preorderR.add(temp.val);
             if(temp.right!=null)
                 stack.push(temp.right);
             if(temp.left!=null)
                 stack.push(temp.left);
         }
         return preorderR;
    }
}