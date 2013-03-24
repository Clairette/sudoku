import javax.swing.JOptionPane;


public class Sudoku {

	private Board board;
	
	public Sudoku () {
		generateBoard();
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
		generator.generateBoard(3);
		this.board = generator.getBoard();		
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
		boolean solved = true;
		boolean[][] matchMatrix = this.checkBoard();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!matchMatrix[i][j]) {
					solved = false;
				}
			}
		}
		return solved;	
	}
	
	/**
	* Solve board. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  - 
	*/
	public void solveBoard() {
		Board solveBoard = this.board;
		solveBoard.reset();
		Solver solver = new Solver(solveBoard);
		solver.solveBoard();
		this.board = solver.getBoard();
	}	
}
