package hei.devweb.wejog.exceptions;

public class WejogAccessException extends Exception {

	private static final long serialVersionUID = 7348054554643258527L;

	public WejogAccessException() {
		super();
	}

	public WejogAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WejogAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public WejogAccessException(String message) {
		super(message);
	}

	public WejogAccessException(Throwable cause) {
		super(cause);
	}

}
