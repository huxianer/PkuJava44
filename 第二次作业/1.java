public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // ��һ��O(n^2) �����鲻�ϱ��� 123ms
        /*int[] result = new int[2];
        lable:
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                if(target == nums[i] + nums[j]){
                    result[0] = i + 1;
                    result[1] = j + 1;
                    break lable;
                }
            }
        }
        return result;*/
        // �ڶ���O(n)  �ڱ��������ͬʱ������ֵ�洢��hashmap�У���ͬʱѰ�ҷ�����������
        int[] result = new int[2];
        // key=nums[i],value=index
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                // �����map���ҵ��˲�ֵ��˵��ǰһ������index��С
                result[0] = map.get(target - nums[i]) + 1;
                result[1] = i + 1;
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}