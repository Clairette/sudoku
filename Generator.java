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

import java.util.Random;

public class Generator {

	private Board board = new Board();
	private Board srcBoard = new Board(); //srcBoard is a board to backtrack to if something goes wrong

	public Generator () {
	}

	public Board getBoard () {
		return this.board;
	}

	public void generateBoard (int diff) {
		initBoard();
		Random random = new Random();
		if (diff == 1) {
			dig(30 + random.nextInt(10));
		} else if (diff == 2) {
			dig(40 + random.nextInt(10));
		} else {
			dig(50 + random.nextInt(10));
		}
	}

	private void resetBoard () {
		board.copy(srcBoard);
	}

	private void initBoard () {
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

				if (!board.contains(i, j, n)) {
					board.setCell(i,j,n);
				}
			}
		} while (!solvable());

		//once a solution is found, set srcBoard to the solved board
		srcBoard.copy(board);
	}

	private int random () {
		Random rand = new Random();
		return rand.nextInt(9);
	}

	private boolean solvable () {
		Solver solver = new Solver(board);
		return solver.solveBoard();
	}


	private boolean multSol () {
		//checks for multiple solutions by brute force. Definitely not the best way to do this but using it for now to test the generator
		int n = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (srcBoard.getCell(i,j) == 0) {
					//if a cell in the original puzzle is empty
					for (int k = 1; k < 10; k++) {
						//loop through all possible numbers for this cell
						resetBoard();
						board.setCell(i,j,k);
						if (!board.contains(i, j, k) && solvable()) {
							//count all possible solutions for numbers in this cell
							n++;
						}
					}

					if (n > 1) {
						//if there is more than one possible solution, return true
						return true;
					}
					board.setCell(i,j,0);
				}
			}
		}
		//if after testing all possible numbers for all empty cells there is not more than one solution, return false
		return false;
	}

	private void dig (int n) {
		//make n randomly placed cells empty
		int i, j, k, dug = 0;

		while (dug < n) {
			i = random();
			j = random();
			if (board.getCell(i,j) != 0) {
				k = board.getCell(i,j);
				board.setCell(i,j,0);
				if (!multSol()) {
					dug++;
				} else {
					board.setCell(i,j,k);
				}
			}
		}
	}
}