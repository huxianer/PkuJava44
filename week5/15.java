public class Solution {
    List<List<Integer>> ans=new ArrayList<List<Integer>>();
   
    public List<List<Integer>> threeSum(int[] nums) {
       int len=nums.length;
       Arrays.sort(nums);
       if(len<3) return ans;
        // for(int i=0;i<len-1;i++){
        //     if(nums[0]>0) break;
        //     //if(nums[i]==0) count++;
        //     if(i>0&&nums[i]==nums[i-1])continue;
        //   // for (int j=i+1;j<len;j++){
        //      for (int j=len-1;j>i;j--){
        //          if(nums[len-1]<0) break;
        //       // if(j<len-1&&nums[j]!=nums[j+1])continue;
        //       //for(int k=i+1;k<j;k++){
        //          int temp=nums[i]+nums[j];
        //          int res = bs(temp, nums, i+1, j-1);
        //          if(res>0){
        //              List<Integer> l=new ArrayList<Integer>();
        //              l.add(nums[i]);
        //              l.add(nums[res]);
        //              l.add(nums[j]);
        //              ans.add(l);
        //          }
        //     }
        // }
         for (int i = 0; i < len-2; i++) {  
            if (i > 0 && nums[i] == nums[i-1]) continue;  
            bs(nums, i+1, len-1, nums[i]); //寻找两个数与num[i]的和为0  
        }  
        return ans;
    }
    public  void bs(int[] num, int begin, int end, int target) {  
        int l = begin, r = end;  
        while (l < r) {  
            if (num[l] + num[r] + target == 0) {  
                List<Integer> b = new ArrayList<Integer>();  
                b.add(target);  
                b.add(num[l]);  
                b.add(num[r]);  
                ans.add(b); //放入结果集中  
                while (l < r && num[l] == num[l+1]) l++;  
                while (l < r && num[r] == num[r-1]) r--;  
                l++;  
                r--;  
            } else if (num[l] + num[r] + target < 0) {  
                l++;  
            } else {  
                r--;  
            }  
        }  
    }
    // public static int bs(int temp,int[] nums, int l, int r){
    //     if(nums[l]>-temp||nums[r]<-temp) return -1;
    //     while(l <= r) {
    //         if(nums[l]==nums[r]&&nums[l] == -temp){
    //             return l;
    //         }else{
    //         int m = (l+r)/2;
    //         if(nums[m] == -temp) return m;
    //         else if(nums[m] < -temp) l = m+1;
    //         else r = m-1;
    //         }
    //     }
    //     return -1;
    // }
}