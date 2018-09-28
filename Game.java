// =============================================================================
/**
 * The <code>Game</code> class.  Manage a single <i>Game of Life</i> by loading
 * the initial grid of cells and evolving them as requested.
 *
 * @author Scott F. Kaplan -- sfkaplan@cs.amherst.edu
 **/
// =============================================================================



// =============================================================================
// IMPORTS

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
// =============================================================================



// =============================================================================
public class Game{
// =============================================================================



    // =========================================================================
    // DATA MEMBERS

    /**
     * The grid itself.
     **/
    private Grid _grid;

    /**
     * The current generation number.
     **/
    private int _generation;

    /**
     * Number of generations to play
     **/
    private int maxGenerations;

    /**
     * Window size of visualization
     **/
    public int WIDTH=1440;
    public int HEIGHT=900;

    /**
     * The size of one cell in pixels for visualization.
     * smaller size corresponds to larger grid
     **/
    public int boxsize=10;

    /**
     * Wait time between frames for visualization.
     **/
    public int wait = 100;

    /**
     * Number of cells the grid extends by offscreen.
     **/
	public int offscreenMargins=10;

    /**
     * Boolean flag for whether visualization is needed.
     **/
	public boolean showGraphics;

    /**
     * The <code>UserInterface</code> that controls and displays the game.
     **/
	private UserInterface _userInterface;

    /**
     * The <code>VisualInterface</code> that displays the game.
     **/
    private VisualInterface _visualInterface;
    // =========================================================================



    // =========================================================================
    /**
     * The constructor.  Read the initial state of a game from a provided
     * pathname, creating the <code>Grid</code> and the specified live
     * <code>Cell</code>s.
     *
     * @param initialStatePath The file from which the initial state of the
     *                         universe is taken.
     **/
    public Game (String initialStatePath,int maxGenerationsArg,boolean showGraphicsArg) {
		this.showGraphics   = showGraphicsArg;
		this.maxGenerations = maxGenerationsArg;
		_userInterface      = new UserInterface(this);

        // Read the initial state, creating a grid of cells as specified.
        readInitialState(initialStatePath);

		// Create visual interface if needed.
        if (showGraphics) {
            _visualInterface = new VisualInterface(this);
        }

        // Start counting at generation 0.
        _generation = 0;

    }// Game()
    // =========================================================================



    // =========================================================================
    /**
     * Read the initial state file, creating a <code>Grid</code> and
     * initializing it with <code>Cell</code>s as specified by that file.
     *
     * @param initialStatePath The file from which the initial state of the
     *                         universe is taken.
     **/
    private void readInitialState (String initialStatePath) {

        // Create the reader for this file.
        Scanner reader = null;
        try {
            reader = new Scanner(new File(initialStatePath));
        } catch (FileNotFoundException e) {
            Support.abort("ERROR: File not found: " + initialStatePath);
        }

        int rows    = -1;
        int columns = -1;

        // Dimensions to be used with graphics mode
        int graphicsRows = HEIGHT/(boxsize);
        int graphicsColumns = WIDTH/(boxsize);

        // Read the first line, which contains the dimensions of the grid for nographics mode.
        try {
            rows    = reader.nextInt();
            columns = reader.nextInt();
        } catch (InputMismatchException e) {
            Support.abort("ERROR: Invalid dimensions at line 1");
        }


        // Create a Grid with these dimensions
        // For graphics mode the grid size depends on window size and not on dimensions specified in first line of init file
        if(showGraphics) {
            _grid = new Grid(graphicsRows+offscreenMargins, graphicsColumns+offscreenMargins);
        }
        else {
            _grid = new Grid(rows, columns);
        }

        // Read coordinates for initially live cells until the end-of-file is
        // reached.
        int lineNumber = 2;
        while (true) {

            int row = -1;
            int col = -1;
            try {
            row = reader.nextInt();
            col = reader.nextInt();
            } catch (InputMismatchException e) {
            Support.abort("ERROR: From initial state file, " +
                      "could not read coordinates at line " +
                      lineNumber);
            } catch (NoSuchElementException e) {
            break;
            }

            System.out.printf("DEBUG: Setting initially live cell at " + row + ", " + col + "\n");

            // Set the cells to be alive at the center of the screen for graphics mode
            if (showGraphics) {
                _grid.getCell((graphicsRows + offscreenMargins - rows) / 2 + row, (graphicsColumns + offscreenMargins - columns) / 2 + col).makeAlive();
            }

            // Set the cell to be alive at coordinates specified by init file for nographics mode
            else{
                _grid.getCell(row,col).makeAlive();
            }
            lineNumber += 1;
        }
    }// readInitialState ()
    // =========================================================================



