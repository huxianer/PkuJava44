public class Solution {
    public int singleNumber(int[] nums) {
        
        if(nums == null || nums.length == 0){
 5             return 0;
 6         }
 7         int result = nums[0];
 8         
 9         for(int i = 1; i < nums.length; i++){
10             result = result ^ nums[i];
11         }
12         return result;
        
    }
}