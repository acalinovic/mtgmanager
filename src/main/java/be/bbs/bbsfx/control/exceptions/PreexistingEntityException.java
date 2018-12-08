package be.bbs.bbsfx.control.exceptions;

public class PreexistingEntityException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3799003607898759908L;
	public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public PreexistingEntityException(String message) {
        super(message);
    }
}
