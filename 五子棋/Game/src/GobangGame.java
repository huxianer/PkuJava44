import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// 定义达到赢条件的棋子数目
	private final int WIN_COUNT = 5;
	// 定义用户输入的X坐标
	private int posX = 0;
	// 定义用户输入的X坐标
	private int posY = 0;
	// 定义棋盘
	private Chessboard chessboard;
	// 定义权重
	private Chessweight chessweight;
	//定义计数
	private Chesscount chesscount;
	/**
	 * 空构造器
	 */
	public GobangGame() {
	}

	/**
	 * 构造器，初始化棋盘和棋子属性
	 * 
	 * @param chessboard
	 *            棋盘类
	 * @param chessweight
	 * @param chesscount
	 */
	public GobangGame(Chessboard chessboard,Chessweight chessweight,Chesscount chesscount) {
		this.chessboard = chessboard;
		this.chesscount=chesscount;
		this.chessweight=chessweight;
	}

	/**
	 * 检查输入是否合法。
	 * 
	 * @param inputStr
	 *            由控制台输入的字符串。
	 * @return 字符串合法返回true,反则返回false。
	 */
	public boolean isValid(String inputStr) {
		// 将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("请以(数字,数字)的格式输入：");
			return false;
		}
		// 检查输入数值是否在范围之内
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X与Y坐标只能大于等于1,与小于等于" + Chessboard.BOARD_SIZE
					+ ",请重新输入：");
			return false;
		}
		// 检查输入的位置是否已经有棋子
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "十") {
			chessboard.printBoard();
			System.out.println("此位置已经有棋子，请重新输入：");
			return false;
		}
		return true;
	}

	/**
	 * 开始下棋
	 */
	public void start() throws Exception {
		// true为游戏结束
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// 获取键盘的输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// 如果不合法，要求重新输入，再继续
				continue;
			}
			// 把对应的数组元素赋为"●"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// 判断用户是否赢了
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// 计算机随机选择位置坐标
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// 判断计算机是否赢了
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// 如果产生胜者，询问用户是否继续游戏
			if (isOver) {
				// 如果继续，重新初始化棋盘，继续游戏
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// 如果不继续，退出程序
				break;
			}
			chessboard.printBoard();
			System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
		}
	}

	/**
	 * 是否重新开始下棋。
	 * 
	 * @param chessman
	 *            "●"为用户，"○"为计算机。
	 * @return 开始返回true，反则返回false。
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了，"
				: "很遗憾，您输了，";
		System.out.println(message + "再下一局？(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// 开始新一局
			return true;
		}
		return false;

	}

	/**
	 * 计算机随机下棋
	 */
	public int[] computerDo() {
		int i;
		int j;
		int posX = 0;
        int posY = 0;
		int max = 0;
		String[][] board = chessboard.getBoard();
		int[][] weight = chessweight.getWeight();
		calNum();
		calweight();
//		int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//		String[][] board = chessboard.getBoard();
//		while (board[posX][posY] != "十") {
//			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//		}
		for ( i = 0; i < 22; i++ ){  
	        for ( j = 0; j < 22; j++ ) {
	        	
	        	if (board[i][j] == "十"){
	        		if (weight[i][j]>max){
	        			max = weight[i][j];
	        			posY = j;
	        			posX = i;	        
					}
	        	}
	        }
		}
		int[] result = { posX, posY };
		return result;
	}

	/**
	 * 判断输赢
	 * 
	 * @param posX
	 *            棋子的X坐标。
	 * @param posY
	 *            棋子的Y坐标
	 * @param ico
	 *            棋子类型
	 * @return 如果有五颗相邻棋子连成一条直接，返回真，否则相反。
	 */
	public boolean isWon(int posX, int posY, String ico) {
		int count1=2;//刚落子的点和周围第一个与其相同的点
		int count2=0;//必要的时候遍历另一段
		int count3=0;
		String[][] boardNew=chessboard.getBoard();
		//如果落子（1，1）
		if(posX==0 && posY==0){
			//横向
			for(int j=0;j<22 && count2<6;j++){
				if(!boardNew[posX][j].equals(ico)) break;
				count2++;
				if(count2==5) return true;
			}
			//纵向
			for(int i=0;i<22 && count2<6;i++){
				if(!boardNew[i][posY].equals(ico)) break;
				count2++;
				if(count2==5) return true;
			}
			//斜向
			for(int i=0,j=0;count2<6;i++,j++){
				if(!boardNew[i][j].equals(ico)) break;
				count2++;
				if(count2==5) return true;
			}
		}
		//如果落子（1，22）
				if(posX==0 && posY==21){
					//横向
					for(int j=posY;count2<6;j--){
						if(!boardNew[posX][j].equals(ico)) break;
						count2++;
						if(count2==5) return true;
					}
					//纵向
					for(int i=posX; count2<6;i++){
						if(!boardNew[i][posY].equals(ico)) break;
						count2++;
						if(count2==5) return true;
					}
					//斜向
					for(int i=posX,j=posY;count2<6;i++,j--){
						if(!boardNew[i][j].equals(ico)) break;
						count2++;
						if(count2==5) return true;
					}
				}
				//如果落子（22，1）
				if(posX==21 && posY==0){
					//横向
					for(int j=posY;count2<6;j++){
						if(!boardNew[posX][j].equals(ico)) break;
						count2++;
						if(count2==5) return true;
					}
					//纵向
					for(int i=posX; count2<6;i--){
						if(!boardNew[i][posY].equals(ico)) break;
						count2++;
						if(count2==5) return true;
					}
					//斜向
					for(int i=posX,j=posY;count2<6;i--,j++){
						if(!boardNew[i][j].equals(ico)) break;
						count2++;
						if(count2==5) return true;
					}
				}
				//如果落子（22，22）
				if(posX==21 && posY==21){
					//横向
					for(int j=posY;count2<6;j--){
						if(!boardNew[posX][j].equals(ico)) break;
						count2++;
						if(count2==5) return true;
					}
					//纵向
					for(int i=posX; count2<6;i--){
						if(!boardNew[i][posY].equals(ico)) break;
						count2++;
						if(count2==5) return true;
					}
					//斜向
					for(int i=posX,j=posY;count2<6;i--,j--){
						if(!boardNew[i][j].equals(ico)) break;
						count2++;
						if(count2==5) return true;
					}
				}
		//如果落子在(1,y)上
		if(posX==0 && posY!=0){
			//横向
				for(int j=posY;j<22 && count2<6;j++){
					if(!boardNew[0][j].equals(ico)) break;
					count2++;
					if(count2==5) return true;
				}
				for(int j=posY;j>=0 && count1<6;j--){
					if(!boardNew[0][j].equals(ico)) break;
					count3++;
					if(count2+count3-1==5) return true;
			}
			//纵向
				for(int i=0;i<22 && count2<6;i++){
					if(!boardNew[i][posY].equals(ico)) break;
					count2++;
					if(count2==5) return true;
				}
			//对角线
				if(boardNew[1][posY-1].equals(ico)){
					count2=count2+1;
					for(int i=1,j=posY-1;j>=0&&count2<6;j--,i++){
						if(!boardNew[i][j].equals(ico)) break;
							count2++;
							if(count2==5) return true;	
					}
				}
				if(boardNew[1][posY+1].equals(ico)){
					count2=count2+1;
					for(int i=2,j=posY+1;j<22 && count2<6;j++,i++){
						if(!boardNew[i][j].equals(ico)) break;
							count2++;
							if(count2==5) return true;	
					}
				}
		}
		//如果落子在(x,1)上
		if(posY==0 && posX!=0){
			//纵向
				for(int i=posX;i<22 && count2<6;i++){
					if(!boardNew[i][0].equals(ico)) break;
						count2++;
					if(count2==5) return true;
				}
				for(int i=posX;i>=0 && count1<6;i--){
					if(!boardNew[i][0].equals(ico)) break;
						count3++;
					if(count2+count3-1==5) return true;
			}
			//横向
				for(int i=0;i<22 && count2<6;i++){
					if(!boardNew[posX][i].equals(ico)) break;
					count2++;
					if(count2==5) return true;
				}
			//对角线
				if(boardNew[posX-1][1].equals(ico)){
					count2=count2+1;
					for(int i=posX-1,j=1;j>=0&&count2<6;i--,j++){
						if(!boardNew[i][j].equals(ico)) break;
							count2++;
							if(count2==5) return true;	
					}
				}
				if(boardNew[posX+1][1].equals(ico)){
					count2=count2+1;
					for(int i=posX+1,j=1;j<22 && count2<6;j++,i++){
						if(!boardNew[i][j].equals(ico)) break;
							count2++;
							if(count2==5) return true;	
					}
				}
		}
		//如果落子在(21,y)上
				if(posX==21 && posY!=0){
					//横向
						for(int j=posY;j<22 && count2<6;j++){
							if(!boardNew[posX][j].equals(ico)) break;
							count2++;
							if(count2==5) return true;
						}
						for(int j=posY;j>=0 && count1<6;j--){
							if(!boardNew[posX][j].equals(ico)) break;
							count3++;
							if(count2+count3-1==5) return true;
					}
					//纵向
						for(int i=posX;i>=0 && count2<6;i--){
							if(!boardNew[i][posY].equals(ico)) break;
							count2++;
							if(count2==5) return true;
						}
					//对角线
						if(boardNew[posX-1][posY-1].equals(ico)){
							count2=count2+1;
							for(int i=posX-1,j=posY-1;j>=0&&count2<6;j--,i--){
								if(!boardNew[i][j].equals(ico)) break;
									count2++;
									if(count2==5) return true;	
							}
						}
						if(boardNew[posX-1][posY+1].equals(ico)){
							count2=count2+1;
							for(int i=posX-1,j=posY+1;j<22 && count2<6;j++,i--){
								if(!boardNew[i][j].equals(ico)) break;
									count2++;
									if(count2==5) return true;	
							}
						}
				}
		//如果落子在(x,21)上
		if(posY==21 && posX!=21){
			//纵向
				for(int i=posX;i<22 && count2<6;i++){
					if(!boardNew[i][posY].equals(ico)) break;
						count2++;
					if(count2==5) return true;
				}
				for(int i=posX;i>=0 && count1<6;i--){
					if(!boardNew[i][posY].equals(ico)) break;
						count3++;
					if(count2+count3-1==5) return true;
			}
			//横向
				for(int i=21;i>=0 && count2<6;i--){
					if(!boardNew[posX][i].equals(ico)) break;
					count2++;
					if(count2==5) return true;
				}
			//对角线
				if(boardNew[posX-1][posY-1].equals(ico)){
					count2=count2+1;
					for(int i=posX-1,j=posY-1;i>=0 && count2<6;i--,j--){
						if(!boardNew[i][j].equals(ico)) break;
							count2++;
							if(count2==5) return true;	
					}
				}
				if(boardNew[posX+1][posY-11].equals(ico)){
					count2=count2+1;
					for(int i=posX+1,j=posY-1;i<22 && count2<6;j--,i++){
						if(!boardNew[i][j].equals(ico)) break;
							count2++;
							if(count2==5) return true;	
					}
				}
		}
	
		else if (posX>0 && posY>0 && posX<22 && posY<22){
		
			for(int i=posX-1;i<posX+2;i++){
				for(int j=posY-1;j<posY+2;j++){
					if(boardNew[i][j].equals(ico)){
						if(i==(posX-1)&&j==(posY-1)){//如果左上角有相同的子，左上角遍历
							for(int t=posX-2,f=posY-2;f>=0 && t>=0 && count1<6;t-=1,f-=1){
								if(!boardNew[t][f].equals(ico)) break;
									count1++;
								if (count1==5) return true;			
							}
							if (boardNew[posX+1][posY+1].equals(ico)) {//考虑棋子落中间的情况
								for(int t=posX+1,f=posY+1;f<22 && t<22 && count1<6;t+=1,f+=1){
									if(!boardNew[t][f].equals(ico)) break;
										count2++;
										if((count1+count2)==5) return true;
								}	
							}
						}
						if(i==(posX+1)&&j==(posY+1)){//如果右下角有相同的子，右下角遍历
							for(int t=posX+2,f=posY+2;f<22 && t<22 && count1<6;t+=1,f+=1){
								if(!boardNew[t][f].equals(ico)) break;
									count1++;
								if (count1==5) return true;			
							}
						}
						
						if(i==(posX-1)&&j==(posY+1)){//如果右上角有相同的子，右上角遍历
							for(int t=posX-2,f=posY+2;f<22 && t>=0 && count1<6;t-=1,f+=1){
								if(!boardNew[t][f].equals(ico)) break;
									count1++;
								if (count1==5) return true;			
							}
							if (boardNew[posX+1][posY-1].equals(ico)) {//考虑棋子落中间的情况
								for(int t=posX+1,f=posY-1;f>=0 && t<22 && count1<6;t+=1,f-=1){
									if(!boardNew[t][f].equals(ico)) break;
										count2++;
										if((count1+count2)==5) return true;
								}	
							}
						}
						if(i==(posX+1)&&j==(posY-1)){//如果左下角有相同的子，右下角遍历
							for(int t=posX+2,f=posY-2;f>=0 && t<22 && count1<6;t+=1,f-=1){
								if(!boardNew[t][f].equals(ico)) break;
									count1++;
								if (count1==5) return true;			
							}
						}
						
						if(i==posX && j==posY-1){//如果是横向左侧的
							for(int f=posY-2;f>=0 && count1<6;f--){
								if(!boardNew[i][f].equals(ico)) break;
								count1++;
								if(count1==5) return true;
								}
							if (boardNew[posX][posY+1].equals(ico)) {//考虑点在中间胜利的情况
								for(int f=posY+1;f<22 && count1<6;f++){
									if(!boardNew[posX][f].equals(ico)) break;
									count2++;
									if((count1+count2)==5) return true;
								}						
							}				
						}
						if(i==posX && j==posY+1){//如果是横向右侧的
							for(int f=posY+2;f<22 && count1<6;f++){
								if(!boardNew[posX][f].equals(ico)) break;
								count1++;
								if(count1==5) return true;
								}				
						}
						
						if(j==posY && i==posX-1){//如果是纵向向上的
							for(int f=posX-2;f>=0 && count1<6;f--){
								if(!boardNew[f][posY].equals(ico)) break;
								count1++;
								if(count1==5) return true;
								}
							if (boardNew[posX+1][posY].equals(ico)) {//考虑点在中间胜利的情况
								for(int f=posX+1;f<22 && count2<6;f++){
									if(!boardNew[f][posY].equals(ico)) break;
									count2++;
									if((count1+count2)==5) return true;
								}						
							}				
						}
						if(j==posY && i==posX+1){//如果是纵向向下的
							for(int f=posX+2;f<22 && count1<6;f++){
								if(!boardNew[f][posY].equals(ico)) break;
								count1++;
								if(count1==5) return true;
								}				
						}
					}
				}
			}
		
		
		}
		return false;
	}

	public void calweight() {
		String[][] board = chessboard.getBoard();
		int[][][] count = chesscount.getCount();
		int i, j, k;  
	    int win ;  
	    for ( i = 0; i < 22; i++ )  
	        for ( j = 0; j < 22; j++ )   
	        {  
	            if ( board[i][j] == "十" ){  
	            	win=0;
	                for ( k = 0; k < 4; k++ )  
	                {  
	                    if ( count[i][j][k] + count[i][j][k+4] >= 4 )  
	                        win += 10000;  
	                    else if ( count[i][j][k] + count[i][j][k+4] == 3 )  
	                        win += 1000;   
	                    else if ( count[i][j][k] + count[i][j][k+4] == 2 )  
	                        win += 100;  
	                    else if ( count[i][j][k] + count[i][j][k+4] == 1 )  
	                        win += 10;  
	                }  

	                chessweight.setWeight(i, j, win);

	            }
	        }
	}
	//统计每个空位周围●连成一线的棋子数目
	public void calNum() {
		int i, j, k, t, cnt; 
		int i1, j1; 
		int dx[]=new int[]{0, 1, 1, 1, 0, -1, -1, -1};
		int dy[]= new int[]{-1, -1, 0, 1, 1, 1, 0, -1}; 
		
		String[][] board = chessboard.getBoard();
		
		for ( i = 0; i < 22; i++ ){  
		        for ( j = 0; j < 22; j++ ) {  
		            if ( board[i][j] == "十" ){  //空位  
		                for ( k = 0; k < 8; k++ ){  // 8个方向
		                    cnt = 0;
		                    i1 = i;  
		                    j1 = j;  
		                    for ( t = 0; t < 5; t++ ) 
		                    {  
		                    	i1 =i1 + dx[k];  
		                        j1 =j1 + dy[k];  
		                        if ( i1 > 22 || i1 < 0 || j1 > 22|| j1 < 0 ) 
		                            break;  
		                        if( board[i1][j1] == "●" )  
		                            cnt++;  
		                        else 
		                            break;  
		                    }  
		                    System.out.println(i+" "+j+" "+k+" "+cnt);
		                    chesscount.setCount(i, j, k, cnt);

		                }
		            }
		        }
		}
	}
	public static void main(String[] args) throws Exception {
		
		GobangGame gb = new GobangGame(new Chessboard(),new Chessweight(),new Chesscount());
		gb.start();
	}
}
