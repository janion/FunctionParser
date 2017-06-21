package fn.operator.singlearg;

import java.util.ArrayList;
import java.util.List;

import fn.Function;

public class Log extends SingleArgumentOperator {

	public Log(Function function) {
		super(function);
	}

	@Override
	public List<Double> evaluate() {
		List<Double> eval = new ArrayList<>();
		for (double value : var1.evaluate()) {
			eval.add(Math.log(value));
		}
		return eval;
	}

}
