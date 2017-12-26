package fn.exception;

public class InvalidFunctionException extends RuntimeException {
	
	private static final long serialVersionUID = -5908905813657305396L;

	public InvalidFunctionException() {
		super("Invalid syntax for function");
	}

}
