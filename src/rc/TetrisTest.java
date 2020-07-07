package rc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TetrisTest {
	
	private static Tetris T=new Tetris();
	
	@Before
	public void setUp() throws Exception {
		T.setNextOne(new J(0,4));
		T.setCurrentOne(new S(9,4));
		Cell[][] wall=new Cell[20][10];
		for(int i=16;i<20;i++)
			for(int j=0;j<10;j++) {
				wall[i][j]=new Cell(i,j);
			}
		wall[17][0]=null;
		wall[17][2]=null;
		wall[17][4]=null;
		wall[17][5]=null;
		wall[17][6]=null;
		wall[17][8]=null;
		wall[18][0]=null;
		wall[18][4]=null;
		wall[19][2]=null;
		wall[15][6]=new Cell(15,6);
		wall[14][6]=new Cell(14,6);
		wall[13][6]=new Cell(13,6);
		T.setWall(wall);
		
	}

	@Test
	public void testIsGameOver() {
		boolean flag=T.isGameOver();
		assertEquals("game state judge fault",false,flag);
	}

	@Test
	public void testIsLineFull() {
		boolean fullline=T.isLineFull(16);
		boolean notfullline=T.isLineFull(15);
		assertEquals("line-full judge fault",true,fullline);
		assertEquals("line-full judge fault",false,notfullline);
	}

	@Test
	public void testCanDrop() {
		boolean dropable=T.canDrop();
		assertEquals("can-drop judge fault",true,dropable);
	}

	@Test
	public void testLandToWall() {
		T.landToWall();
		Cell[][] w=new Cell[20][10];
		w=T.getWall();
		Tetromino t=new Tetromino();
		t=T.getCurrentOne();
		Cell[] cs=t.getCells();
		boolean alreadylanded=(w[cs[0].getRow()][cs[0].getColumn()]!=null)
				            &&(w[cs[1].getRow()][cs[1].getColumn()]!=null)
				            &&(w[cs[2].getRow()][cs[2].getColumn()]!=null)
				            &&(w[cs[3].getRow()][cs[3].getColumn()]!=null);
		assertEquals("land-to-wall failure",true,alreadylanded);
	}

	@Test
	public void testOutOfBounds() {
		boolean outofbounds=T.outOfBounds();
		assertEquals("out-of-bounds judge failure",false,outofbounds);
	}

	@Test
	public void testCoincide() {
		boolean coinci=T.coincide();
		assertEquals("coincide judge failure",false,coinci);
	}

	@Test
	public void testMoveleftAction() {
		Tetromino t=new Tetromino();
		t=T.getCurrentOne();
		Cell[] cs=new Cell[4];
		cs=t.getCells();
		int[] row1=new int[4];
		int[] col1=new int[4];
		for(int i=0;i<4;i++) {
			row1[i]=cs[i].getRow();
			col1[i]=cs[i].getColumn();
		}
		int[] row2=new int[4];
		int[] col2=new int[4];	
		T.moveleftAction();
		t=T.getCurrentOne();
		cs=t.getCells();
		for(int i=0;i<4;i++) {
			row2[i]=cs[i].getRow();
			col2[i]=cs[i].getColumn();
		}
		boolean alreadymoved=true;
		for(int i=0;i<4;i++) {
			if(row1[i]!=row2[i]||col1[i]!=col2[i]+1)
				alreadymoved=false;
		}
		assertEquals("move-left-failure",true,alreadymoved);
	}

	@Test
	public void testMoverightAction() {
		Tetromino t=new Tetromino();
		t=T.getCurrentOne();
		Cell[] cs=new Cell[4];
		cs=t.getCells();
		int[] row1=new int[4];
		int[] col1=new int[4];
		for(int i=0;i<4;i++) {
			row1[i]=cs[i].getRow();
			col1[i]=cs[i].getColumn();
		}
		int[] row2=new int[4];
		int[] col2=new int[4];	
		T.moverightAction();
		t=T.getCurrentOne();
		cs=t.getCells();
		for(int i=0;i<4;i++) {
			row2[i]=cs[i].getRow();
			col2[i]=cs[i].getColumn();
		}
		boolean alreadymoved=true;
		for(int i=0;i<4;i++) {
			if(row1[i]!=row2[i]||col1[i]!=col2[i]-1)
				alreadymoved=false;
		}
		assertEquals("move-right-failure",true,alreadymoved);
	}

	@Test
	public void testSoftDropAction() {
		
		Tetromino t=new Tetromino();
		t=T.getCurrentOne();
		Cell[] cs=t.getCells();
		int[] row1=new int[4];
		int[] col1=new int[4];
		for(int i=0;i<4;i++) {
			row1[i]=cs[i].getRow();
			col1[i]=cs[i].getColumn();
		}
		T.softDropAction();
		int[] row2=new int[4];
		int[] col2=new int[4];
		t=T.getCurrentOne();
		cs=t.getCells();
		for(int i=0;i<4;i++) {
			row2[i]=cs[i].getRow();
			col2[i]=cs[i].getColumn();
		}
		boolean alreadysoftdropped=true;
		for(int i=0;i<4;i++) {
			if(row1[i]!=row2[i]-1||col1[i]!=col2[i]) {
				alreadysoftdropped=false;
			}
		}
		assertEquals("soft-drop-failure",true,alreadysoftdropped);
	}

	@Test
	public void testRotateRightAction() {
		Tetromino t=new Tetromino();
		Tetromino t1=new Tetromino();
		t1=T.getCurrentOne();
		t1.rotate(true);
		
		Cell[] cs=t1.getCells();
		int[] row1=new int[4];
		int[] col1=new int[4];
		for(int i=0;i<4;i++) {
			row1[i]=cs[i].getRow();
			col1[i]=cs[i].getColumn();
		}
		int[] row2=new int[4];
		int[] col2=new int[4];
		T.setCurrentOne(new S(9,4));
		T.RotateRightAction();
		t=T.getCurrentOne();
		cs=t.getCells();
		for(int i=0;i<4;i++) {
			row2[i]=cs[i].getRow();
			col2[i]=cs[i].getColumn();
		}
		boolean alreadyrotated=true;
		for(int i=0;i<4;i++) {
			if(row1[i]!=row2[i]||col1[i]!=col2[i])
				alreadyrotated=false;
		}
		assertEquals("rotate-right-failure",true,alreadyrotated);
	}
	
	@After
	public void resetstate() {
		T.setNextOne(new J(0,4));
		T.setCurrentOne(new S(9,4));
		Cell[][] wall=new Cell[20][10];
		for(int i=16;i<20;i++)
			for(int j=0;j<10;j++) {
				wall[i][j]=new Cell(i,j);
			}
		wall[17][0]=null;
		wall[17][2]=null;
		wall[17][4]=null;
		wall[17][5]=null;
		wall[17][6]=null;
		wall[17][8]=null;
		wall[18][0]=null;
		wall[18][4]=null;
		wall[19][2]=null;
		wall[15][6]=new Cell(15,6);
		wall[14][6]=new Cell(14,6);
		wall[13][6]=new Cell(13,6);
		T.setWall(wall);
		
	}
}
