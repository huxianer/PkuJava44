public class Solution {
    public int reverse(int x) {
        
        String snum = Integer.toString(x);
        StringBuffer str = new StringBuffer();
        boolean flag = true; // ��־x�Ƿ�>=0
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
        }catch(Exception e){// Խ��֮�󣬷���0�����ﲶ׽���Ĵ������Խ��Ĵ���
            return 0;
        }
        
    }
}