public class Solution {
    public int removeElement(int[] nums, int val) {
        int len=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==val) {len++;}
            else{
                nums[i-len]=nums[i];
            }
            
        }
        
        return nums.length-len;
    }
}