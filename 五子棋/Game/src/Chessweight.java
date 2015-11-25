

public class Chessweight {
	private int[][] weight;
	public int[][] getWeight() {
		return this.weight;
	}
	public void initWeight() {
		// 初始化棋盘数组
		weight = new int[22][22];
		
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 22; j++) {
				weight[i][j] = 0;
			}
		}
	}
	public void setWeight(int posX, int posY,int weight) {
		
		this.weight[posX][posY] = weight;
	}
}
