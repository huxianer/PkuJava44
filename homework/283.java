public class Solution {
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int fzero = 0;
        // 找到第一个0的下标，之前的数组都不用移动
        for(int i=0; i < length; i++){
            if(nums[i]==0){
                fzero=i;
                break;
            }
        }
		 // 记录0的个数
        int count = 0;
		// 将数组前移
        for(int i=fzero+1; i < length; i++){
            if(nums[i-1]==0){
                count++;
            }
            nums[i-count]=nums[i];
        }
		// 将最后几位赋值成0
        for(int i=0; i<count; i++){
            nums[length-i-1]=0;
        }
    }
}