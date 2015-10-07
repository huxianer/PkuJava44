public class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        if(s.length() == 0){
            return 0;
        }
        int spIndex = s.lastIndexOf(' ');
        if(spIndex < 0){
            // 此处错误了，应该直接返回字符串的长度
            //return s.length() - 1;
            return s.length();
        }else{
            return s.length() - 1 -spIndex;
        }
    }
}