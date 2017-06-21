package fn.operator.singlearg;

import fn.Function;
import fn.operator.Operator;

public abstract class SingleArgumentOperator implements Operator {

	protected Function function;
	protected Operator var1;

	protected SingleArgumentOperator(Function function) {
		this.function = function;
	}

	public void setVar1(Operator var1) {
		this.var1 = var1;
	}

}
