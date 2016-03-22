	/**
	 *  @Author Björn Ros
	 */

public class NotGonnaHappenException extends Exception {
	public PressQException NotGonnaHappenException;
	// Scanner only throws exceptions for .nextInt() and the like if the
	// input is malformed.
	private Exception originalException;

	public Exception getException() {
		return originalException;
	}

	public NotGonnaHappenException(String message, Exception e) {
		super(message);
		
		originalException = e;
	}

}