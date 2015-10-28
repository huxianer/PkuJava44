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
    public List<Integer> inorderTraversal(TreeNode root) {
        /* �����㷨ûʲô���⣬����ʱ�ˣ�������Ϊʲô�ᳬʱ
        ArrayList<Integer> list = new ArrayList<Integer>(); // ��¼�������ı���ֵ
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>(); // ����ڵ���Ϣ
        stack.push(root); // �Ѹ��ڵ�ѹ��ջ��
        
        while(!stack.empty()){
            TreeNode node;
            do{
                node = stack.peek().left;
                if(node != null){
                    stack.push(node);
                }
            }while(node != null);
            //list.add(stack.peek().val); //�������ӽڵ�
            if(stack.peek().right == null){
                list.add(stack.pop().val);
                //stack.pop();
            }
            if(!stack.empty()){
                if(stack.peek().right != null){
                    list.add(stack.peek().val); //�Ȱѵ�ǰֵ����list
                    stack.push(stack.pop().right); //�ٰѵ�ǰ��㵯��ջ�����������ӽڵ�ѹջ
                }
            }
            
        }
        return list;*/
        // ���Լ�д����Ƚϣ����㷨���жϸ��٣�˼·�������������Լ����㷨�е��ظ�����
        ArrayList<Integer> list = new ArrayList<Integer>(); // ��¼�������ı���ֵ
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>(); // ����ڵ���Ϣ
        TreeNode node = root;
        while( node != null || !stack.empty()){
            
            while(node != null){
               stack.push(node);
               node = node.left;
            }
            node = stack.peek();
            list.add(node.val);
            stack.pop();
            node = node.right;
            
        }
        return list;
    }
}