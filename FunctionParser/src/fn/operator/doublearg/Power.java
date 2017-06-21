package fn.operator.doublearg;

import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.List;

import fn.Function;

public class Power extends DoubleArgumentOperator {

	public Power(Function function) {
		super(function);
	}

	public List<Double> evaluate() {
		List<Double> answer = new ArrayList<>();
		List<Double> eval1 = var1.evaluate();
		List<Double> eval2 = var2.evaluate();

		for (double x : eval1) {
			for (double y : eval2) {
				answer.add(pow(x, y));
			}
		}
		return answer;
	}

}
