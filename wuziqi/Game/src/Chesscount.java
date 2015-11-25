
public class Chesscount {
	
		private int[][][] count;
		public int[][][] getCount() {
			return this.count;
		}
		public void initcount() {
			// 初始化棋盘数组
			count = new int[22][22][8];
			
			for (int i = 0; i < 22; i++) {
				for (int j = 0; j < 22; j++) {
					for (int k = 0; k < 8; k++) {
						count[i][j][k] = 0;
					}
				}
			}
		}

		
		public void setCount(int posX, int posY,int k,int cnt) {
			
			this.count[posX][posY][k] = cnt;
		}

}
