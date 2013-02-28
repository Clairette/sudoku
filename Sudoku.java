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
* Last updated:		28.02.2013
***********************************************/

import java.util.Random;

public class Sudoku {

	//board is for solving and testing
	private int[][] board = new int[9][9];
	//srcBoard is a board to backtrack to if something goes wrong
	private int[][] srcBoard = new int[9][9];

	public Sudoku () {
		initBoard(2);
		printBoard();
		System.out.println();
		solveBoard();
		printBoard();
	}

	public void initBoard (int diff) {
		genBoard();
		Random random = new Random();
		if (diff == 1) {
			dig(30 + random.nextInt(10));
		} else if (diff == 2) {
			dig(40 + random.nextInt(10));
		} else {
			dig(50 + random.nextInt(10));
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
		if (i == 9 && j == 0) {
			//if at end of board, puzzle solved
			return true;
		} else {
			if (board[i][j] == 0) {
				//if the cell is empty
				while (n <= 9) {
					if (contains(i, j, n)) {
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
				//if not, go to the next cell
				return solveBoard(next(i, j)[0], next(i, j)[1], 1);
			}
		}
	}

	public boolean multSol () {
		//checks for multiple solutions by brute force. Definitely not the best way to do this but using it for now to test the generator
		int n = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (srcBoard[i][j] == 0) {
					//if a cell in the original puzzle is empty
					for (int k = 1; k < 10; k++) {
						//loop through all possible numbers for this cell
						resetBoard();
						board[i][j] = k;
						if (!contains(i, j, k) && solveBoard()) {
							//count all possible solutions for numbers in this cell
							n++;
						}
					}

					if (n > 1) {
						//if there is more than one possible solution, return true
						return true;
					}
					board[i][j] = 0;
				}
			}
		}
		//if after testing all possible numbers for all empty cells there is not more than one solution, return false
		return false;
	}

	public void resetBoard () {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
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

	public boolean contains(int i, int j, int n) {
		int[] col = getCol(j);
		int[] row = getRow(i);
		int[] block = getBlock(i, j);

		if(contains(col, n) || contains(row, n) || contains(block, n)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean contains (int[] array, int n) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == n) {
				return true;
			}
		}
		return false;
	}

//	public boolean valid () {
		//this function is unnecessary right now--was used before for testing the generator, might come in handy again later?
//		int n;
//
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				if (board[i][j] != 0) {
//					n = board[i][j];
//					board[i][j] = 0;
//					
//					if (contains(i, j, n)) {
//						board[i][j] = n;
//						return false;
//					} else {
//						board[i][j] = n;
//					}
//				}
//			}
//		}
//		return true;
//	}
	
	private int random () {
		Random rand = new Random();
		return rand.nextInt(9);
	}

	private void genBoard () {
		//generates a valid filled sudoku puzzle
		int i, j, n;

		do {
			resetBoard();
			//fill the board up randomly with numbers, then attempt to solve it
			//80 attempts at filling a cell (the actual number of filled cells will be 35-45) seems to make for a good balance between solve speed (faster if there are more filled cells) and randomness of puzzles generated
			for (int k = 0; k < 80; k++) {
				i = random();
				j = random();
				n = random() + 1;

				if (!contains(i, j, n)) {
					board[i][j] = n;
				}
			}
		} while (!solveBoard());

		//once a solution is found, set srcBoard to the solved board
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				srcBoard[i][j] = board[i][j];
			}
		}
	}

	private void dig (int n) {
		//make n randomly placed cells empty
		int i, j, k, dug = 0;

		while (dug < n) {
			i = random();
			j = random();
			if (board[i][j] != 0) {
				k = board[i][j];
				board[i][j] = 0;
				if (!multSol()) {
					dug++;
				} else {
					board[i][j] = k;
				}
			}
		}
	}

	public static void main (String[] args) {
		Sudoku sudoku = new Sudoku();
	}

}
