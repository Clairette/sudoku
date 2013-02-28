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
* Creation date:	14.02.2013
* Last updated:		28.02.2013
***********************************************/
//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuGUI extends Frame {

	private Board board;
	private Panel panel = new Panel();

	public SudokuGUI () {
		tempBoard();
		initWindow();
	}

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

	public void initWindow () {
		setTitle("Sudoku");
	    setSize(200,200);
     	addWindowListener(new SudokuWindowListener());
		drawPanel();
		setVisible(true);
	}

	public void drawPanel () {

	}

   	class SudokuWindowListener extends WindowAdapter
   	{
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