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
		drawMenu();
		drawPanel();
		setVisible(true);
	}
	
	public void drawMenu () {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		JMenuItem itemNew = new JMenuItem("New");
		itemNew.setSelected(true);
		itemNew.setMnemonic(KeyEvent.VK_N);
		itemNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				generateBoard();
			}
		});
		menu.add(itemNew);
		JMenuItem itemCheck = new JMenuItem("Check");
		itemCheck.setMnemonic(KeyEvent.VK_C);
		itemCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				checkBoard();
			}
		});
		menu.add(itemCheck);		
		JMenuItem itemSolve = new JMenuItem("Solve");
		itemSolve.setMnemonic(KeyEvent.VK_S);
		itemSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				solveBoard();
			}
		});
		menu.add(itemSolve);
		menu.addSeparator();
		JMenuItem itemExit = new JMenuItem("Exit");
		itemExit.setMnemonic(KeyEvent.VK_E);
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				dispose();
			}
		});
		menu.add(itemExit);	
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
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
		this.grid.getSudoku().solveBoard();
		this.grid.refreshGrid();
	}
	
	/**
	* Check board. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  - 
	*/
	private void checkBoard() {
		if (this.grid.getSudoku().isSolved()) {
			JOptionPane.showMessageDialog(null, "All correct! Congratulations!");
		} else {
			this.grid.highlightCells(this.grid.getSudoku().checkBoard());
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
		this.grid.setSudoku(new Sudoku());
	}
	
	public static void main (String[] args) {
		new SudokuGUI();
	}
}
