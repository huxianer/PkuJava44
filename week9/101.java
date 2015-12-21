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
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return isSymmertric(root.left,root.right);
    }
    
    public boolean isSymmertric(TreeNode left,TreeNode right){
        if(left==null && right==null) return true;
        if(left==null || right==null) return false;
        if(left.val != right.val) return false;
        boolean leftR = isSymmertric(left.left,right.right);
        boolean rightR = isSymmertric(left.right,right.left);
        return rightR && leftR;
    }
}