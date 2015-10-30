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
    public int maxDepth(TreeNode root) {
        // 递归
       /* if(root == null){
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if(leftDepth > rightDepth){
            return leftDepth + 1;
        }else{
            return rightDepth + 1;
        }*/
        // 非递归 使用队列
        if(root == null){
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int depth = 0; //记录深度
        queue.add(root); //先把根节点加入队列
        int count = 1; // 记录每一层节点的个数，因为root已存在所以先赋值1
        while(count > 0){
            depth++;
            for(int i = count; i > 0; i--){
                
                if(queue.peek().left != null){
                    queue.add(queue.peek().left);
                }
                if(queue.peek().right != null){
                    queue.add(queue.peek().right);
                }
                queue.poll(); // 弹出队首元素
            }
            count = queue.size();
        }
        return depth;

    }

}