public class Solution {
    public int[] twoSum(int[] nums, int target) {
//         List l=new ArrayList<Integer>();
//         List s=new ArrayList<Integer>();
//         List m=new ArrayList<Integer>();
//         int[] r=new int[2];
//         for(int i=0;i<nums.length;i++){ 
//             //if(nums[i]<=target){
//                 if(nums[i]<target/2){
//                     s.add(nums[i]);
//                     s.add(i);
//                 }else if(nums[i]==target/2){
//                     m.add(nums[i]);
// 		            m.add(i); 
//                 }else{
//                     l.add(nums[i]);
//                     l.add(i);
//                 }
//           // }
//         }
//     //     for(int j=0;j<l.size();j=j+2){
//     //         if(l.get(j)>=target) {
//     //             l.remove(l.get(j));
//     //             l.remove(l.get(j+1);
//     //     }
//     // }
//     if(m.size()>=4){
// 	      r[0]=(Integer)m.get(1)+1;
// 	      r[1]=(Integer)m.get(3)+1;
// 	  }else{
//          for(int j=0;j<s.size();j=j+2){
//             for(int k=0;k<l.size();k=k+2){
//               if(((Integer)s.get(j)+(Integer)l.get(k))==target){
//                     r[0]=(Integer)s.get(j+1)+1;
//                     r[1]=(Integer)l.get(k+1)+1;
//                 }
//             }
//         }
// 	  }
        int[] r=new int[2];
	    	for(int i=0;i<nums.length;i++){
	    		for(int j=i+1;j<nums.length;j++){
	    			if(nums[i]+nums[j]==target){
	    			// 	if(nums[i]>nums[j] && nums[i]>0){
	    				//	System.out.println("h");
	    				r[0]=i+1;
	    				r[1]=j+1;
	   // 				}else {
	   // 				//	System.out.println(nums[i]);
	   // 					r[0]=i+1;
		  //  				r[1]=j+1;
				// 		}
	    			}
	    		}
	    	}
        return r;
    }
}