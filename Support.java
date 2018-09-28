// =============================================================================
/**
 * The <code>Support</code> class provides a number of static methods that make
 * certain tasks easier.  It also encapsulates and handles problems that are not
 * particularly important to the current assignment.
 *
 * @author Scott F. Kaplan -- sfkaplan@cs.amherst.edu
 **/
// =============================================================================



// =============================================================================
// IMPORTS

// =============================================================================



// =============================================================================
public class Support {
// =============================================================================



    // =========================================================================
    /**
     * Print an error message and abort processing.
     *
     * @param The error message to display on <code>stderr</code>.
     **/
    public static void abort (String message) {

	System.err.println(message);
	System.exit(1);

    } // abort ()
    // =========================================================================



// =============================================================================
} // class Support
// =============================================================================
