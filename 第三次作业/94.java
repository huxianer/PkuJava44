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
        /* 觉得算法没什么问题，但超时了，不明白为什么会超时
        ArrayList<Integer> list = new ArrayList<Integer>(); // 记录二叉树的遍历值
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>(); // 保存节点信息
        stack.push(root); // 把根节点压入栈中
        
        while(!stack.empty()){
            TreeNode node;
            do{
                node = stack.peek().left;
                if(node != null){
                    stack.push(node);
                }
            }while(node != null);
            //list.add(stack.peek().val); //弹出左子节点
            if(stack.peek().right == null){
                list.add(stack.pop().val);
                //stack.pop();
            }
            if(!stack.empty()){
                if(stack.peek().right != null){
                    list.add(stack.peek().val); //先把当前值存入list
                    stack.push(stack.pop().right); //再把当前结点弹出栈，并将其右子节点压栈
                }
            }
            
        }
        return list;*/
        // 与自己写的相比较，该算法的判断更少，思路更清晰，简化了自己的算法中的重复步骤
        ArrayList<Integer> list = new ArrayList<Integer>(); // 记录二叉树的遍历值
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>(); // 保存节点信息
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