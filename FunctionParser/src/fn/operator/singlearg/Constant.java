package fn.operator.singlearg;

import java.util.Collections;
import java.util.List;

public class Constant extends SingleArgumentOperator {
	
	private double value;

	public Constant(double value) {
		super(null);
		this.value = value;
	}

	@Override
	public List<Double> evaluate() {
		return Collections.singletonList(value);
	}

}
