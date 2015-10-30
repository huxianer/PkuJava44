public class Solution {
    public boolean isPalindrome(String s) {
        /**
         * ��һ�δ���ԭ��û�к��Դ�Сд
         * �ڵõ�strʱ����ת����Сд
         * �ڶ��δ���ԭ��û�������Ŀ����˼��alphanumeric characters��ʾ������ĸ������
         * ֻ��������ĸ
         * ��isLetter��ΪisLetterOrDigit
         * */
        StringBuffer str =  new StringBuffer();
        // ����ȥ���ַ����еķ���ĸ
        for(int i = 0; i < s.length(); i++){
            if(Character.isLetterOrDigit(s.charAt(i))){
                str.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        s = str.toString();
        // �ַ���Ϊ���ַ�������ֻ��һ���ַ�ʱ����Ϊ�ǻ��ĵ�
        if(s.length() == 0 || s.length() == 1){
            return true;
        }
        for(int i = 0, j = s.length()-1; i < j; i++, j--){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }
}