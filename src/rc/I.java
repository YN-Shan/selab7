package rc;

/**   IÐÍ¿é
 *    1 0 2 3
 * 
 */
public class I extends Tetromino {
	public I(int row,int column) {
		cells[0]=new Cell(row,column);
		cells[1]=new Cell(row,column-1);
		cells[2]=new Cell(row,column+1);
		cells[3]=new Cell(row,column+2);
		states=new State[] {
				new State(0,0,0,-1,0,1,0,2),
				new State(0,0,-1,0,1,0,2,0)
		};
	}
}
