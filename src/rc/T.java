package rc;

/**   TÐÍ¿é
 *    1 0 2
 *      3
 */
public class T extends Tetromino {
	public T(int row,int column) {
		cells[0]=new Cell(row,column);
		cells[1]=new Cell(row,column-1);
		cells[2]=new Cell(row,column+1);
		cells[3]=new Cell(row+1,column);
		states=new State[4];
		states[0]= new State(0,0,0,-1,0,1,1,0);
		states[1]= new State(0,0,-1,0,1,0,0,-1);
		states[2]= new State(0,0,0,1,0,-1,-1,0);
		states[3]= new State(0,0,1,0,-1,0,0,1);
	}
}