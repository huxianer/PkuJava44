public class Solution {
    public String addBinary(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        // ��λ�����Ϊ����ĳ���
        int max = aLength < bLength ? bLength : aLength;
        int[] aArr = new int[max];
        int[] bArr = new int[max];
        // �����洢�����ֵ
        int[] cArr = new int[max + 1];
        // �����
        String result;
        // ���ַ�������ת����int�ʹ浽������
        /* a��b�ĳ��Ȳ�һ�������Բ��ܷ���һ��ѭ���У��±�ᳬ��
        for(int i = max-1; i >= 0; i--){
            aArr[i] = a.charAt(i) - '0';
            bArr[i] = b.charAt(i) - '0';
        }*/
        int count = max;
		for(int i = a.length()-1, j=b.length()-1;i>=0||j>=0;i--,j--){
			if(a.length()>b.length()){
				if(i>=0){
					aArr[i] = a.charAt(i) - '0';
				}
				if(j>=0){
					bArr[--count] = b.charAt(j) - '0';
				}
			}else{
				if(i>=0){
					aArr[--count] = a.charAt(i) - '0';
				}
				if(j>=0){
					bArr[j] = b.charAt(j) - '0';
				}
			}
			
		}
        cArr = add(aArr,bArr,max);
        char[] dArr = new char[max + 1];
        for(int i = max; i >= 0; i--){
            dArr[i] = (char)(cArr[i] + '0');
        }
        if(dArr[0] == '0'){
            result = new String(dArr,1,max);
        }else{
            result = new String(dArr);
        }
        return result;
    }
    
    public int[] add(int[] a, int[] b, int length){
        int[] c = new int[length + 1];
        for(int i = a.length -1; i >= 0; i--){
            c[i+1] = a[i] + b[i];
        }
        for(int i = length; i > 0; i--){
            if(c[i] > 1){
                c[i] %= 2;
                c[i - 1]++;
            }
        }
        return c;
    }
}