
import java.util.*;

public class AIPlayer {

	
	
	public ArrayList<Integer> getAvaible(MiniBoard[] board,int index){
		
		ArrayList<Integer> list = new ArrayList<>();
		
		int[] tempCell = board[index].cell;
		for(int i = 0;i<9;i++) {
			if(tempCell[i]==0)
				list.add(i);
		}
		return list;
	}
	
	public int findScore(MiniBoard[] board,int index){
	
		Random randomNum = new Random();
		ArrayList<Integer> list = getAvaible(board,index);
		ArrayList<Integer> maxList = new ArrayList<>();
		int val = Integer.MIN_VALUE;
		for(int i = 0;i<list.size();i++) 
			System.out.print(list.get(i)+" ");
		
		System.out.println();

		for(int i = 0;i<list.size();i++) {
			
			board[index].cell[list.get(i)] = 2;
			int tempVal = MinValue(list.get(i),board,-Integer.MAX_VALUE,Integer.MAX_VALUE,2, 2);// + calScore(index,i,board,2);
			if(val< tempVal){
				val = tempVal;
				maxList.add(list.get(i));
			}
			board[index].cell[list.get(i)] = 0;
//			
//			System.out.println(list.get(i));
//			print(board);
//			System.out.println("---------------------------------");
//			System.out.println("---------------------------------");
//			System.out.println("---------------------------------");

		}
		
		int num = maxList.get(randomNum.nextInt(maxList.size()));
		return num;
	}
	
	public int MinValue(int index, MiniBoard[] board,int alpha,int beta,int depth,int player) {
		
		if(depth<=0)
			return 0;
		
		int val = Integer.MAX_VALUE;
		ArrayList<Integer> list = getAvaible(board,index);
	
		for(int i = 0;i<list.size();i++) {
			board[index].cell[list.get(i)] = player;
			val = Math.min(val, MaxValue(list.get(i),board,depth,alpha,beta,1));// + calScore(index,i,board,1);
			
			board[index].cell[list.get(i)] = 0;
			
//			System.out.println("Min");
//			for(int f = 0;f<list.size();f++) 
//				System.out.print(list.get(f)+" ");
//			System.out.println();
//			print(board);
//			System.out.println("---------------------------------");

			if(val<=alpha) {
				return val;
			}
			beta = Math.min(val, beta);
		}
		return val;
		
	}
	
	public int MaxValue(int index, MiniBoard[] board,int alpha,int beta,int depth,int player) {
		
		if(depth==0)
			return 0;
		int val = Integer.MIN_VALUE;
		ArrayList<Integer> list = getAvaible(board,index);
		
		for(int i = 0;i<list.size();i++) {
			board[index].cell[list.get(i)] = player;
			val = Math.max(val, MinValue(list.get(i),board,depth-1,alpha,beta,2));// + calScore(index,i,board,2);
			
			board[index].cell[list.get(i)] = 0;
			
//			System.out.println("Max");
//			for(int f = 0;f<list.size();f++) 
//				System.out.print(list.get(f)+" ");
//			System.out.println();
//			print(board);
//			System.out.println("---------------------------------");
//			System.out.println("---------------------------------");

			if(val>=beta) {
				return val;
			}
			alpha = Math.max(val, alpha);
		}
		return val;
	}
	

	public int calScore(int Bindex, int index, MiniBoard[] board,int player) {
		
		int[] scorePerCell = {300,200,300,200,1000,200,300,200,300};
		HashMap<Integer,ArrayList<Point>> map = new HashMap<Integer,ArrayList<Point>>();
		map = creatMap(map);
		
		MiniBoard tempMiniBoard = board[Bindex];
		int[] tempCell = tempMiniBoard.cell;
		
		if(tempMiniBoard.isWiner()||tempMiniBoard.isFull)
			return 0;
		else {
			
			ArrayList<Point> tempPointList = map.get(index);
			for(int i = 0;i<tempPointList.size();i++) {
				
				Point tempPoint = tempPointList.get(i);
				if(tempCell[tempPoint.first]==2) {
					if(tempCell[tempPoint.first]==tempCell[tempPoint.second]) {
						return 1000;
					}
				}
				else if(tempCell[tempPoint.first]==1) {
					if(tempCell[tempPoint.first]==tempCell[tempPoint.second]) {
						return -1000;
					}
				}
			}
			if(player==1)
				return -scorePerCell[index];
			else
				return scorePerCell[index];
			
		}
				
	}
	
