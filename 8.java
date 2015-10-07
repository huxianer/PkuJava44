public class Solution {
    public int myAtoi(String str) {
        str = str.trim();
        Integer result = 0;
        if(str.length() == 0){
            return 0;
        }
        if(str.charAt(0) == '+' || str.charAt(0) == '-'){//���ַ���������
            for(int i = 1; i < str.length(); i++){
                if(!Character.isDigit(str.charAt(i))){ // �����Ƿ��ַ�����ֹͣ������֮ǰ�ĺϷ�����
                    if(i == 1){
                        return 0;
                    }
                    str = str.substring(0,i);
                    break;
                }
            }
            result = myInvalid(str);
        }else if(Character.isDigit(str.charAt(0))){// ���ַ�������
            for(int i = 0; i < str.length(); i++){
                if(!Character.isDigit(str.charAt(i))){ // �����Ƿ��ַ�����ֹͣ������֮ǰ�ĺϷ�����
                    str = str.substring(0,i);
                    break;
                }
            }
            result = myInvalid(str);
        }
        return result;
    }
    // ͨ����׽Խ���쳣������Խ������
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