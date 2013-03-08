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
* Creation date:	14.02.2013 <br>
* Last updated:		28.02.2013 <br>
***********************************************/
//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuGUI extends Frame {

	private Board board;
	private Panel panel = new Panel();

	/**
	* Create new SudokuGUI object. <p>
	* 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	*/
	public SudokuGUI () {
		tempBoard();
		initWindow();
	}

	/**
	* Simulate behaviour of buttons. <p>
	* 
	* NOTE: temporary function <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	*/
	public void tempBoard () {
		Generator generator = new Generator();
		generator.generateBoard(2);
		board = generator.getBoard();

		board.printBoard();
		System.out.println();

		Solver solver = new Solver(board);
		solver.solveBoard();
		board = solver.getBoard();

		board.printBoard();
	}

	/**
	* Initialize window <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*/
	public void initWindow () {
		setTitle("Sudoku");
	    setSize(200,200);
     	addWindowListener(new SudokuWindowListener());
		drawPanel();
		setVisible(true);
	}

	/**
	* Draw panel <p>
	* 
	* @author 
	* Created by: - <br>
	* Edited by:  -
	*/
	public void drawPanel () {

	}

   	class SudokuWindowListener extends WindowAdapter
   	{
   		/**
   		* Terminate application if window gets closed <p>
   		* 
   		* @author 
   		* Created by: Hans-Peter Hoellwirth <br>
   		* Edited by:  -
   		*/
   		public void windowClosing (WindowEvent e)
     	{
     		e.getWindow().dispose();
       		System.exit(0);
     	}
   	}

	public static void main (String[] args) {
		new SudokuGUI();
	}
}