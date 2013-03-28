import junit.framework.*;
import junit.textui.TestRunner;

public class TestBoard extends TestCase {
	
	public TestBoard () {
		super();
	}

	public void testEquals () {
		Sudoku sudoku = new Sudoku(1);
		assertTrue(sudoku.getBoard().equals(sudoku.getBoard()));
	}
	
	public static void main(String[] args) {
	    TestRunner.run(TestBoard.class);
	  }
}
