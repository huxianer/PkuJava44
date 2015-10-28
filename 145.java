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
   public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        produce(result, root);
        return  result;
    }

    private void produce(List<Integer> result, TreeNode node) {
        if (node == null)
            return;
        produce(result, node.left);
        produce(result, node.right);
        result.add(node.val);
    }
}