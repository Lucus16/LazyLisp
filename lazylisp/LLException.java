package lazylisp;

/**
 * Represents an error within an ll program.
 * @author lars
 *
 */
public class LLException extends Exception {
	private static final long serialVersionUID = 4201472718505089536L;

	public LLException(String string) {
		super(string);
	}
}
