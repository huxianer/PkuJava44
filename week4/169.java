public class Solution {
    public int majorityElement(int[] nums) {
       // if(nums==null) return null;
        Arrays.sort(nums);
        return(nums[nums.length/2]);
        
        
    }
}