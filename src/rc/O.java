package rc;

/**   OÐÍ¿é
 *    0 1
 *    2 3
 */
public class O extends Tetromino {
	public O(int row,int column) {
		cells[0]=new Cell(row,column);
		cells[1]=new Cell(row,column+1);
		cells[2]=new Cell(row+1,column);
		cells[3]=new Cell(row+1,column+1);
		states=new State[] {new State(0,0,0,1,1,0,1,1)};
	}

}
