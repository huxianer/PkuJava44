public class Solution {
	// O(n)
    public List<Integer> getRow(int rowIndex) {
        // ����pascal����
        int[][] arr = new int[rowIndex + 1][rowIndex + 1];
        // ��0�к�0�ж�Ϊ1
        for(int i = 0; i <= rowIndex; i++){
            arr[0][i] = 1;
            arr[i][0] = 1;
        }
        for(int i = 1; i <= rowIndex; i++){
            for(int j = 1; j <= rowIndex; j++){
                arr[i][j] = arr[i][j-1] + arr[i-1][j];
            }
        }
        int[] result = new int[rowIndex + 1];
        // ����ĴζԽ����ϵ�ֵ����result
        ArrayList list = new ArrayList<>(rowIndex + 1);
	    for(int i = 0, n = rowIndex; i <= rowIndex; i++,  n--){
	        list.add(arr[i][n]);
	    }
	    return list;
    }
}