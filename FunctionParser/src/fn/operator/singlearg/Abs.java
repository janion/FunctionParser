package fn.operator.singlearg;

import java.util.ArrayList;
import java.util.List;

import fn.Function;

public class Abs extends SingleArgumentOperator {

	public Abs(Function function) {
		super(function);
	}

	@Override
	public List<Double> evaluate() {
		List<Double> eval = new ArrayList<>();
		for (double value : var1.evaluate()) {
			eval.add(Math.abs(value));
		}
		return eval;
	}

}
