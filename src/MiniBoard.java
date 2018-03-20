
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
	
	public boolean isWinerr() {
		if(cell[0]==cell[1]&&cell[1]==cell[2]) {
			return true;
		}
		if(cell[3]==cell[4]&&cell[4]==cell[5]) {
			return true;
		}
		if(cell[6]==cell[7]&&cell[7]==cell[8]) {
			return true;
		}
		
		if(cell[0]==cell[3]&&cell[3]==cell[6]) {
			return true;
		}
		if(cell[1]==cell[4]&&cell[4]==cell[7]) {
			return true;
		}
		if(cell[2]==cell[5]&&cell[5]==cell[8]) {
			return true;
		}
		
		if(cell[0]==cell[4]&&cell[4]==cell[8]) {
			return true;
		}
		if(cell[2]==cell[4]&&cell[4]==cell[6]) {
			return true;
		}
		return false;
	}
	
}
