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

public class Board {

	private int[][] board = new int[9][9];

	public Board () {
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

	public void copy (Board board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = board.getCell(i,j);
			}
		}
	}

	public int getCell (int i, int j) {
		return board[i][j];
	}

	public void setCell (int i, int j, int number) {
		board[i][j] = number;
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
}
