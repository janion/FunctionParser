package fn.exception;

public class NoVariableException extends RuntimeException {

	private static final long serialVersionUID = -135178990867016454L;
	
	private String variableName;
	
	public NoVariableException(String variableName) {
		super(String.format("No value for variable %s", variableName));
		this.variableName = variableName;
	}
	
	public String getVariableName() {
		return variableName;
	}

}