    // =========================================================================
    /**
     * Evolve a game of Life through its generations, emitting the state of the
     * game at each generation.  Evolution will continue until the universe
     * becomes static or until the given maximum number of generations is
     * reached.
     *
     * @param generations The number of generations to evolve.
     **/

	public void play () {
		
		while(_generation < maxGenerations)
		{
			evolve();
			//System.out.println("evolved");
			// Wait before showing the next frame of visualization.
			try{
				Thread.sleep(wait);
			}
			catch(InterruptedException e){}

            // If in graphics mode, draw the current stage.
			if(showGraphics)_visualInterface.repaint();

			// Display the current stage.
			_userInterface.display();

			// Advance to the next stage.
			
			advance();
			//System.out.println("advanced");
            // Increment the generation counter
            _generation+=1;
		} // evolution loop
	} // play ()
    // =========================================================================



    // =========================================================================
    /**
     * Calculate the state of the game's universe from the current generation to
     * the next.
     **/
    public void evolve () {
		
        //WRITE ME
    	for(int iteratingRow = 0; iteratingRow < getRows(); iteratingRow++) {
    		for (int iteratingCol = 0; iteratingCol < getColumns(); iteratingCol++) {
    			this.getCell(iteratingRow, iteratingCol).evolve();
    		}
    	}
	} // evolve ()
    // =========================================================================



    // =========================================================================
    /**
     * Advance the state of the game's universe to the state generated in evolve()
     **/
	public void advance(){

		//WRITE ME
		for(int iteratingRow = 0; iteratingRow < getRows(); iteratingRow++) {
    		for (int iteratingCol = 0; iteratingCol < getColumns(); iteratingCol++) {
    			this.getCell(iteratingRow, iteratingCol).advance();
    		}
    	}
	} // advance ()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the current generation number.
     *
     * @return The current generation number for this universe.
     **/
    public int getGeneration () {

	return _generation;

    }
    // =========================================================================



    // =========================================================================
    /**
     * Provide the number of live cells.
     *
     * @return The number of live cells in the current universe.
     **/
    public int getPopulation () {
        
        //WRITE ME
    	int population = 0;
    	for(int iteratingRow = 0; iteratingRow < getRows(); iteratingRow++) {
    		for (int iteratingCol = 0; iteratingCol < getColumns(); iteratingCol++) {
    			if (getCell(iteratingRow, iteratingCol).isAlive() == true) {
    				population++;
    			}
    		}
    	}
    	return population;
    } // getPopulation()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the number of rows in the universe.
     *
     * @return The number of rows in the universe.
     **/
    public int getRows () {

	return _grid.getRows();

    } // getRows()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the number of columns in the universe.
     *
     * @return The number of columns in the universe.
     **/
    public int getColumns () {

	return _grid.getColumns();

    } // getColumns()
    // =========================================================================

    // =========================================================================
    /**
     * Provide direct access to a <code>Cell</code> from this universe.
     *
     * @param row The row coordinate of the <code>Cell</code>.
     * @param column The column coordinate of the <code>Cell</code>.
     **/
    public Cell getCell (int row, int column) {

	return _grid.getCell(row, column);

    } // getCell()
    // =========================================================================


// =============================================================================
} // class Game
// =============================================================================