public class Solution {
    public String reverseWords(String s) {
       String[] words = s.split(" ");
        int n=words.length;
        String[] res=new String[n];
        for(int i=0;i<n;i++){
            res[i]=words[n-i-1];
        }
      // return res;
      StringBuffer sb = new StringBuffer("");
      //String newStr = null;
      String res1=s.trim();
      if(res.length==0) return s.trim(); 
      for(int j = 0;j< res.length; j++){
          if(res[j].length()>0){
               sb. append(res[j]).append(" "); 
          }
        }
       // newStr = sb.toString();
     // }
        String  newStr = sb.toString();
      return newStr.trim();
    }
    
}