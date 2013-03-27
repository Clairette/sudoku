/***********************************************
* Project: 			Sudoku
************************************************
*
* @author 	Claire Giry           <br>
*			Hans-Peter Hoellwirth <br>
*			Scott Cantisani       <br>
*			Simranbir Singh       <br>
*			Oana Radu             <br>
*
* @since
* Creation date:	28.02.2013 <br> 
* Last updated:		08.03.2013 <br>
***********************************************/
import java.awt.*;
import javax.swing.*;

public class Grid extends JPanel {

	private Sudoku sudoku;
	private GridCell[][] gridCells = new GridCell[9][9];

	/**
	* Create new grid object and generate initial board. <p>
	* 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	* 
	* @param level level of difficulty
	*/
	public Grid (int level) {
		this.sudoku = new Sudoku(level);
		this.initGrid();
	}

	/**
	* Overwrite existing sudoku. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	* 
	* @param sudoku new sudoku
	*/	
	public void setSudoku (Sudoku sudoku) {
		this.sudoku = sudoku;
		this.refreshGrid();
	}
	
	/**
	* Get sudoku. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	* 
	* return sudoku
	*/	
	public Sudoku getSudoku () {
		return this.sudoku;
	}

	/**
	* Refresh grid. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  - 
	*/
	public void refreshGrid () {
		this.drawGridCells();
		this.updateUI();
	}

	/**
	* Update board cell. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	* 
	* @param row row in board
	* @param column column in board
	* @param number new number 
	*/
	public void updateBoardCell (int row, int column, int number) {
		this.sudoku.getBoard().setCell(row, column, number, false);
		this.sudoku.getBoard().printBoard();
	}

	/**
	* Initialize grid. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  - 
	*/
	private void initGrid () {
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout (9, 9));
		this.drawGridCells();
	}

	/**
	* Draw grid cells. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  - 
	*/
	private void drawGridCells () {
		this.removeAll();
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j++) {
				this.gridCells[i][j] = new GridCell(this, i, j, this.sudoku.getBoard().getCell(i, j));
				this.add(this.gridCells[i][j]);
			}
		}		
	}

	/**
	* Paint components. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  - 
	*/
	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(Color.white);
		super.paintComponent(g2);
	
		int width = getSize().width;
		int height = getSize().height;

		for (int i = 1; i < 10; i++) {
			if (i % 3 == 0) {
				g2.setStroke(new BasicStroke(3));
				g2.drawLine(i * width/9, 0, i * width/9, height);
				g2.drawLine(0, i * height/9, width, i * height/9);
				g2.setStroke(new BasicStroke(1));
			} else {
				g2.drawLine(i * width/9, 0, i * width/9, height);
				g2.drawLine(0, i * height/9, width, i * height/9);
			}
		}
	}
	
	/**
	* Highlight cells. <p>
	*
	* @author
	* Created by:	Hans-Peter Hoellwirth <br>
	* Edited by	-
	*
	* @param checkMatrix matrix of booleans corresponding to cells
	*/
	public void highlightCells (boolean[][] checkMatrix) {
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j++) {
				this.gridCells[i][j].highlightCell(checkMatrix[i][j]);
			}
		}
	}
}

