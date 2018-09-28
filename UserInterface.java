// =============================================================================
/**
 * The <code>UserInterface</code> for the <i>Game of Life</i>.  A
 * <code>UserInterface</code> controls the progression from one generation to
 * the next, displaying the state at each generation.
 **/
// =============================================================================



// =============================================================================
public class UserInterface {
// =============================================================================



    // =========================================================================
    /**
     * The constructor.  Hold onto a pointer to the <code>Game</code> for which
     * this <code>UserInterface</code> is providing interaction.
     *
     * @param game The <code>Game</code> whose state to draw.
     **/
    public UserInterface (Game game) {

	_game = game;

    } // UserInterface ()
    // =========================================================================



    // =========================================================================
    /**
     * Display the state of the <code>Cell</code>s in the <code>Grid</code>.
     **/
    public void display () {
    	
	// Provide generation and population counts.
	System.out.println("Generation = " + _game.getGeneration() +
			   ", Population = " + _game.getPopulation());

	// Provide a textual representation of the grid of cells themselves.
	for (int row = 0; row < _game.getRows(); row++) {
	    for (int column = 0; column < _game.getColumns(); column++) {

		Cell cell = _game.getCell(row, column);
		System.out.print(cell);

	    }

	    // Move to a new line for the next row.
	    System.out.println();

	}

	System.out.println();

    } // display ()
    // =========================================================================



    // =========================================================================
    /**
     * Keep control of the program until it is time to advance the state of the
     * <code>Game</code>.
     **/
    public void triggerMove () {

	// Do nothing.  Just let it advance as quickly as it wants.
	
    }
    // =========================================================================



    // =========================================================================
    // DATA MEMBERS

    /**
     * The <code>Game</code> that this interface is controlling.
     **/
    Game _game;
    // =========================================================================



// =============================================================================
} // class UserInterface
// =============================================================================
