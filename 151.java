public class Solution {
	// O(n)
    public String reverseWords(String s) {
        
        // 此处表达式不能分割连续两个或以上空格的字符串
        //String[] strs = s.split(" ");
        // 应该使用正则表达式
        String[] strs = s.split("\\s+"); //表示1个或多个空格
        StringBuffer result = new StringBuffer();
        for(int i = strs.length - 1; i >= 0; i--){
            // 错误原因是拼接时忘记加空格了
            //result.append(strs[i]);
            result.append(strs[i] + " ");
        }
        return result.toString().trim();
    }
}