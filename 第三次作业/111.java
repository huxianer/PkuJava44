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
    public int minDepth(TreeNode root) {
        // 由求最大深度的题做类比，依旧使用队列处理
        if(root == null){
            return 0;
        }
        int depth = 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int count = -1; // 用来记录当前节点的子节点的个数
        while(count != 0){ // 子节点个数为0时，说明是叶子结点
            depth++;
            for(int i = queue.size(); i > 0; i--){
                count = 0;
                if(queue.peek().left != null){
                    queue.add(queue.peek().left);
                    count++;
                }
                if(queue.peek().right != null){
                    queue.add(queue.peek().right);
                    count++;
                }
                queue.poll();
                if(count == 0){
                    break;
                }
            }
        }
        return depth;
    }
}