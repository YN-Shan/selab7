package rc;

/**   SÐÍ¿é
 *      0 1
 *    2 3
 */
public class S extends Tetromino {
	public S(int row,int column) {
		cells[0]=new Cell(row,column);
		cells[1]=new Cell(row,column+1);
		cells[2]=new Cell(row+1,column-1);
		cells[3]=new Cell(row+1,column);
		states=new State[] {
			new State(0,0,0,1,1,-1,1,0),
			new State(0,0,-1,0,1,1,0,1)
		};
	}
}
