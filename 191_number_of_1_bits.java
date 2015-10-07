public class Solution {
    // you need to treat n as an unsigned value
	// 时间复杂度O(n)
    public int hammingWeight(int n) {
        //java不能处理无符号数
        /*Long k = (long) n;
        try{
            int count = 0;// 记录1的个数
            while(k != 0){
                if(k % 2 == 1){ // 余数为1就说明二进制有一位1
                    count++;
                }
                k /= 2;
            }
            return count;
        }catch(Exception e){
            return 0;
        }*/
        int count = 0;
        // 使用位运算
        while(n != 0){
            //if(n & 1 == 1){
                //count++;
            //}
            count += n&1;
            n = n >>> 1;
        }
        return count;

    }
}