public class Solution {
    
    public int removeElement(int[] nums, int val) {
        int count = 0;
        int[] newNums = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(val != nums[i]){
                newNums[count++] = nums[i];
            }
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = newNums[i];
        }
        
        return count;
    }
}