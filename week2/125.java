public class Solution {
    public boolean isPalindrome(String s) {
        // //1、用正则表达式预处理字符串
        // String testreg = "[^a-zA-Z\\s]";
        // Pattern matchsip = Pattern.compile(testreg);
        // Matcher mp = matchsip.matcher(s);
        // s = mp.replaceAll("");
        // s = s.replaceAll(" ","").toLowerCase();
        // String str=new StringBuffer(s).reverse().toString();
        // if(s.equals(str)) return true;
        // return false;
        //if(s.equals(" ")) return true; 
        //2、将字符串转化成字符数组
        s = s.replaceAll(" ","").toLowerCase();
        char [] str = s.toCharArray();
        for(int i=0;i<str.length;i++){
          if((str[i]<97||str[i]>122)&&(str[i]<48||str[i]>57))
          str[i]=' ';
        // if(!Character.isLetter(str[i]) && !Character.isDigit(str[i]))
        //     str[i]=' ';
        }
        String s1=new String(str);
       s1=s1.replaceAll(" ","");
       String s2=new StringBuffer(s1).reverse().toString();
       if(!s1.equals(s2)) return false;
       return true;
    }
}