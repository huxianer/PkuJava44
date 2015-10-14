public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 第一种O(n^2) 将数组不断遍历 123ms
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
        // 第二种O(n)  在遍历数组的同时，将数值存储到hashmap中，并同时寻找符合条件的数
        int[] result = new int[2];
        // key=nums[i],value=index
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                // 如果在map中找到了差值，说明前一个数的index较小
                result[0] = map.get(target - nums[i]) + 1;
                result[1] = i + 1;
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}