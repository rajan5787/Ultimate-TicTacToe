
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
		ArrayList<Integer> maxList = null;
		Random randomNum = new Random();
		ArrayList<Integer> list = getAvaible(board,index);
		int val = Integer.MIN_VALUE;

		for(int i = 0;i<list.size();i++) {

			System.out.println("rajan0 "+index+" "+ list.get(i)+" "+2);
			board[index].cell[list.get(i)] = 2;
			int tempVal = MinValue(list.get(i),board,-Integer.MAX_VALUE,Integer.MAX_VALUE,5, 1,0) 
					+ calScore(index,list.get(i),board,2)*5;

			System.out.print(tempVal+" ");
			if(val< tempVal){
				val = tempVal;
				maxList = new ArrayList<>();
				maxList.add(list.get(i));
			}
			else if(val==tempVal)
				maxList.add(list.get(i));

			board[index].cell[list.get(i)] = 0;

		}
		System.out.println();
		int num = maxList.get(randomNum.nextInt(maxList.size()));
		return num;
	}

	public int MinValue(int index, MiniBoard[] board,int alpha,int beta,int depth,int player,int score) {

		if(depth==0)
			return 0;
		else {
			int val = Integer.MIN_VALUE;
			ArrayList<Integer> list = getAvaible(board,index);

			for(int i = 0;i<list.size();i++) {
				board[index].cell[list.get(i)] = player;
				val = Math.max(val, MaxValue(list.get(i),board,alpha,beta,depth-1,2,score)
						+ calScore(index,list.get(i),board,player)*depth);

				board[index].cell[list.get(i)] = 0;
				beta = Math.min(val, beta);

				if(beta<=alpha) {
					return val;
				}
			}
			return  val;
		}
	}

	public int MaxValue(int index, MiniBoard[] board,int alpha,int beta,int depth,int player,int score) {

		if(depth==0)
			return 0;
		else {
			int val = -Integer.MIN_VALUE;
			ArrayList<Integer> list = getAvaible(board,index);

			for(int i = 0;i<list.size();i++) {
				board[index].cell[list.get(i)] = player;
				val = Math.max(val, MinValue(list.get(i),board,alpha,beta,depth-1,1,score)
						+calScore(index,list.get(i),board,player));

				board[index].cell[list.get(i)] = 0;
				alpha = Math.max(val, alpha);

				if(alpha>=beta) {
					return val;
				}
			}
			
			return  val;
		}
	}


	public int calScore(int Bindex, int index, MiniBoard[] board,int player) {

		int[] scorePerCell = {30,20,30,20,50,20,30,20,30};
		HashMap<Integer,ArrayList<Point>> map = new HashMap<Integer,ArrayList<Point>>();
		map = creatMap(map);

		MiniBoard tempMiniBoard = board[Bindex];
		int[] tempCell = tempMiniBoard.cell;

		if(tempMiniBoard.isFull)
			return 0;
		else {

			ArrayList<Point> tempPointList = map.get(index);
			for(int i = 0;i<tempPointList.size();i++) {

				Point tempPoint = tempPointList.get(i);
				if(tempCell[tempPoint.first]==tempCell[tempPoint.second]&&tempCell[tempPoint.first]!=0) {
					if(tempCell[index]==2&&tempCell[tempPoint.first]==2) {
						System.out.println("rajan1 " +Bindex+" "+index+" "+player );
						return 1000;
					}
					else if(tempCell[index]==1) {
							System.out.println("rajan2  "+Bindex+" "+index+" "+player+" ");
						return -1000;
					}

				}

			}

			int total_score = 0;
			if(player==2)
			return scorePerCell[index];
			else
				return -scorePerCell[index];
			//			for(int i = 0;i<9;i++) {
			//			if(tempCell[i]==1)
			//				total_score -= scorePerCell[i];
			//			else if(tempCell[i]==2)
			//				total_score += scorePerCell[i];
			//			}
			//			return total_score;
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
