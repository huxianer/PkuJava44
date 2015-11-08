public class Solution {
    public int majorityElement(int[] nums) {
        int size = nums.length;
        // 准备使用hashmap来存储每个数(key)的个数(value)
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<size; i++){
            if(map.containsKey(nums[i])){ // 如果map中有这个数(key)
                map.put(nums[i],map.get(nums[i])+1); //就给它的个数(value) +1
            }else{                      // 没有这个数
                map.put(nums[i],1);     // 就给它的个数设为1
            }
            if(map.get(nums[i]) > size/2){ //当它的个数达到n/2 就返回
                return nums[i];
            }
        }
        return 0;
    }
}