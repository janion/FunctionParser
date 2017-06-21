package fn;

import java.util.Collections;
import java.util.List;

import fn.operator.Operator;

public class Function {
	
	private Operator operation;
	private double x;
	private double y;
	private double t;

	public List<Double> evaluate(double x, double y, double t) {
		this.x = x;
		this.y = y;
		this.t = t;
        
        if (operation != null) {
        	return operation.evaluate();
        } else {
        	return Collections.emptyList();
        }
	}

	public List<Double> getX() {
		return Collections.singletonList(x);
	}

	public List<Double> getY() {
		return Collections.singletonList(y);
	}

	public List<Double> getT() {
		return Collections.singletonList(t);
	}

	public void setOperator(Operator operation) {
		this.operation = operation;
	}

}
