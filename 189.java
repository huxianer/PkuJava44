public class Solution {
	//O(n)
    public void rotate(int[] nums, int k) {
        
        // ����ĳ���
        int length = nums.length;
        k %= length;
        if(k == 0){
            return ;
        }
        int[] newNums = new int[length];
        for(int i = 0; i < length; i++){
            newNums[i] = nums[(length - k + i) % length];
        }
        
        //nums = newNums; ���´����ԭ����������ֵ�Ǵ����
        for (int i = 0; i < length; i++) {
			nums[i] = newNums[i];
		}
    }
}