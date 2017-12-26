package fn;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fn.exception.NoVariableException;
import fn.operator.Operator;

public class Function {
	
	private Operator operation;
	private Map<String, Double> variableValues;

	public List<Double> evaluate() {
		return evaluate(new HashMap<>());
	}

	public List<Double> evaluate(String[] variables, double[] values) {
		return evaluate(createVariables(variables, values));
	}

	public List<Double> evaluate(Map<String, Double> variableValues) {
		this.variableValues = new HashMap<>(variableValues);
        
        if (operation != null) {
        	return operation.evaluate();
        } else {
        	return Collections.emptyList();
        }
	}

	public void setOperator(Operator operation) {
		this.operation = operation;
	}

	public double getVariableValue(String variableName) {
		if (!variableValues.containsKey(variableName)) {
			throw new NoVariableException(variableName);
		}
		return variableValues.get(variableName);
	}
	
	private Map<String, Double> createVariables(String[] variables, double[] values) {
		if (variables.length != values.length) {
			throw new IllegalStateException("number of values does not match number of variables");
		}
		
		Map<String, Double> variableValues = new HashMap<>();
		for (int i = 0; i < variables.length; i++) {
			variableValues.put(variables[i], values[i]);
		}
		
		return variableValues;
	}

}
