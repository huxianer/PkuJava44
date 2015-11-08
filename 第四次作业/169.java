public class Solution {
    public int majorityElement(int[] nums) {
        int size = nums.length;
        // ׼��ʹ��hashmap���洢ÿ����(key)�ĸ���(value)
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<size; i++){
            if(map.containsKey(nums[i])){ // ���map���������(key)
                map.put(nums[i],map.get(nums[i])+1); //�͸����ĸ���(value) +1
            }else{                      // û�������
                map.put(nums[i],1);     // �͸����ĸ�����Ϊ1
            }
            if(map.get(nums[i]) > size/2){ //�����ĸ����ﵽn/2 �ͷ���
                return nums[i];
            }
        }
        return 0;
    }
}