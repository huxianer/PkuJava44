public class Solution {
    public boolean isPalindrome(String s) {
        /**
         * 第一次错误原因，没有忽略大小写
         * 在得到str时，先转换成小写
         * 第二次错误原因，没有理解题目的意思，alphanumeric characters表示的是字母和数字
         * 只考虑了字母
         * 将isLetter改为isLetterOrDigit
         * */
        StringBuffer str =  new StringBuffer();
        // 首先去掉字符串中的非字母
        for(int i = 0; i < s.length(); i++){
            if(Character.isLetterOrDigit(s.charAt(i))){
                str.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        s = str.toString();
        // 字符串为空字符串或者只有一个字符时，认为是回文的
        if(s.length() == 0 || s.length() == 1){
            return true;
        }
        for(int i = 0, j = s.length()-1; i < j; i++, j--){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }
}