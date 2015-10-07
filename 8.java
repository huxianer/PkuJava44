public class Solution {
    public int myAtoi(String str) {
        str = str.trim();
        Integer result = 0;
        if(str.length() == 0){
            return 0;
        }
        if(str.charAt(0) == '+' || str.charAt(0) == '-'){//首字符是正负号
            for(int i = 1; i < str.length(); i++){
                if(!Character.isDigit(str.charAt(i))){ // 遇到非法字符串就停止，返回之前的合法数字
                    if(i == 1){
                        return 0;
                    }
                    str = str.substring(0,i);
                    break;
                }
            }
            result = myInvalid(str);
        }else if(Character.isDigit(str.charAt(0))){// 首字符是数字
            for(int i = 0; i < str.length(); i++){
                if(!Character.isDigit(str.charAt(i))){ // 遇到非法字符串就停止，返回之前的合法数字
                    str = str.substring(0,i);
                    break;
                }
            }
            result = myInvalid(str);
        }
        return result;
    }
    // 通过捕捉越界异常来处理越界问题
    public Integer myInvalid(String str){
        Integer res = 0;
        try{
            res = Integer.parseInt(str);
        }catch(Exception e){
            if(str.charAt(0) == '-'){
                res = Integer.MIN_VALUE;
            }else{
                res = Integer.MAX_VALUE;
            }
        }finally{
            return res;
        }
    }
}