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
        if (NULL == root)
25         return 0;
26     queue <TreeNode *> que;
27     int nCount = 1;
28     int nDepth = 0;// 记录队列里面每一层上的元素
29 
30     que.push(root);
31     while(!que.empty()) {
32         TreeNode *pTemp = que.front();
33         que.pop();
34         nCount --;
35 
36         if (pTemp->left)
37             que.push(pTemp->left);
38         if (pTemp->right)
39             que.push(pTemp->right);
40         
41         if (nCount == 0) {
42             nDepth ++;
43             nCount = que.size();
44         }
45     }
46     return nDepth;
47 
        
    }
}