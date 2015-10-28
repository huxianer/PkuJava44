public class Solution {
    public int singleNumber(int[] nums) {
    //     int res=0;
    //     if(nums.length==1){
    //         return nums[0];
    //     }
    //     for(int i=0;i<nums.length;i++){
    //         for(int j=i+1;j<nums.length;j++){
    //             if(nums[i]==nums[j]){
    //                 nums[i]=0;
    //                 nums[j]=0;
    //           }
    //     }
    // }
    // for(int m=0;m<nums.length;m++){
    //     if(nums[m]!=0){
    //         res=nums[m];
    //     }
    // }
    // return res;
       int i;
       for(i=0;i<nums.length-1;i++){
           int a=nums[i]^nums[i+1];
           nums[i+1]=a;
       }
       return nums[i];
    }
}