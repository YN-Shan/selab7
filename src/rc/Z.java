package rc;


/**   ZÐÍ¿é
 *    1 2
 *      0 3
 */
public class Z extends Tetromino {
	public Z(int row,int column) {
		cells[0]=new Cell(row+1,column);
		cells[1]=new Cell(row,column-1);
		cells[2]=new Cell(row,column);
		cells[3]=new Cell(row+1,column+1);
		states=new State[] {
			new State(0,0,-1,-1,-1,0,0,1),
			new State(0,0,-1,1,0,1,1,0)
		};
	}
}

