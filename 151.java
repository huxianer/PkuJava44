public class Solution {
	// O(n)
    public String reverseWords(String s) {
        
        // �˴����ʽ���ָܷ��������������Ͽո���ַ���
        //String[] strs = s.split(" ");
        // Ӧ��ʹ��������ʽ
        String[] strs = s.split("\\s+"); //��ʾ1�������ո�
        StringBuffer result = new StringBuffer();
        for(int i = strs.length - 1; i >= 0; i--){
            // ����ԭ����ƴ��ʱ���Ǽӿո���
            //result.append(strs[i]);
            result.append(strs[i] + " ");
        }
        return result.toString().trim();
    }
}