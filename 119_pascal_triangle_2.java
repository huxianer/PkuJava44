public class Solution {
	// O(n)
    public List<Integer> getRow(int rowIndex) {
        // 构造pascal矩阵
        int[][] arr = new int[rowIndex + 1][rowIndex + 1];
        // 第0行和0列都为1
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
        // 矩阵的次对角线上的值就是result
        ArrayList list = new ArrayList<>(rowIndex + 1);
	    for(int i = 0, n = rowIndex; i <= rowIndex; i++,  n--){
	        list.add(arr[i][n]);
	    }
	    return list;
    }
}