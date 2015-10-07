public class Solution {
    public boolean isHappy(int n) {
        //List<Integer> l=new ArrayList<Integer>();
        Set<Integer> s = new HashSet<Integer>();
        while (s.add(n)){
           int m=0;
        while(n!=0){
            m += Math.pow(n % 10,2);
            //m=(m-m%10)/10;
            n /=10;
        }
        n=m;
 /*       if(n==1){
         return true;
        }
        if(l.contains(n))
         return false;
        else
         l.add(n);*/
      }
     // return true;
     return n == 1;
    }
}