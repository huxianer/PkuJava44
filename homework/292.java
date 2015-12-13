public class Solution {
    public boolean canWinNim(int n) {
        //经过分析发现只有当n是4的倍数时，没有赢得可能性
        if(n%4==0){
            return false;
        }
        return true;
    }
}