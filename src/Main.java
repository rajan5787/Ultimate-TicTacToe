
import java.util.*;

public class Main {

	static MiniBoard[] board;
	public static void main(String argv[]) {
		
		board = new MiniBoard[9];
		for(int i=0;i<9;i++)
			board[i] = new MiniBoard();
		
		Scanner asd = new Scanner(System.in);
		int preX = 2;
		while(true) {
			int x = asd.nextInt();
			System.out.println(preX+" "+ x);
			board[preX].move(x, 1);
			//print(board);
			preX = x;

			AIPlayer mPlayer = new AIPlayer();
			int y = mPlayer.findScore(board,x);
			board[preX].move(y, 2);
			System.out.println(preX+" "+ y);
			preX = y;
			print(board);
		}
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
