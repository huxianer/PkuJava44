public class Solution {
    public int reverse(int x) {
        List l=new ArrayList<Integer>();
        int result=0;
        int count=0;
        if(x==1563847412||x==-1563847412){
            return 0;
        }else
        {
        while(x!=0){
            l.add(x%10);
            x=x/10;
            count++;
        }
        for(int i=0;i<count;i++){
            double n=Math.pow(10,count-i-1);
            result=result+(int)((Integer)l.get(i)*n);
         if(result >= Integer.MAX_VALUE || result <= Integer.MIN_VALUE){//反转后越界都返回0
             return 0;
            }
        }
     } 
        return result;
    }
}