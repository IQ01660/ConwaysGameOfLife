// =============================================================================
/**
 * A <code>Cell</code> keeps track of its own liveness.  It also can determine
 * its own evolution by examining its neighbors and applying its survival rules.
 **/
// =============================================================================



// =============================================================================
public class Cell {
// =============================================================================

    

    // =========================================================================
    /**
     * The specialized constructor.  Create a new, initially-dead cell.
     *
     * @param grid The <code>Grid</code> that contains this cell.
     * @param row The row coordinate of this cell within its <code>Grid</code>.
     * @param column The column coordinate of this cell within its
     *               <code>Grid</code>.
     **/
    public Cell (Grid grid, int row, int column) {

	// Set the initial state to be dead.
	_isAlive = false;
	_wasEverAlive = false;

	// Store the grid and the coordinates within that grid.
	_grid = grid;
	_row = row;
	_column = column;

    } // Cell()
    // =========================================================================



    // =========================================================================
    /**
     * Indicate whether this cell is currently dead or alive.
     *
     * @return <code>true</code> if the cell is alive; <code>false</code> if it
     *         is dead.
     **/
    public boolean isAlive () {

	return _isAlive;

    } // isAlive()
    // =========================================================================



    // =========================================================================
    /**
     * Set the cell to be alive.
     **/
    public void makeAlive () {

	_isAlive = true;
	_wasEverAlive=true;
	
    } // makeAlive ()
    // =========================================================================



    // =========================================================================
    /**
     * Set the cell to be dead.
     **/
    public void makeDead () {

	_isAlive = false;
	
    } // makeDead ()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the row coordinate of this cell in its <code>Grid</code>.
     *
     * @return The row coordinate of this cell.
     **/
    public int getRow () {

	return _row;

    } // getRow ()
    // =========================================================================




    // =========================================================================
    /**
     * Provide the column coordinate of this cell in its <code>Grid</code>.
     *
     * @return The column coordinate of this cell.
     **/
    public int getColumn () {

	return _column;

    } // getColumn ()
    // =========================================================================



    // =========================================================================
    /**
     * Provide a textual representation of the cell's state.
     *
     * @return One particular character to represent liveness, another to
     *         represent deadness.  The characters chosen depend on the type of
     *         cell, and thus are determined by the subclasses.
     **/
    public String toString () {

	if (_isAlive) {
	    return "+";
	} else {
	    return "-";
	}
	
    }
    // =========================================================================



    // =========================================================================
    /**
     * Traverse the standard neighborhood (the surrounding 8 <code>Cell</code>s
     * in the <code>Grid</code>) and count the number of neighbors that are
     * alive.
     *
     * @return The number of live neighbors in the standard neighborhood.
     **/
    //change it to private!!!!!!
    private int countLiveNeighbors () {
    	int numOfNeighbors = 0;
        // WRITE ME.
        // iterating through the nine cells and skipping central one
    	// as well as the ones beyond matrix
    	for(int iteratingRow = this.getRow() - 1; iteratingRow < this.getRow() + 2; iteratingRow++) {
    		for (int iteratingCol = this.getColumn() - 1; iteratingCol < this.getColumn() + 2; iteratingCol++) {
    			if( iteratingRow < 0 || iteratingCol < 0 || iteratingRow >= this._grid.getRows() || iteratingCol >= this._grid.getColumns() || ( iteratingRow == this.getRow() && iteratingCol == this.getColumn() )) {
    				continue; // skip the unnecessary cells
    			}
    			else {
    				if ( this._grid.getCell(iteratingRow, iteratingCol).isAlive() ) {
    					numOfNeighbors++; //taking the alive cell into account
    				}
    			}
    		}
    	}
    	return numOfNeighbors;
    }
    // =========================================================================



    // =========================================================================
    /**
     * Based on its neighbors' states, calculate what this cell's state will be
     * in the <i>next</i> generation.  Here, the Conway rules are that <i>a live
     * cell with 2 or 3 live neighbors remains alive, a dead cell with 3 live
     * neighbors becomes alive, and all other cells will die</i>.
     **/
    public void evolve () 
    {

       // WRITE ME.
    	//making a dead cell with 3 neighbors alive
    	// System.out.println("cell's evolved");
    	if( this.isAlive() == false && this.countLiveNeighbors() == 3) 
    	{
    		this._willBeAlive = true;
    		this._willChange = true;
    	}
    	//all cells with less than 2 or more than 3 neighbors
    	else if (this.countLiveNeighbors() < 2 || this.countLiveNeighbors() > 3) 
    	{
    		this._willBeAlive = false;
    		if(this.isAlive()) {
        		this._willChange = true;
    		}
    		else {
    			this._willChange = false;
    		}
    	}
    	//the cells that have 2 or 3 neighbors and are alive
    	else {
			this._willChange = false;
    	}
    } // evolve ()
    // =========================================================================



    // =========================================================================
    /**
     * Advance to the next generation.  That is, adopt as the current
     * liveness whatever was calculated by <code>evolve()</code>.
     **/
    public void advance () {
    //	System.out.println("cell's advanced");
	// WRITE ME.
        if (this._willChange) {
        	if(this._willBeAlive == true) 
        	{
        		this.makeAlive();
        	}
        	else 
        	{
        		this.makeDead();
        	}
        	
        }
    }
    // =========================================================================



    // =========================================================================
    // DATA MEMBERS

    /**
     * The current liveness.
     **/
    boolean _isAlive;

    /**
     * The liveness in the next generation.
     **/
    public boolean _willBeAlive;
    public boolean _wasEverAlive;
    public boolean _willChange;

    /**
     * The <code>Grid</code> that contains this cell.
     **/
    Grid _grid;

    /**
     * The cell's row coordinate within its <code>Grid</code>.
     **/
    int _row;

    /**
     * The cell's column coordinate within its <code>Grid</code>.
     **/
    int _column;
    // =========================================================================



// =============================================================================
} // class Cell
// =============================================================================
