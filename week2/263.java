public class Solution {
    public boolean isUgly(int num) {
        //1、能被7整除的
        //2、素数，能被素数整除的(除了2，3，5，7)
    //     if(num==1 || num==2 ||num==3||num==5) return true;
    //     if(num%7==0) return false;
    //     if(isPrime(num)) return false;
    //      return true;
    // }
    // public static boolean isPrime(int n){
    //     if(n<2)     return false;
    //     if(n==2)    return true;
    //     if(n%2==0)  return false;
    //     for(int i=3;i<=Math.sqrt(n);i+=2)
    //         if(n%i==0) return false;
    //         return true;
    // }
        if(num<0) return false;
        if(num%7==0) return false;
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }
}