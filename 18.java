public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if(num.length < 4){
            return quadruplets;
        }
        Arrays.sort(num);
        
            for(int j = i + 1; j < num.length - 2; j++){
                if(j > i + 1 && num[j] == num[j - 1]){
                    continue;
                }
            or(int i = 0; i < num.length - 3; i++){
            if(i > 0 && num[i] == num[i - 1]){
                continue;
            }
                int first = j + 1;
                int last = num.length - 1;
                while(first< last){
                    if(first > j + 1 && num[first] == num[first - 1]){
                        first++;
                        continue;
                    }
                    if(last < num.length - 1 && num[last] == num[last + 1]){
                        last--;
                        continue;
                    }
                    int sum = num[i] + num[j] + num[first] + num[last];
                    if(sum == target){
                        List list = new ArrayList();
                        list.add(num[i]);
                        list.add(num[j]);
                        list.add(num[first]);
                        list.add(num[last]);
                        quadruplets.add(list);
                        start++;
                        end--;
                    }else if(sum < target){
                        first++;
                    }else if(sum > target){
                        last--;
                    }
                }
            }
        }
        return quadruplets;
    }
}