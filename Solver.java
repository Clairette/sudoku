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
* Creation date:	28.02.2013
* Last updated:		28.02.2013
***********************************************/

public class Solver {

	private Board board;


	public Solver (Board board) {
		this.board = board;
	}

	public Board getBoard () {
		return this.board;
	}

	public boolean solveBoard () {
		return solveBoard(0, 0, 1);
	}

	private boolean solveBoard (int i, int j, int n) {
		if (i == 9 && j == 0) {
			//if at end of board, puzzle solved
			return true;
		} else {
			if (board.getCell(i,j) == 0) {
				//if the cell is empty
				while (n <= 9) {
					if (board.contains(i, j, n)) {
						//find the first possible number for the cell
						n++;
					} else {
						//set the cell to this number
						board.setCell(i,j,n);
						if (solveBoard(next(i, j)[0], next(i, j)[1], 1)) {
							//if the board is solvable with this number, return true
							board.setCell(i,j,n);
							return true;
						} else {
							//if not, try the next possible number for the cell
							board.setCell(i,j,0);
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

	private int[] next (int i, int j) {
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
}