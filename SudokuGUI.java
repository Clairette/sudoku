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

	private Board board;
	private JButton[][] buttons = new JButton[9][9];

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

//		Solver solver = new Solver(board);
//		solver.solveBoard();
//		board = solver.getBoard();
//
//		board.printBoard();
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
		setSize(900, 1000);
//	     	addWindowListener(new SudokuWindowListener());
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
		add(new Grids(), BorderLayout.CENTER);
		add(new ThreeButtons(), BorderLayout.SOUTH);
	}

	/**
	* Terminate application if window gets closed <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*/
//   	class SudokuWindowListener extends WindowAdapter {
//		public void windowClosing (WindowEvent e) {
//			e.getWindow().dispose();
//			System.exit(0);
//		}
//	}

	/**
	* Create a panel with the Generate, Check and Solve buttons. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  -
	*/
	class ThreeButtons extends JPanel {
		public ThreeButtons () {
			setLayout(new GridLayout(1, 3, 20, 20));
			add(new Button("Generate"));
			add(new Button("Check"));
			add(new Button("Solve"));
		}
	}

	/**
	* Paint the sudoku board and overlay the numbers. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  -
	*/
	class Grids extends JPanel {

		public Grids () {
			setBackground(Color.WHITE);
			setLayout(new GridLayout (9, 9));
			for (int i = 0; i < 9; i ++) {
				for (int j = 0; j < 9; j++) {
					Cell cell = board.getCell(i, j);
					if (cell.isFixed()) {
						JLabel num = new JLabel(cell.toString(), JLabel.CENTER);
						num.setFont(new Font("Arial", Font.BOLD, 20));
						num.setOpaque(false);
						add(num);
					} else {
						editableCell eCell = new editableCell(i, j);
						add(eCell);
					}
				}
			}
		}

		public void paintComponent (Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setBackground(Color.white);
			super.paintComponent(g2);
		
			int width = getSize().width;
			int height = getSize().height;

			for (int i = 1; i < 10; i++) {
				if (i % 3 == 0) {
					g2.setStroke(new BasicStroke(3));
					g2.drawLine(i * width/9, 0, i * width/9, height);
					g2.drawLine(0, i * height/9, width, i * height/9);
					g2.setStroke(new BasicStroke(1));
				} else {
					g2.drawLine(i * width/9, 0, i * width/9, height);
					g2.drawLine(0, i * height/9, width, i * height/9);
				}
			}
		}
	}

	/**
	* JTextField with row and column coordinates. <p>
	* 
	* @author 
	* Created by: Scott Cantisani <br>
	* Edited by:  -
	*/
	class editableCell extends JTextField {

		private int row, column;

		public editableCell (int row, int column) {
			this.row = row;
			this.column = column;
			setOpaque(false);
			setBorder(BorderFactory.createEmptyBorder());
			setFont(new Font("Arial", Font.PLAIN, 20));
			setHorizontalAlignment(JTextField.CENTER);
		}

		public int getRow () {
			return row;
		}
		
		public int getColumn () {
			return column;
		}
	}
	
	public static void main (String[] args) {
		new SudokuGUI();
	}
}

