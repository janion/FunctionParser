package fn.operator.singlearg;

import java.util.List;

import fn.Function;

public class NullOperation extends SingleArgumentOperator {

	public NullOperation(Function function) {
		super(function);
	}

	@Override
	public List<Double> evaluate() {
		return var1.evaluate();
	}

}
