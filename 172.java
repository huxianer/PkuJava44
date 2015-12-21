public class Solution {
    public int trailingZeroes(int n) {
        
         int a = 0;    
    while(n)    
    {    
        a += n/5;    
        n /= 5;    
    }    
    return a;  
    }
}