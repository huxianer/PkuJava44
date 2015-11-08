public class Solution {
    public int climbStairs(int n) {
        /* 递归解法在n=44时，会超时
        if(n == 0)
            return 0;
        if(n==1 || n==2)
            return 1;
        return climbStairs(n-1)+climbStairs(n-2);*/
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        for(int i=2; i<=n+1; i++){
            list.add((list.get(i-1)+list.get(i-2)));
        }
        return list.get(n+1);
    }
}