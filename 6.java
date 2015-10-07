public class Solution {
    public String convert(String s, int numRows) {
 if(numRows <= 1)
            return s;
        StringBuffer buf = new StringBuffer();
        int period = 2 * numRows - 2;
        for(int i=0;i<numRows;i++){
            for(int j=i;j<s.length();j+=period){
                buf.append(s.charAt(j));
                if(i !=0 && i != numRows - 1 && j+period - 2*i <s.length())
                    buf.append(s.charAt(j+period - 2*i));
            }
        }
        return buf.toString();
        
    }
}