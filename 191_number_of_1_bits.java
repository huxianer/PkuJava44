public class Solution {
    // you need to treat n as an unsigned value
	// ʱ�临�Ӷ�O(n)
    public int hammingWeight(int n) {
        //java���ܴ����޷�����
        /*Long k = (long) n;
        try{
            int count = 0;// ��¼1�ĸ���
            while(k != 0){
                if(k % 2 == 1){ // ����Ϊ1��˵����������һλ1
                    count++;
                }
                k /= 2;
            }
            return count;
        }catch(Exception e){
            return 0;
        }*/
        int count = 0;
        // ʹ��λ����
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