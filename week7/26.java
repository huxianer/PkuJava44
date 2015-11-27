public class Solution {
    public int removeDuplicates(int[] nums) {
        int count=1;//返回结果
        int len=nums.length;
        if(nums==null) return 0;
        if(len==1)  return 1;
        
        // for(int i=0;i<len-1;i++){
        //     // for(int j=i+1;j<len;j++){
        //     //     if(nums[i]==nums[j]) len--;
        //     // }
        //      if(nums[i]!=nums[i+1]) count++;
        // }
        int i=0,j=1;
        while(i<len&&j<len){
            if(nums[i]==nums[j]){
                j++;
            }else{
                i++;
                nums[i]=nums[j];
                j++;
            }
            
        }
        count=i+1;
        return count;
    }
}