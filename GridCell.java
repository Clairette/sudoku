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
* Creation date:	28.02.2013 <br> 
* Last updated:		08.03.2013 <br>
***********************************************/
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class GridCell extends JTextField  {

	private Grid grid;
	private int row;
	private int column;
	
	/**
	* Create new grid cell object. <p>
	* 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	*/	
	public GridCell (Grid grid, int row, int column, Cell cell) {
		this.grid = grid;
		this.row = row;
		this.column = column;
		this.drawCell(cell);
	}
	
	/**
	* Draw grid cell. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	* 
	* @param cell corresponding cell in board
	*/	
	private void drawCell (Cell cell) {
		if (!cell.isEmpty()) {
			this.setText(Integer.toString(cell.getNumber()));
		}
		this.setEditable(!cell.isFixed());
		if (cell.isFixed()) {
			this.setFont(new Font("Arial", Font.BOLD, 20));
		} else {
			this.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder());		
		this.setHorizontalAlignment(JTextField.CENTER);	
		this.getDocument().addDocumentListener(new CellDocumentListener());
	}
	
	/**
	* Highlight cell.
	*
	* @author
	* Created by:	Hans-Peter Hoellwirth <br>
	* Edited by:	-
	*
	* @param valid boolean
	*/
	public void highlightCell(boolean valid) {
		if (valid) {
			this.setForeground(Color.BLACK);
		} else {
			this.setForeground(Color.RED);
		}
	}

	/**
	* Assign customized document for appropriate cell behaviour. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	* 
	* @param cell corresponding cell in board
	*/
	protected Document createDefaultModel() {
		return new CellDocument();
	}

	/**
	* Customized cell document <p>
	* 
	* Only accept one numeric digit. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	*/
	private class CellDocument extends PlainDocument {

        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= 1) {
            	try {
            		Integer.parseInt(str);
            		super.insertString(offset, str, attr);
            	} catch (NumberFormatException e) {}
            }
        }       
    }	

	/**
	* Customized document listener <p>
	* 
	* Update board whenever the user changes the value of any cell. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth  <br>
	* Edited by:  -
	*/
	private class CellDocumentListener implements DocumentListener {
	    
		public void changedUpdate(DocumentEvent arg0) {
			this.updateCell();
		}
		
		public void insertUpdate(DocumentEvent arg0) {
			this.updateCell();
		}
		
		public void removeUpdate(DocumentEvent arg0) {
			this.updateCell();
		}
		
		private void updateCell() {
			highlightCell(true);
			try {
				grid.updateBoardCell(row, column, Integer.parseInt(getText()));
			}
			catch (NumberFormatException e) {
				grid.updateBoardCell(row, column, 0);
			}
		}
	}		
}

