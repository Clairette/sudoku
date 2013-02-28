import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame; 
import javax.swing.JButton; 
import java.awt.GridLayout; 
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class SudokuGrid extends JFrame {
 
        JFrame frame=new JFrame(); //creates frame
        JLabel[][] grid; //names the grid of label
        
        
        public SudokuGrid(int width, int length){ //constructor
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
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400); //sets appropriate size for frame
                frame.setVisible(true); //makes frame visible
        }
        public static void main(String[] args) {
                new SudokuGrid(9,9);//makes new LabelGrid with 2 parameters
                
               
                
        }
}
              