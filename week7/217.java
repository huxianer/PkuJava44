{
    public boolean containsDuplicate(int[] nums) {
        int len=nums.length;
        if(len<2) return false;
        boolean ans=false;
       //方法一：排序，遍历
        // Arrays.sort(nums);
        // for(int i=0;i<len-1;i++){
        //     if(nums[i]==nums[i+1]) ans=true;
        // }
        //用HashSet
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i: nums){
        if(!set.add(i)){
            ans=true;
        }
     }
        return ans;
    }
}