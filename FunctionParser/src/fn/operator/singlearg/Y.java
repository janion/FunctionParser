package fn.operator.singlearg;

import java.util.List;

import fn.Function;

public class Y extends SingleArgumentOperator {

	public Y(Function function) {
		super(function);
	}

	@Override
	public List<Double> evaluate() {
		return function.getY();
	}

}
