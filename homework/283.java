public class Solution {
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int fzero = 0;
        // �ҵ���һ��0���±֮꣬ǰ�����鶼�����ƶ�
        for(int i=0; i < length; i++){
            if(nums[i]==0){
                fzero=i;
                break;
            }
        }
		 // ��¼0�ĸ���
        int count = 0;
		// ������ǰ��
        for(int i=fzero+1; i < length; i++){
            if(nums[i-1]==0){
                count++;
            }
            nums[i-count]=nums[i];
        }
		// �����λ��ֵ��0
        for(int i=0; i<count; i++){
            nums[length-i-1]=0;
        }
    }
}