	public HashMap<Integer,ArrayList<Point>> creatMap(HashMap<Integer,ArrayList<Point>> map){
		
		ArrayList<Point> tempPoint = new ArrayList<>();
		tempPoint.add(new Point(1,2));
		tempPoint.add(new Point(3,6));
		tempPoint.add(new Point(4,8));
		map.put(0,tempPoint);
		
		tempPoint = new ArrayList<>();
		tempPoint.add(new Point(0,2));
		tempPoint.add(new Point(4,7));
		map.put(1,tempPoint);
		
		tempPoint = new ArrayList<>();
		tempPoint.add(new Point(0,1));
		tempPoint.add(new Point(4,6));
		tempPoint.add(new Point(5,8));
		map.put(2,tempPoint);
		
		tempPoint = new ArrayList<>();
		tempPoint.add(new Point(0,6));
		tempPoint.add(new Point(5,4));
		map.put(3,tempPoint);
		
		tempPoint = new ArrayList<>();
		tempPoint.add(new Point(0,8));
		tempPoint.add(new Point(2,6));
		tempPoint.add(new Point(1,7));
		tempPoint.add(new Point(3,5));
		map.put(4,tempPoint);
		
		tempPoint = new ArrayList<>();
		tempPoint.add(new Point(2,8));
		tempPoint.add(new Point(3,4));
		map.put(5,tempPoint);
		
		tempPoint = new ArrayList<>();
		tempPoint.add(new Point(0,3));
		tempPoint.add(new Point(7,8));
		tempPoint.add(new Point(2,4));
		map.put(6,tempPoint);
		
		tempPoint = new ArrayList<>();
		tempPoint.add(new Point(6,8));
		tempPoint.add(new Point(1,4));
		map.put(7,tempPoint);
		
		tempPoint = new ArrayList<>();
		tempPoint.add(new Point(6,7));
		tempPoint.add(new Point(2,5));
		tempPoint.add(new Point(0,4));
		map.put(8,tempPoint);

		return map;
	}

	static void print(MiniBoard[] board) {
		
		for(int i = 0;i<3;i++) {
			MiniBoard temp = board[i];
			for(int j = 0;j<3;j++){
				System.out.print(temp.cell[j]+" ");
			}
			System.out.print(" ");
		}
		System.out.println();
		
		for(int i = 0;i<3;i++) {
			MiniBoard temp = board[i];
			for(int j = 3;j<6;j++){
				System.out.print(temp.cell[j]+" ");
			}
			System.out.print(" ");
		}
		System.out.println();

		for(int i = 0;i<3;i++) {
			MiniBoard temp = board[i];
			for(int j = 6;j<9;j++){
				System.out.print(temp.cell[j]+" ");
			}
			System.out.print(" ");
		}
		System.out.println();
		System.out.println();

		for(int i = 3;i<6;i++) {
			MiniBoard temp = board[i];
			for(int j = 0;j<3;j++){
				System.out.print(temp.cell[j]+" ");
			}
			System.out.print(" ");
		}
		System.out.println();

		for(int i = 3;i<6;i++) {
			MiniBoard temp = board[i];
			for(int j = 3;j<6;j++){
				System.out.print(temp.cell[j]+" ");
			}
			System.out.print(" ");
		}
		System.out.println();

		for(int i = 3;i<6;i++) {
			MiniBoard temp = board[i];
			for(int j = 6;j<9;j++){
				System.out.print(temp.cell[j]+" ");
			}
			System.out.print(" ");
		}
		System.out.println();
		System.out.println();

		for(int i = 6;i<9;i++) {
			MiniBoard temp = board[i];
			for(int j = 0;j<3;j++){
				System.out.print(temp.cell[j]+" ");
			}
			System.out.print(" ");
		}
		System.out.println();

		for(int i = 6;i<9;i++) {
			MiniBoard temp = board[i];
			for(int j = 3;j<6;j++){
				System.out.print(temp.cell[j]+" ");
			}
			System.out.print(" ");
		}
		System.out.println();

		for(int i = 6;i<9;i++) {
			MiniBoard temp = board[i];
			for(int j = 6;j<9;j++){
				System.out.print(temp.cell[j]+" ");
			}
			System.out.print(" ");
		}
		System.out.println();
		System.out.println();
		System.out.println();

	}

}
