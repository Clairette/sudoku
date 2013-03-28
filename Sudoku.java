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
* Creation date:	25.03.2013 <br> 
* Last updated:		28.03.2013 <br>
***********************************************/
import javax.swing.JOptionPane;

public class Sudoku {

	private Board board;
	private Board solution;
	private int level;
	
	/**
	* Create new Sudoku object. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	* 
	* @param level level of difficulty
	*/
	public Sudoku (int level) {
		this.level = level;
		generateBoard();
		getSolution();
	}
	
	/**
	* Get board. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	* 
	* return board
	*/	
	public Board getBoard () {
		return this.board;
	}
	
	/**
	* Generate board. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  -
	*/	
	public void generateBoard() {
		Generator generator = new Generator();
		generator.generateBoard(this.level);
		this.board = generator.getBoard();		
	}
	
	/**
	* Compute solution. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  - 
	*/
	private void getSolution() {
		Board solveBoard = new Board();
		solveBoard.copy(this.board);
		solveBoard.reset();
		Solver solver = new Solver(solveBoard);
		solver.solveBoard();
		this.solution = solver.getBoard();
	}		
	
	/**
	* Check board. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  - 
	*/
	public boolean[][] checkBoard() {
		Board checkBoard = new Board();
		checkBoard.copy(this.board);
		checkBoard.reset();

		Solver solver = new Solver(checkBoard);
		solver.solveBoard();
		checkBoard = solver.getBoard();

		return checkBoard.compare(this.board);
	}	
	
	/**
	* Is board successfully solved. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  - 
	* 
	* @return true if solved, otherwise false
	*/
	public boolean isSolved() {	
		return this.board.equals(this.solution);
	}
	
	/**
	* Solve board. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  - 
	*/
	public void solveBoard() {
		this.board = this.solution;
	}		
}
