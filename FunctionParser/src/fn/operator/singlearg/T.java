package fn.operator.singlearg;

import java.util.List;

import fn.Function;

public class T extends SingleArgumentOperator {

	public T(Function function) {
		super(function);
	}

	@Override
	public List<Double> evaluate() {
		return function.getT();
	}

}
