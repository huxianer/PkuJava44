public class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if(len == 1){
            return ;
        }
        k = k%len; 
       int[] lin = new int[k];
       for(int i=0;i<k;i++){
           lin[i] = nums[len-k+i];
       }
       int cc =0;
       for(int p=len-k;p<len;p++){
           int j = nums[(k+p)%len];
           nums[(k+p)%len] = lin[cc];
           cc++;
                     
           int p1 = (k+p)%len;
           while(p1+k<len){
                          
               int i = nums[p1+k];
               nums[p1+k] = j;
               j = i;
               p1 = p1+k   ;
           }
            
       }
        
    }
}