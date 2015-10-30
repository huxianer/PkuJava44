public class Solution {
    public int reverse(int x) {
        
        String snum = Integer.toString(x);
        StringBuffer str = new StringBuffer();
        boolean flag = true; // 标志x是否>=0
        if(x >= 0){
            for(int i = snum.length()-1; i >= 0; i--){
                str.append(snum.charAt(i));
            }
            
        }else{
            for(int i = snum.length()-1; i > 0; i--){
                str.append(snum.charAt(i));
                flag = false;
            }
            
        }
        try{
            if(flag){
                return Integer.parseInt(str.toString());
            }else{
                return -Integer.parseInt(str.toString());
            }
        }catch(Exception e){// 越界之后，返回0，这里捕捉到的错误就是越界的错误
            return 0;
        }
        
    }
}