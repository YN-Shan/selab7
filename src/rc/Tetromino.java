package rc;

public class Tetromino {
	protected static int row=0;
	protected static int column=4;
	protected Cell[] cells=new Cell[4];
	protected State[] states;
	private int counter=1000;
	
	public class State{
		int r0,c0,r1,c1,r2,c2,r3,c3;

		public State() {
			super();
			// TODO Auto-generated constructor stub
		}

		public State(int r0, int c0, int r1, int c1, int r2, int c2, int r3, int c3) {
			super();
			this.r0 = r0;
			this.c0 = c0;
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.r3 = r3;
			this.c3 = c3;
		}

		public int getR0() {
			return r0;
		}

		public void setR0(int r0) {
			this.r0 = r0;
		}

		public int getC0() {
			return c0;
		}

		public void setC0(int c0) {
			this.c0 = c0;
		}

		public int getR1() {
			return r1;
		}

		public void setR1(int r1) {
			this.r1 = r1;
		}

		public int getC1() {
			return c1;
		}

		public void setC1(int c1) {
			this.c1 = c1;
		}

		public int getR2() {
			return r2;
		}

		public void setR2(int r2) {
			this.r2 = r2;
		}

		public int getC2() {
			return c2;
		}

		public void setC2(int c2) {
			this.c2 = c2;
		}

		public int getR3() {
			return r3;
		}

		public void setR3(int r3) {
			this.r3 = r3;
		}

		public int getC3() {
			return c3;
		}

		public void setC3(int c3) {
			this.c3 = c3;
		}
	}
	
	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

	public void moveleft() {
		for(int i=0;i<4;i++)
			cells[i].move_left();
	}
	
	public void moveright() {
		for(int i=0;i<4;i++)
			cells[i].move_right();
	}
	
	public void movedown() {
		for(int i=0;i<4;i++)
			cells[i].move_down();
	}
	
	public void rotate(boolean flg) {
		if(flg==true)//向右旋转
			counter++;
		else//向左旋转
			counter--;
		State s=states[counter%states.length];
		Cell c=cells[0];
		int row=c.getRow();
		int column=c.getColumn();
		cells[1].setRow(row+s.r1);
		cells[1].setColumn(column+s.c1);
		cells[2].setRow(row+s.r2);
		cells[2].setColumn(column+s.c2);
		cells[3].setRow(row+s.r3);
		cells[3].setColumn(column+s.c3);
	}
	
	public static Tetromino randomOne() {
		Tetromino t = null;
		int num=(int)(Math.random()*7);
		switch(num) {
		case 0:t=new T(row,column);break;
		case 1:t=new O(row,column);break;
		case 2:t=new I(row,column);break;
		case 3:t=new J(row,column);break;
		case 4:t=new S(row,column);break;
		case 5:t=new L(row,column);break;
		case 6:t=new Z(row,column);break;
		}
		return t;
	}
	

	
	
}
