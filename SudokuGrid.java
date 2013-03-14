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
* Creation date:	06.02.2013
* Last updated:		28.02.2013
***********************************************/


import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Frame;

public class SudokuGrid {
		
		Frame frame = new Frame();
		Label[][] grid; 
		
			
		public SudokuGrid (int width, int length){
			
			
			frame.setLayout(new GridLayout(width, length));
			grid=new Label[width][length];
			
				for(int i=0; i<length; i++){
				
					for(int j=0; j<width; j++){
					
						grid[i][j] = new Label ("("+i+","+j+")");
						frame.add(grid[i][j]);
					}
				}
		
			frame.pack();
			frame.setVisible(true);
			
		}
		
		public static void main(String[] args){
			
			new SudokuGrid(9,9);
			
		}
}
	
/**
* Creates a simple frame
* 
* @author 
* Created by: Claire Giry 
* Edited by:  -
*/	
