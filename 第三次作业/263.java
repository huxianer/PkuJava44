public class Solution {
    public boolean isUgly(int num) {
        if(num == 1){
            return true;
        }
        //�˷����ᳬʱ������������������ʱ�ᳬʱ
       /* for(int i = num/2; i >= 2; i--){
            if(num%i == 0){
                if(i == 2 || i == 3 || i== 5){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;*/
        // ���԰ѽ��㼯����2��3��5�����ֻ�����������������е����⼸�������ֵһ��Ϊ1
        if(num == 0){
            return false;
        }
        while(num%2 == 0){
            num /= 2;
        }
        while(num%3 == 0){
            num /= 3;
        }
        while(num%5 == 0){
            num /= 5;
        }
        if(num == 1){
            return true;
        }else{
            return false;
        }
    }
}