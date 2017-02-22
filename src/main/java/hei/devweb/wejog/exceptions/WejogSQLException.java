package hei.devweb.wejog.exceptions;

public class WejogSQLException extends RuntimeException {

	private static final long serialVersionUID = 8520858908301006744L;

	public WejogSQLException() {
		super();
	}

	public WejogSQLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WejogSQLException(String message, Throwable cause) {
		super(message, cause);
	}

	public WejogSQLException(String message) {
		super(message);
	}

	public WejogSQLException(Throwable cause) {
		super(cause);
	}

}
