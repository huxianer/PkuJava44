public class Solution {
	// O(n^2)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for(int i = 0; i < numRows; i++){
            List<Integer> branch = new ArrayList<Integer>();
            // getֻ����������ֵ������������ֵ
            //branch.get(0) = 1;
            //branch.get(i) = 1;
            if(i == 0){
				branch.add(1);
				list.add(branch);
				continue;
			}
            branch.add(1);
            if(i > 1){
                // ��ÿ�в�����������ӵ�list��
                for(int j = 1; j < i; j++){
                    //branch[j] = list.get(j - 1).get(j - 1) + list.get(j - 1).get(j);
                    // �˴���һ��Ӧ���� i-1
                    // branch.add(j, list.get(j - 1).get(j - 1) + list.get(j - 1).get(j));
                    branch.add(j, list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
                }
            }
            branch.add(1);
            list.add(branch);
        }
        return list;
        
    }
}