package fn.operator.singlearg;

import java.util.ArrayList;
import java.util.List;

import fn.Function;

public class Sin extends SingleArgumentOperator {

	public Sin(Function function) {
		super(function);
	}

	@Override
	public List<Double> evaluate() {
		List<Double> eval = new ArrayList<>();
		for (double value : var1.evaluate()) {
			eval.add(Math.sin(value));
		}
		return eval;
	}

}
