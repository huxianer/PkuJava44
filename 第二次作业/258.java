public class Solution {
    public int addDigits(int num) {
        //ʹ�������ָ��Ĺ�ʽb=(a-1)%9+1 �Ϳ��Բ���ѭ��
        int root = (num - 1) % 9 + 1;
        return root;
    }
}