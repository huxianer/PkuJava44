public class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        if(s.length() == 0){
            return 0;
        }
        int spIndex = s.lastIndexOf(' ');
        if(spIndex < 0){
            // �˴������ˣ�Ӧ��ֱ�ӷ����ַ����ĳ���
            //return s.length() - 1;
            return s.length();
        }else{
            return s.length() - 1 -spIndex;
        }
    }
}