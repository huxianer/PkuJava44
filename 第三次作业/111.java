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
        // ���������ȵ�������ȣ�����ʹ�ö��д���
        if(root == null){
            return 0;
        }
        int depth = 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int count = -1; // ������¼��ǰ�ڵ���ӽڵ�ĸ���
        while(count != 0){ // �ӽڵ����Ϊ0ʱ��˵����Ҷ�ӽ��
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