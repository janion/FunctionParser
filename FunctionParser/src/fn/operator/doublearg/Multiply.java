package fn.operator.doublearg;

import java.util.ArrayList;
import java.util.List;

import fn.Function;

public class Multiply extends DoubleArgumentOperator {

	public Multiply(Function function) {
		super(function);
	}

	public List<Double> evaluate() {
		List<Double> answer = new ArrayList<>();
		List<Double> eval1 = var1.evaluate();
		List<Double> eval2 = var2.evaluate();

		for (double x : eval1) {
			for (double y : eval2) {
				answer.add(x * y);
			}
		}
		return answer;
	}

}
