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
* Creation date:	06.02.2013 <br>
* Last updated:		08.03.2013 <br>
***********************************************/

public class Board {

	private Cell[][] board = new Cell[9][9];

	/**
	* Create new board object.
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	*/	
	public Board () {
	}

	/**
	* Get cell from given postion. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @param i ith row on board (0-8)
	* @param j jth column on board (0-8)
	* @return cell at position (i,j)
	*/	
	public Cell getCell (int i, int j) {
		return board[i][j];
	}

	/**
	* Set cell at given postion. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @param i ith row on board (0-8)
	* @param j jth column on board (0-8)
	* @param c cell
	*/
	public void setCell (int i, int j, Cell c) {
		board[i][j] = c;
	}

	/**
	* Set new cell at given postion. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @param i ith row on board (0-8)
	* @param j jth column on board (0-8)
	* @param number number of cell (1-9)
	* @param fixed  status of cell (fixed or variable)
	*/
	public void setCell (int i, int j, int number, boolean fixed) {
		if (board[i][j] == null) {
			board[i][j] = new Cell (number, fixed);
		} else {
			board[i][j].setNumber(number);
			board[i][j].setFixed(fixed);
		}
	}

	/**
	* Get given column on board. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  -
	*
	* @param j jth column on board (0-8)
	* @return jth column as an array of cells
	*/
	public Cell[] getCol (int j) {
		Cell[] col = new Cell[9];

		for (int i = 0; i < 9; i++) {
			col[i] = board[i][j];
		}
		return col;
	}

	/**
	* Get given row on board. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  -
	*
	* @param i ith column on board (0-8)
	* @return ith row as an array of cells
	*/
	public Cell[] getRow (int i) {
		return board[i];
	}

	/**
	* Get block of ith-jth cell on board. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  -
	*
	* @param i ith row on board (0-8)
	* @param j jth column on board (0-8)
	* @return block of ith-jth cell as array of cells
	*/
	public Cell[] getBlock (int i, int j) {
		//could use improvement
		Cell[] block = new Cell[9];

		i = i - (i % 3);
		j = j - (j % 3);
		int b = 0;

		for (int n = i; n < i+3; n++) {
			for (int m = j; m < j+3; m++) {
				block[b] = board[n][m];
				b++;
			}
		}
		return block;
	}

	/**
	* Check if number is already contained in row, column or block of given position. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  -
	*
	* @param i ith row on board (0-8)
	* @param j jth column on board (0-8)
	* @param n number (1-9)
	* @return true if number is already contained, otherwise false
	*/
	public boolean contains(int i, int j, int n) {
		Cell[] col = getCol(j);
		Cell[] row = getRow(i);
		Cell[] block = getBlock(i, j);

		if(contains(col, n) || contains(row, n) || contains(block, n)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	* Check if number already occurs in array. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  -
	*
	* @param array array of cells
	* @param n number (1-9)
	* @return true if number occurs, otherwise false
	*/
	private boolean contains (Cell[] array, int n) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i].equals(n)) {
				return true;
			}
		}
		return false;
	}

	/**
	* Console output of board. <p>
	* 
	* @author 
	* Created by:	Scott Cantisani <br>
	* Edited by:	-
	*/
	public void printBoard () {
		System.out.println("-------------------");
		for (int i = 0; i < 9; i++) {
			System.out.print("|");
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == null || board[i][j].equals(0)) {
					System.out.print("*");
				} else {
					System.out.print(board[i][j]);
				}
				if (j % 3 == 2) {
					System.out.print("|");
				} else {
					System.out.print(" ");
				}
			}
			if (i % 3 == 2) {
				System.out.println("\n-------------------");
			} else {
				System.out.println();
			}
		}
	}

	/**
	* Copies the cells of a given board. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @param board the board to copy
	*/	
	public void copy (Board board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = board.getCell(i,j);
			}
		}
	}

	/**
	* Clears the board. <p>
	*
	* @author
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*/
	public void clear () {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = new Cell(0, false);
			}
		}
	}

	/**
	* Removes all user input from the board. <p>
	*
	* @author
	* Created by:	Scott Cantisani <br>
	* Edited by:	-
	*/
	public void reset () {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!getCell(i, j).isFixed()) {
					this.board[i][j] = new Cell(0, false);
				}
			}
		}
	}
	
	/**
	 * Compares two boards. <p>
	 *
	 * @author
	 * Created by:	Scott Cantisani <br>
	 * Edited by:	-
	 *
	 * @param compBoard the board to be compared
	 * @return matrix of booleans: true if same number in cell, false if not
	 */
	public boolean[][] compare (Board compBoard) {
		boolean[][] bools = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (getCell(i, j).equals(compBoard.getCell(i, j))) {
					bools[i][j] = true;
				} else {
					bools[i][j] = false;
				}
			}
		}
		return bools;
	}

	/**
	 * Compares two boards. <p>
	 *
	 * @author
	 * Created by:	Hans-Peter Hoellwirth <br>
	 * Edited by:	-
	 *
	 * @param board the board to be compared
	 * @return true if boards are equal, otherwise false
	 */
	public boolean equals (Board board) {
		boolean[][] matchMatrix = this.compare(board);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!matchMatrix[i][j]) {
					return false;
				}
			}
		}
		return true;			
	}
}
