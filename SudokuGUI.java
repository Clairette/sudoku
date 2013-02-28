
//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class SudokuGUI extends Frame {

	private Sudoku sudoku = new Sudoku();
	private Panel panel = new Panel();
	
    
	public SudokuGUI () {
		initWindow();
	}

	public void initWindow () {
	    setTitle("Sudoku");
    	setSize(400,400);
    	addWindowListener(new SudokuWindowListener());
		drawPanel(9, 9);
		setVisible(true);
	}

	public void drawPanel (int width, int length){

		JFrame frame=new JFrame(); //creates frame
	    JLabel[][] grid; //names the grid of label
	    
					
		frame.setLayout(new GridLayout(width,length)); //set layout
        grid=new JLabel[width][length]; //allocate the size of grid
        
        	for(int i=0; i<length; i++){
                for(int j=0; j<width; j++){
                	
                
                        grid[i][j]=new JLabel(" 1"); //creates new label   
                        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
                        grid[i][j].setBorder(border);
                        frame.add(grid[i][j]); //adds label to grid
                }
        	}
		
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
