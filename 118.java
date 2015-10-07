public class Solution {
    public List<List<Integer>> generate(int numRows) {
  /*      System.out.println("[");
        for(int i=1;i<=numRows;i++){
        for(int j=numRows;j<1;j--){
            System.out.print(" ");
        }
        System.out.print("[");
        for(int k=1;k<=numRows;k++){
        }
        }*/
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        if(numRows<=0) return res;
        List<Integer> temp=new ArrayList<Integer>();
      //  res.add(1);
        temp.add(1);
        res.add(temp);
        for(int i=2;i<=numRows;i++){
            List<Integer> cur=new ArrayList<Integer>(); 
            cur.add(1);
            for(int j=0;j<temp.size()-1;j++){
                cur.add(temp.get(j) + temp.get(j+1));
            }
            cur.add(1);
            res.add(cur);
            temp=cur;
        }
        return res;
    }
}