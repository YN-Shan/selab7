package rc;

public class Cell {
	private int row;
	private int column;
	
	public Cell() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	public void move_left() {
		this.column--;
	}
	
	public void move_right() {
		this.column++;
	}
	
	public void move_down() {
		this.row++;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
}
