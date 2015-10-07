public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        /*String s=String.valueOf(n);
        long m=Long.parseLong(n);*/
        int r=0;
        while(n!=0){
			r += n & 1;
			n >>>= 1;
		}
     /*   String str="";
        int sum=0;
        while(r!=0){
           str= (r%2)+str;
           r=r/2;
        }
        String obj="1";
        while(str.indexOf(obj)!=-1){
            sum++;
            str=str.substring(str.indexOf(obj)+1);
        }
        return sum;*/
        return r;
    }
}