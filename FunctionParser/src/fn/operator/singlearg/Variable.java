package fn.operator.singlearg;

import java.util.Collections;
import java.util.List;

import fn.Function;

public class Variable extends SingleArgumentOperator {
	
	private String variableName;

	public Variable(Function function, String variableName) {
		super(function);
		this.variableName = variableName;
	}

	@Override
	public List<Double> evaluate() {
		return Collections.singletonList(function.getVariableValue(variableName));
	}

}
