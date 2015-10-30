public class Solution {
    public boolean isUgly(int num) {
        if(num == 1){
            return true;
        }
        //此方法会超时，估计是遇到大素数时会超时
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
        // 所以把焦点集中在2，3，5，如果只包含这三个素因子中的任意几个，最后值一定为1
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