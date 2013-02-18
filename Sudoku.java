/***********************************************
* Project: 			Sudoku
************************************************
*
* Authors: 		Claire Giry
*				Hans-Peter Hoellwirth
*				Scott Cantisani
*				Simranbir Singh
*				Oana Radu
*
* Creation date:	06.02.2013
* Last updated:		18.02.2013
***********************************************/
public class Sudoku {

	private int[][] board = new int[9][9];
	private int[][] srcBoard = new int[9][9];

	public Sudoku () {
		initBoard();
		printBoard();
		System.out.println();
		if (multSol() || !solveBoard()) {
			System.out.println("Invalid sudoku");
		} else {
			printBoard();
		}
	}

	public void initBoard () {
		int[][] temp = {
//			{4, 0, 0, 0, 8, 0, 5, 0, 6},
//			{0, 8, 0, 6, 0, 2, 0, 0, 4},
//			{0, 0, 6, 0, 9, 5, 0, 1, 0},
//			{0, 9, 1, 0, 0, 0, 0, 4, 0},
//			{0, 0, 0, 8, 4, 9, 0, 0, 0},
//			{0, 5, 0, 0, 0, 0, 3, 8, 0},
//			{0, 7, 0, 5, 2, 0, 8, 0, 0},
//			{9, 0, 0, 3, 0, 7, 0, 2, 0},
//			{5, 0, 2, 0, 1, 0, 0, 0, 3}
			{0, 5, 4, 0, 0, 0, 0, 9, 0},
			{0, 1, 3, 0, 9, 7, 0, 0, 0},
			{0, 0, 2, 0, 0, 0, 0, 0, 0},
			{7, 0, 0, 6, 0, 0, 2, 1, 0},
			{0, 0, 0, 5, 0, 9, 0, 0, 0},
			{0, 2, 5, 0, 0, 1, 0, 0, 9},
			{0, 0, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 4, 1, 0, 9, 3, 0},
			{0, 6, 0, 0, 0, 0, 8, 5, 0}
//			{1, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0}
		};

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = temp[i][j];
				srcBoard[i][j] = temp[i][j];
			}
		}
	}

	public void printBoard () {
		//rough print function! just console for now
		System.out.println("-------------------");
		for (int i = 0; i < 9; i++) {
			System.out.print("|");
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 0) {
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

	public boolean solveBoard () {
		return solveBoard(0, 0, 1);
	}

	private boolean solveBoard (int i, int j, int n) {

		int[] col = new int[9];
		int[] row = new int[9];
		int[] block = new int[9];

		if (i == 9 && j == 0) {
			//if at end of board, puzzle solved
			return true;
		} else {
			if (board[i][j] == 0) {
				//if the cell is empty
				while (n <= 9) {
					col = getCol(j);
					row = getRow(i);
					block = getBlock(i, j);

					if (contains(col, n) || contains(row, n) || contains(block, n)) {
						//find the first possible number for the cell
						n++;
					} else {
						//set the cell to this number
						board[i][j] = n;
						if (solveBoard(next(i, j)[0], next(i, j)[1], 1)) {
							//if the board is solvable with this number, return true
							board[i][j] = n;
							return true;
						} else {
							//if not, try the next possible number for the cell
							board[i][j] = 0;
							return solveBoard(i, j, n+1);
						}
					}
				}
				//if no possible numbers lead to a solution, return false
				return false;
			} else {
				//if not, check the validity of the number already in the cell
				n = board[i][j];
				board[i][j] = 0;
				
				col = getCol(j);
				row = getRow(i);
				block = getBlock(i, j);

				if (contains(col, n) || contains(row, n) || contains(block, n)) {
					//if the column, row, or block already contain this number, return false
					board[i][j] = n;
					return false;
				}
				else {
					//if not, keep solving the board
					board[i][j] = n;
					return solveBoard(next(i, j)[0], next(i, j)[1], 1);
				}
			}
		}
	}

	public boolean multSol () {
		//checks for multiple solutions by brute force. Definitely not the best way to do this but using it for now to test the generator
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (srcBoard[i][j] == 0) {
					//if a cell in the original puzzle is empty
					int n = 0;
					for (int k = 1; k < 10; k++) {
						//loop through all possible numbers for this cell
						resetBoard(0, 0);
						board[i][j] = k;
						if (solveBoard()) {
							//count all possible solutions for numbers in this cell
							n++;
						}
					}

					if (n > 1) {
						//if there is a possible solution for more than one number, return true
						return true;
					}
					board[i][j] = 0;
				}
			}
		}
		return false;
	}

	public void resetBoard (int i, int j) {
		//resets the puzzle from a certain cell to the end of the board
		for (j = j; j < 9; j++) {
			board[i][j] = srcBoard[i][j];
		}
		for (i = i + 1; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				board[i][j] = srcBoard[i][j];
			}
		}
	}

	public int[] next (int i, int j) {
		int[] next = new int[2];

		j += 1;
		if (j > 8) {
			i += 1;
			j = 0;
		}

		next[0] = i;
		next[1] = j;
		return next;
	}

	public int[] getCol (int j) {
		int[] col = new int[9];

		for (int i = 0; i < 9; i++) {
			col[i] = board[i][j];
		}
		return col;
	}

	public int[] getRow (int i) {
		return board[i];
	}

	public int[] getBlock (int i, int j) {
		//could use improvement
		int[] block = new int[9];

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

	public boolean contains (int[] array, int n) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == n) {
				return true;
			}
		}
		return false;
	}

	public static void main (String[] args) {
		Sudoku sudoku = new Sudoku();
	}

}
