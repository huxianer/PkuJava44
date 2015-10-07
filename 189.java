public class Solution {
	//O(n)
    public void rotate(int[] nums, int k) {
        
        // 数组的长度
        int length = nums.length;
        k %= length;
        if(k == 0){
            return ;
        }
        int[] newNums = new int[length];
        for(int i = 0; i < length; i++){
            newNums[i] = nums[(length - k + i) % length];
        }
        
        //nums = newNums; 导致错误的原因就是这个赋值是错误的
        for (int i = 0; i < length; i++) {
			nums[i] = newNums[i];
		}
    }
}