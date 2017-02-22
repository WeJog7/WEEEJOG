package hei.devweb.wejog.exceptions;

public class WejogSecuriteException extends Exception {

	private static final long serialVersionUID = 8520858908301006744L;

	public WejogSecuriteException() {
		super();
	}

	public WejogSecuriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WejogSecuriteException(String message, Throwable cause) {
		super(message, cause);
	}

	public WejogSecuriteException(String message) {
		super(message);
	}

	public WejogSecuriteException(Throwable cause) {
		super(cause);
	}

}
