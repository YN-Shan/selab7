package rc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JPanel {
	
	public static final int GAMEON = 0;
	public static final int PAUSE = 1;
	public static final int GAMEOVER = 2;
	private int gameState;
	private int[]scorePool = {0,1,2,5,10};
	private int totalScore = 0;
	private int totalLine = 0;
	private static final int CELL_SIZE = 26;
	private Cell[][] wall = new Cell[20][10];
	private Tetromino currentOne=Tetromino.randomOne();
	private Tetromino nextOne=Tetromino.randomOne();

	public int getGameState() {
		return gameState;
	}
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
	public Cell[][] getWall() {
		return wall;
	}
	public void setWall(Cell[][] wall) {
		this.wall = wall;
	}
	public Tetromino getCurrentOne() {
		return currentOne;
	}
	public void setCurrentOne(Tetromino currentOne) {
		this.currentOne = currentOne;
	}
	public Tetromino getNextOne() {
		return nextOne;
	}
	public void setNextOne(Tetromino nextOne) {
		this.nextOne = nextOne;
	}

	
	public void paint(Graphics g) {
		super.paint(g);
		paintWall(g);
		paintCurrentOne(g);
		paintNextOne(g);
		paintScore(g);
		paintState(g);
	}
	
	public void paintScore(Graphics g) {
		g.setFont(new Font("Times New Roman",Font.BOLD,20));
		g.drawString("SCORES:"+totalScore, 330, 160);
		g.drawString("LINES:"+totalLine, 330, 200);
	}
	
 	public void paintState(Graphics g) {
		g.setFont(new Font("Times New Roman",Font.BOLD,20));
		g.setColor(Color.BLACK);
		if(gameState==GAMEON) {
			g.drawString("GAMEON", 330, 270);
			g.drawString("PAUSE [P]", 330, 300);
		}
		if(gameState==GAMEOVER) {
			g.drawString("GAMEOVER", 300, 270);
			g.drawString("RESTART [ENTER]", 300, 300);
		}
		if(gameState==PAUSE) {
			g.drawString("PAUSE", 330, 270);
			g.drawString("CONTINUE [C]", 330, 300);
		}
	}
	
	public void paintWall(Graphics g) {
		for(int i=0;i<20;i++)
			for(int j=0;j<10;j++) {
				int x=j*CELL_SIZE;
				int y=i*CELL_SIZE;
				Cell cell=wall[i][j];
				if (cell==null) {
					
					g.setColor(Color.GRAY);
					g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
					g.setColor(Color.WHITE);
					g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
				}
				else {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
				}
			}
	}
	
	public void paintCurrentOne(Graphics g) {
		Cell[] cells=currentOne.cells;
		for(Cell c:cells) {
			int x=c.getColumn()*CELL_SIZE;
			int y=c.getRow()*CELL_SIZE;
			g.setColor(Color.BLACK);
			g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
		}
			
	}
	
	public void paintNextOne(Graphics g) {
		Cell[] cells=nextOne.cells;
		for(Cell c:cells) {
			int x=c.getColumn()*CELL_SIZE+260;
			int y=c.getRow()*CELL_SIZE+26;
			g.setColor(Color.BLACK);
			g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
		}
	}
	
	public boolean isGameOver() {
		Cell[] cells=nextOne.cells;
		for(Cell c:cells) {
			int row=c.getRow();
			int column=c.getColumn();
			if(wall[row][column]!=null)
				return true;
		}
		return false;
	}
	
	public boolean isLineFull(int row) {
		Cell[] line=wall[row];
		for(Cell c:line) {
			if(c==null)
				return false;
		}
		return true;
	}
	
	public void destroyLine() {
		int lines=0;
		Cell[] cells=currentOne.cells;
		for(Cell c:cells) {
			int row=c.getRow();
			while(row<20) {
				if(isLineFull(row)) {
					lines++;
					wall[row]=new Cell[10];
					for(int i=row;i>0;i--) {
						System.arraycopy(wall[i-1], 0, wall[i], 0, 10);
					}
					wall[0]=new Cell[10];
				}
				row++;
			}
		}
		totalScore+=scorePool[lines];
		totalLine+=lines;
	}
	
	public boolean canDrop() {
		Cell cells[]=currentOne.cells;
		for(Cell c:cells) {
			int x=c.getRow();
			int y=c.getColumn();
			if(x==19||wall[x+1][y]!=null)
				return false;
		}
		return true;
	}

	public void landToWall() {
		Cell cell[]=currentOne.cells;
		for(Cell c:cell) {
			wall[c.getRow()][c.getColumn()]=c;
		}
	}

	public boolean outOfBounds() {
		Cell[] cell=currentOne.cells;
		for(Cell c:cell) {
			if(c.getRow()>19||c.getRow()<0||c.getColumn()>9||c.getColumn()<0)
				return true;
		}
		return false;
	}

	public boolean coincide() {
		Cell[] cell=currentOne.cells;
		for(Cell c:cell) {
			int row=c.getRow();
			int column=c.getColumn();
			if(wall[row][column]!=null)
				return true;
		}
		return false;
	}

	protected void moveleftAction() {
		currentOne.moveleft();
		if(outOfBounds()||coincide()) {
			currentOne.moveright();
		}
	}
	
	protected void moverightAction() {
		currentOne.moveright();
		if(outOfBounds()||coincide()) {
			currentOne.moveleft();
		}
	}

	public void softDropAction() {
		if(canDrop()) {
			currentOne.movedown();
		}
		else
		{
			landToWall();
			destroyLine();
			currentOne=nextOne;
			nextOne=Tetromino.randomOne();
		}
	}

	public void hardDropAction() {
		while(canDrop())
		{
			currentOne.movedown();
		}
		landToWall();
		destroyLine();
		if(!isGameOver()) {
			currentOne=nextOne;
			nextOne=Tetromino.randomOne();
		}
		else
			gameState=GAMEOVER;
	}

	public void RotateRightAction() {
		currentOne.rotate(true);
		if(outOfBounds()||coincide()) {
			currentOne.rotate(false);
		}
	}

	public void start() {
		gameState=GAMEON;
		KeyListener l=new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code=e.getKeyCode();
				if(code==KeyEvent.VK_P) {
					if(gameState==GAMEON)
						gameState=PAUSE;	
				}
				if(code==KeyEvent.VK_C) {
					if(gameState==PAUSE)
						gameState=GAMEON;	
				}
				if(code==KeyEvent.VK_ENTER) {
					gameState=GAMEON;
					wall=new Cell[20][10];
					currentOne=Tetromino.randomOne();
					nextOne=Tetromino.randomOne();
					totalScore=0;
					totalLine=0;
				}
				switch(code) {
				case KeyEvent.VK_DOWN:
					softDropAction();
					break;
				case KeyEvent.VK_LEFT:
					moveleftAction();
					break;
				case KeyEvent.VK_RIGHT:
					moverightAction();
					break;
				case KeyEvent.VK_UP:
					RotateRightAction();
					break;
				case KeyEvent.VK_SPACE:
					hardDropAction();
					break;
				}
				repaint();
			}
		};
		this.addKeyListener(l);
		this.requestFocus();
		while(true) {
			try {
				Thread.sleep(800);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(gameState==GAMEON) {
				if(canDrop()) {
					currentOne.movedown();
				}else {
					landToWall();
					destroyLine();
					if(!isGameOver()) {
						currentOne=nextOne;
						nextOne=Tetromino.randomOne();
						
					}else {
						gameState=GAMEOVER;
					}
				}
				repaint();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf=new JFrame("TETRIS");
		Tetris panel=new Tetris();
		jf.add(panel);
		jf.setVisible(true);
		jf.setSize(500, 600);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.start();
	}

}
