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
        // �ݹ�
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
        // �ǵݹ� ʹ�ö���
        if(root == null){
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int depth = 0; //��¼���
        queue.add(root); //�ȰѸ��ڵ�������
        int count = 1; // ��¼ÿһ��ڵ�ĸ�������Ϊroot�Ѵ��������ȸ�ֵ1
        while(count > 0){
            depth++;
            for(int i = count; i > 0; i--){
                
                if(queue.peek().left != null){
                    queue.add(queue.peek().left);
                }
                if(queue.peek().right != null){
                    queue.add(queue.peek().right);
                }
                queue.poll(); // ��������Ԫ��
            }
            count = queue.size();
        }
        return depth;

    }

}