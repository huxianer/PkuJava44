public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1;
        int j=n-1;
        while(i>=0 && j>=0){
            if(nums2[j]>nums1[i]){
                nums1[i+j+1]=nums2[j];
                j--;
            }else{
                nums1[i+j+1]=nums1[i];
                i--;
            }
        }
        if(i<0){
            //nums1[0]=nums2[0];
            for(int k=j; k>=0; k--){
                nums1[k]=nums2[k];
            }
        }
    }
}