package fn.operator.singlearg;

import java.util.ArrayList;
import java.util.List;

import fn.Function;

public class Tan extends SingleArgumentOperator {

	public Tan(Function function) {
		super(function);
	}

	@Override
	public List<Double> evaluate() {
		List<Double> eval = new ArrayList<>();
		for (double value : var1.evaluate()) {
			eval.add(Math.tan(value));
		}
		return eval;
	}

}
