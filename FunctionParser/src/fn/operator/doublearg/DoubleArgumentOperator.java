package fn.operator.doublearg;

import fn.Function;
import fn.operator.Operator;
import fn.operator.singlearg.SingleArgumentOperator;

public abstract class DoubleArgumentOperator extends SingleArgumentOperator {

	protected Operator var2;

	protected DoubleArgumentOperator(Function function) {
		super(function);
	}

	public void setVar2(Operator var2) {
		this.var2 = var2;
	}

}
