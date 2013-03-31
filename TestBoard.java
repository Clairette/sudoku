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
* Creation date:	29.03.2013 <br>
* Last updated:		29.03.2013 <br>
***********************************************/

import junit.framework.*;
import junit.textui.TestRunner;

public class TestBoard extends TestCase {
	
	public TestBoard () {
		super();
	}
	
	/**
	* Test method Board.setCell <p>
	* 
	* Expected to set cell to given value. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*/
	public void testSetCell () {
		Sudoku sudoku = new Sudoku(1);
		sudoku.getBoard().setCell(0, 0, 7, false);
		assertEquals(sudoku.getBoard().getCell(0,0).getNumber(), 7);
		assertEquals(sudoku.getBoard().getCell(0,0).isFixed(), false);
	}	
	
	/**
	* Test method Board.contains <p>
	* 
	* Expected to return true if duplicate numbers are found in row, column or block. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*/
	public void testCopy () {
		Sudoku sudoku1 = new Sudoku(1);
		Sudoku sudoku2 = new Sudoku(1);
		sudoku2.getBoard().copy(sudoku1.getBoard());
		assertTrue(sudoku2.getBoard().equals(sudoku1.getBoard()));
	}	
	
	/**
	* Test method Board.copy <p>
	* 
	* Expected to copy all values of the board. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*/
	public void testContains () {
		Sudoku sudoku = new Sudoku(1);
		sudoku.getBoard().setCell(0, 0, 7, false);
		assertTrue(sudoku.getBoard().contains(0, 8, 7));
		assertTrue(sudoku.getBoard().contains(8, 0, 7));
		assertTrue(sudoku.getBoard().contains(1, 1, 7));
	}	

	/**
	* Test method Board.equals <p>
	* 
	* Expected to return true if all cell numbers are equal. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*/	
	public void testEquals () {
		Sudoku sudoku = new Sudoku(1);
		assertTrue(sudoku.getBoard().equals(sudoku.getBoard()));
	}

	/**
	* Test method Board.clear <p>
	* 
	* Expected to set all cell numbers to 0. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*/	
	public void testClear () {
		Board board = new Board();
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				board.setCell(i, j, 0, false);
			}
		}
		Sudoku sudoku = new Sudoku(1);
		sudoku.getBoard().clear();
		assertTrue(board.equals(sudoku.getBoard()));
	}

	/**
	* Test method Board.reset <p>
	* 
	* Expected to set all user cell numbers to 0. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*/
	public void testReset () {
		Sudoku sudoku = new Sudoku(1);
		Board initial = sudoku.getBoard();
		sudoku.solveBoard();
		sudoku.getBoard().reset();
		assertTrue(initial.equals(sudoku.getBoard()));
	}
	
	/**
	* Test row/column/block sums of solved board. <p>
	* 
	* Each row, column and block sum is expected to be 45. <p>
	* 
	* @author 
	* Created by: Claire Giry <br>
	* Edited by:  Hans-Peter Hoellwirth
	*/
	public void testSubTotals () {
		Sudoku sudoku = new Sudoku(1);
		sudoku.solveBoard();
		
		for(int i = 0; i < 9; i++) {
			assertEquals (sum(sudoku.getBoard().getRow(i)), 45);
			assertEquals (sum(sudoku.getBoard().getCol(i)), 45);
			for(int j = 0; j < 3; j++) {
				assertEquals (sum(sudoku.getBoard().getBlock(i, j*3)), 45);
			}
		}
	}	

	private int sum (Cell[] array) {
		int sum = 0;
		for (int i=0; i<array.length; i++) {
			sum += array[i].getNumber();
		}
		return sum;
	}
	
	public static void main(String[] args) {
	    TestRunner.run(TestBoard.class);
	  }
}
