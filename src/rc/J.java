package rc;

/**   J�Ϳ�
 *    1 0 2
 *        3
 */
public class J extends Tetromino {
	public J(int row,int column) {
		cells[0]=new Cell(row,column);
		cells[1]=new Cell(row,column-1);
		cells[2]=new Cell(row,column+1);
		cells[3]=new Cell(row+1,column+1);
		states=new State[] {
		new State(0,0,0,1,0,-1,-1,-1),
		new State(0,0,1,0,-1,0,-1,1),
		new State(0,0,0,-1,0,1,1,1),
		new State(0,0,-1,0,1,0,1,-1)
		};
	}
}
