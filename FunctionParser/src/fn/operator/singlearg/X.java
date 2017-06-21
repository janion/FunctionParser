package fn.operator.singlearg;

import java.util.List;

import fn.Function;

public class X extends SingleArgumentOperator {

	public X(Function function) {
		super(function);
	}

	@Override
	public List<Double> evaluate() {
		return function.getX();
	}

}
