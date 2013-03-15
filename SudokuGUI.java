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
		this.check = new JButton ("Check");
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
		System.out.println("solve");
		Solver solver = new Solver(this.grid.getBoard());
		solver.solveBoard();
		this.grid.setBoard(solver.getBoard());
		this.grid.getBoard().printBoard();
	}
	
	public static void main (String[] args) {
		new SudokuGUI();
	}
}

