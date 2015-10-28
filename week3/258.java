public class Solution {
    public int addDigits(int num) {
        //树根的性质
        return 1 + (num-1)%9; 
    }
}