// =============================================================================
/**
 * The <code>Life</code> class.  This class is the entry point -- that is, it is
 * the class that is invoked on the command line to begin the creation of
 * objects used to play the game.
 *
 * @author Scott F. Kaplan -- sfkaplan@cs.amherst.edu
 **/
// =============================================================================



// =============================================================================
public class Life {
// =============================================================================


    
    // =========================================================================
    /**
     * The program's entry point.
     *
     * @param args Command line arguments containing the pathname to the initial
     *             state of the universe and the number of generations to
     *             compute.
     **/
    public static void main (String[] args) {

	if (args.length != 3) {
		showUsageAndExit();
	}
	else {
		String initialStatePathname = args[0];

		int generations=0;
		boolean graphics=true;
		try {
			generations = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			showUsageAndExit();
		}

		if (args[2].equals("nographics"))
			graphics=false;

		// Create and play the game, evolving one generation at a time.
		Game game = new Game(initialStatePathname,generations,graphics);
		game.play();
		
	}


    } // main ()
    // =========================================================================



    // =========================================================================
    /**
     * Print the correct command-line usage and then exit.
     **/
    protected static void showUsageAndExit () {

	Support.abort("USAGE: java Life <initial state pathname>\n" +
		      "                 <number of generations to compute>\n"+
			"				<\"graphics\" or \"nographics\">");
	
    }
    // =========================================================================



// =============================================================================
} // class Life
// =============================================================================
