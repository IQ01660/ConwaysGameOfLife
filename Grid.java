// =============================================================================
/**
 * The <code>Grid</code> class.  Manage a two-dimentional collection of
 * <code>Cell</code>s.
 **/
// =============================================================================



// =============================================================================
public class Grid {
// =============================================================================


    // =========================================================================
    /**
     * The constructor.  Create a grid of <code>Cell</code>s, in their default
     * initial states.
     *
     * @param rows The number of rows in the known region.
     * @param columns The number of columns in the known region.
     **/
    public Grid (int rows, int columns) {

	// Ensure that the sizes are valid.
	if ((rows <= 0) || (columns <= 0)) {
	    Support.abort("Cannot construct a grid of size " +
			  rows +
			  ", " +
			  columns);
	}

	// Create the underlying 2-D array that will track the Cells.
	_array = new Cell[rows][columns];

	// Create the Cells, all in their default states.
	for (int row = 0; row < rows; row += 1) {
	    for (int col = 0; col < columns; col += 1) {
		_array[row][col] = new Cell(this, row, col);
	    }
	}

    } // Grid()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the number of rows in the known region of the grid.
     *
     * @return The number of rows in the known region.
     **/
    public int getRows () {

	return _array.length;

    } // getRows ()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the number of columns in the known region of the grid.
     *
     * @return The number of rows in the known region.
     **/
    public int getColumns () {

	return _array[0].length;

    } // getColumns ()
    // =========================================================================



    // =========================================================================
    /**
     * Obtain the <code>Cell</code> at given coordinates.
     *
     * @param row The row of the requested <code>Cell</code>.
     * @param column The column of the requested <code>Cell</code>.
     * @return If the coordinates are within the grid, the <code>Cell</code> at
     *         that location; otherwise, <code>null</code>.
     **/ 
    public Cell getCell (int row, int column) {

	// Is this cell within the known region?
	if ((row >= 0) && (row < getRows()) &&
	    (column >= 0) && (column < getColumns())) {

	    // Yes, so return that cell.
	    return _array[row][column];

	} else {

	    // No.
	    return null;

	}

    } // getCell()
    // =========================================================================



    // =========================================================================
    // DATA MEMBERS

    /**
     * The actual 2-D collection of Cells.
     **/
    private Cell[][] _array;

    /**
     * Whether to provide debugging information.
     **/
    private final static boolean _debug = false;
    // =========================================================================
    


// =============================================================================
} // class Grid
// =============================================================================
