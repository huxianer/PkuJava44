public class Solution {
    public List<Integer> getRow(int rowIndex) {
        // List<List<Integer>> res=new ArrayList<List<Integer>>();
        List<Integer> ans=new ArrayList<Integer>();
        if(rowIndex<0) return ans;
        List<Integer> temp=new ArrayList<Integer>();
        temp.add(1);
        if (rowIndex==0) return temp;
        temp.add(1);
        if(rowIndex==1) return temp;
        //res.add(temp);
        for(int i=2;i<=rowIndex;i++){
            List<Integer> cur=new ArrayList<Integer>();
            cur.add(1);
            for(int j=0;j<temp.size()-1;j++)
            {
                cur.add(temp.get(j) + temp.get(j+1));
            }
            cur.add(1);
           // res.add(cur);
            temp=cur;
        }
       // ans=res.get(rowIndex);
         //return ans;
         return temp;
    }
}