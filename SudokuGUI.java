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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuGUI extends JFrame {
	
	private Grid grid;
	private JButton solve;
	private JButton check;
	private JButton generate;

	/**
	* Create new SudokuGUI object. <p>
	* 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	*/
	public SudokuGUI () {
		initWindow();
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
		setSize(450, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		drawPanel();
		setVisible(true);
	}

	/**
	* Draw panel <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  Scott Cantisani (14.03.2013)
	*/
	public void drawPanel () {
		setLayout(new BorderLayout(0, 40));
	
		this.grid = new Grid();
		add(this.grid, BorderLayout.CENTER);
		
		this.generate = new JButton("Generate"); 		
		this.generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				generateBoard();
			}
		});

		this.check = new JButton ("Check");
		this.check.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e)
			{
				checkBoard();
			}
		});

		this.solve = new JButton("Solve");
		this.solve.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e)
			{
				solveBoard();
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3, 20, 20));
		buttons.add(this.generate);
		buttons.add(this.check);
		buttons.add(this.solve);		
		add(buttons, BorderLayout.SOUTH);
	}
	
	/**
	* Solve board. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  - 
	*/
	private void solveBoard() {
		Board solveBoard = this.grid.getBoard();
		solveBoard.reset();
		Solver solver = new Solver(solveBoard);
		solver.solveBoard();
		this.grid.setBoard(solver.getBoard());
	}
	
	/**
	* Check board. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  - 
	*/
	private void checkBoard() {
		Board checkBoard = new Board();
		checkBoard.copy(this.grid.getBoard());
		checkBoard.reset();

		Solver solver = new Solver(checkBoard);
		solver.solveBoard();
		checkBoard = solver.getBoard();

		boolean[][] compared = checkBoard.compare(this.grid.getBoard());
		this.grid.highlightCells(compared);

		boolean solved = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!compared[i][j]) {
					solved = false;
				}
			}
		}
		if (solved) {
			JOptionPane.showMessageDialog(null, "All correct! Congratulations!");
		}
	}

	/**
	* Generate board. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  - 
	*/
	private void generateBoard () {
		Generator generator = new Generator();
		generator.generateBoard(2);
		this.grid.setBoard(generator.getBoard());
	}
	
	public static void main (String[] args) {
		new SudokuGUI();
	}
}
