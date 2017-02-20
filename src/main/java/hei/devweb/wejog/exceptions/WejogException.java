package hei.devweb.wejog.exceptions;

public class WejogException extends Exception {

	private static final long serialVersionUID = 8520858908301006744L;

	public WejogException() {
		super();
	}

	public WejogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WejogException(String message, Throwable cause) {
		super(message, cause);
	}

	public WejogException(String message) {
		super(message);
	}

	public WejogException(Throwable cause) {
		super(cause);
	}

}
