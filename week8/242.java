public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s==null && t==null) return true;
        if(s.length()!=t.length()) return false;
        
        char str1[] = s.toCharArray();
        char str2[] = t.toCharArray();
        
        Arrays.sort(str1); 
        Arrays.sort(str2);
        return String.valueOf(str1).equals(String.valueOf(str2));
        
    }
}