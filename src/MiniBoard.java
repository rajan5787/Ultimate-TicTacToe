
public class MiniBoard {

	int[] cell;
	boolean winStatus;
	int count;
	boolean isFull;
	
	MiniBoard(){
		cell = new int[9];
		winStatus = false;
		count = 0;
		isFull = false;
	}
	
	public void move(int x,int player) {
		
			cell[x] = player;
	}
	
	public boolean isWiner() {
		if(winStatus)
			return true;
		else
			return false;
	}
	
	
}
