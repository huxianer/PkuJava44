public class Solution {
    public boolean isHappy(int n) {
        // �������ÿ�μ���������
        List list = new ArrayList<>();
        
        while(true){ 
            char[] arr = String.valueOf(n).toCharArray();
            int length = arr.length;
            int result = 0;
            for(int i = 0; i < length; i++){
                // pow()ֻ������double
                //result += pow(arr[i] - '0', 2);
                result += (arr[i]-'0')*(arr[i]-'0');
            }
            if(list.contains(result)){
                return false;
            }
            if(result == 1){
                return true;
            }
            list.add(result);
            n = result;
        } 
        
    }
}