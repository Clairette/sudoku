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
* Creation date:	07.03.2013 <br>
* Last updated:		14.03.2013 <br>
***********************************************/

public class Cell {

	private int number;
	private boolean fixed;

	/**
	* Create new cell object. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  Scott Cantisani (14.03.2013)
	*
	*/	
	public Cell () {
		fixed = false;
	}

	/**
	* Create new cell object. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @param number the number of the cell
	* @param fixed generated or variable cell
	*/	
	public Cell (int number, boolean fixed) {
		this.number = number;
		this.fixed  = fixed;
	}

	/**
	* Get the number of the cell. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @return the number of the cell
	*/
	public int getNumber () {
		return this.number;
	}

	/**
	* Set the number of the cell. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @param number the new number of the cell
	*/
	public void setNumber (int number) {
		this.number = number;
	}

	/**
	* Get whether the cell is already set (1-9). <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @return true if set, otherwise false
	*/
	public boolean isEmpty () {
		return this.number == 0;
	}

	/**
	* Get whether the cell is fixed or variable. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @return true if fixed, false if variable
	*/
	public boolean isFixed () {
		return this.fixed;
	}

	/**
	* Set the new fixed/variable status of the cell. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @param fixed true if fixed, false if variable
	*/
	public void setFixed (boolean fixed) {
		this.fixed = fixed;
	}

	/**
	* String output. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @return number of the cell
	*/
	public String toString () {
		return "" + this.number;
	}

	/**
	* Compare cell (only verifies number). <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @param c cell to compare
	* @return true if numbers are equal, otherwise false
	*/
	public boolean equals (Cell c) {
		return this.number == c.number;
	}

	/**
	* Compare cell with a number. <p>
	* 
	* @author 
	* Created by: Hans-Peter Hoellwirth <br>
	* Edited by:  -
	*
	* @param number number to compare
	* @return true if numbers are equal, otherwise false
	*/
	public boolean equals (int number) {
		return this.number == number;
	}
}
