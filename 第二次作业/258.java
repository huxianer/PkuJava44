public class Solution {
    public int addDigits(int num) {
        //使用求数字根的公式b=(a-1)%9+1 就可以不用循环
        int root = (num - 1) % 9 + 1;
        return root;
    }
}