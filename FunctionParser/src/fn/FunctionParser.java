package fn;

import fn.operator.Operator;
import fn.operator.doublearg.Add;
import fn.operator.doublearg.Divide;
import fn.operator.doublearg.DoubleArgumentOperator;
import fn.operator.doublearg.Minus;
import fn.operator.doublearg.Modulo;
import fn.operator.doublearg.Multiply;
import fn.operator.doublearg.Power;
import fn.operator.singlearg.Abs;
import fn.operator.singlearg.Constant;
import fn.operator.singlearg.Cos;
import fn.operator.singlearg.Exp;
import fn.operator.singlearg.Log;
import fn.operator.singlearg.NullOperation;
import fn.operator.singlearg.Sin;
import fn.operator.singlearg.SingleArgumentOperator;
import fn.operator.singlearg.Sqrt;
import fn.operator.singlearg.T;
import fn.operator.singlearg.Tan;
import fn.operator.singlearg.X;
import fn.operator.singlearg.Y;

public class FunctionParser {

	private static final String NUMERALS = ".0123456789";

	private static final String SIN = "sin";
	private static final String COS = "cos";
	private static final String TAN = "tan";
	private static final String SQRT = "sqrt";
	private static final String EXP = "exp";
	private static final String LOG = "log";
	private static final String ABS = "abs";

	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";
	private static final String MODULO = "%";
	private static final String POWER = "^";
	private static final String OPERATIONS = PLUS + MINUS + MULTIPLY + DIVIDE + MODULO + POWER;

	private static final String X = "x";
	private static final String Y = "y";
	private static final String T = "t";
	private static final String VARIABLES = X + Y + T;

	private static final String SPACE = " ";
	private static final String OPEN_BRACKET = "(";
	private static final String CLOSE_BRACKET = ")";
    
    public Function parseFunction(String string) {
    	OrderOfOperations orderer = new OrderOfOperations();
        String orderedString = orderer.addBrackets(string);
        NegativeNumberAdjuster adjuster = new NegativeNumberAdjuster();
        String adjustedString = adjuster.adjust(orderedString);
        Function function = new Function();
        function.setOperator(parseEquation(adjustedString, function));
        return function;
    }

    
    private SingleArgumentOperator makeVariable(String charr, Function function) {
        if (charr.equals(X)) {
            return new X(function);
        } else if (charr.equals(Y)) {
            return new Y(function);
        } else if (charr.equals(T)) {
        	return new T(function);
        }
        return null;
    }
        

    
    private DoubleArgumentOperator makeOperator(String charr, Function function) {
        if (charr.equals(PLUS)) {
            return new Add(function);
        } else if (charr.equals(MINUS)) {
            return new Minus(function);
        } else if (charr.equals(MULTIPLY)) {
            return new Multiply(function);
        } else if (charr.equals(DIVIDE)) {
            return new Divide(function);
        } else if (charr.equals(MODULO)) {
            return new Modulo(function);
        } else if (charr.equals(POWER)) {
            return new Power(function);
        }
        return null;
    }
    
    private SingleArgumentOperator parseEquation(String string, Function function) {
        int pos = 0;
        Operator v1 = null;
		Operator v2 = null;
		SingleArgumentOperator operation = new NullOperation(function);
		
        while (pos < string.length()) {
            String charr = string.substring(pos, pos + 1);
            SingleArgumentOperator variable = null;
            
            if (OPERATIONS.contains(charr)) {
                operation = makeOperator(charr, function);
                pos++;
            } else if (charr.equals(OPEN_BRACKET)) {
                String substr = getBracketContents(string.substring(pos, string.length()));
                variable = parseEquation(substr, function);
                pos += substr.length() + 2;
            } else if (string.length() >= pos + 3 && string.substring(pos , pos + 3).equals(SIN)) {
                variable = new Sin(function);
                String arg = getBracketContents(string.substring(pos + 3 , string.length()));
                variable.setVar1(parseEquation(arg, function));
                pos += 3 + arg.length() + 2;
            } else if (string.length() >= pos + 3 && string.substring(pos , pos + 3).equals(COS)) {
                variable = new Cos(function);
        		String arg = getBracketContents(string.substring(pos + 3 , string.length()));
                variable.setVar1(parseEquation(arg, function));
                pos += 3 + arg.length() + 2;
            } else if (string.length() >= pos + 3 && string.substring(pos , pos + 3).equals(TAN)) {
                variable = new Tan(function);
        		String arg = getBracketContents(string.substring(pos + 3 , string.length()));
                variable.setVar1(parseEquation(arg, function));
                pos += 3 + arg.length() + 2;
            } else if (string.length() >= pos + 3 && string.substring(pos , pos + 3).equals(EXP)) {
                variable = new Exp(function);
        		String arg = getBracketContents(string.substring(pos + 3 , string.length()));
                variable.setVar1(parseEquation(arg, function));
                pos += 3 + arg.length() + 2;
            } else if (string.length() >= pos + 3 && string.substring(pos , pos + 3).equals(LOG)) {
                variable = new Log(function);
        		String arg = getBracketContents(string.substring(pos + 3 , string.length()));
                variable.setVar1(parseEquation(arg, function));
                pos += 3 + arg.length() + 2;
            } else if (string.length() >= pos + 3 && string.substring(pos , pos + 3).equals(ABS)) {
                variable = new Abs(function);
        		String arg = getBracketContents(string.substring(pos + 3 , string.length()));
                variable.setVar1(parseEquation(arg, function));
                pos += 3 + arg.length() + 2;
            } else if (string.length() >= pos + 4 && string.substring(pos , pos + 4).equals(SQRT)) {
                variable = new Sqrt(function);
        		String arg = getBracketContents(string.substring(pos + 4 , string.length()));
                variable.setVar1(parseEquation(arg, function));
                pos += 4 + arg.length() + 2;
            } else if (NUMERALS.contains(charr)) {
                String numStr = parseNumber(string.substring(pos , string.length()));
                variable = new Constant(Double.valueOf(numStr).doubleValue());
                pos += numStr.length();
            } else if (VARIABLES.contains(charr)) {
                variable = makeVariable(charr, function);
                pos++;
            } else if (charr.equals(SPACE)) {
                pos++;
            } else {
                throw new RuntimeException(String.format("Unrecognised character: \"%s\" in equation:\n\"%s\"", charr, string));
            }
            
            if (v1 == null && variable != null) {
                v1 = variable;
            } else if (v2 == null && variable != null) {
                v2 = variable;
            }
        }
        
        operation.setVar1(v1);
        if (operation instanceof DoubleArgumentOperator) {
            ((DoubleArgumentOperator) operation).setVar2(v2);
        }
        
        return operation;
    }
    
    private String parseNumber(String string) {
        String copy = "";
        for (int pos = 0; pos < string.length(); pos++) {
            String charr = string.substring(pos, pos + 1);
            if (NUMERALS.contains(charr)) {
                copy += charr;
            } else {
                break;
            }
        }
        return copy;
    }
            
    private String getBracketContents(String string) {
    	// Should always be "("
        String contents = string.substring(0, 1);
        int bracketCount = 1;
        
        int pos = 1;
        while (bracketCount > 0) {
            String charr = string.substring(pos, pos + 1);
            if (charr.equals(OPEN_BRACKET)) {
                bracketCount++;
            } else if (charr.equals(CLOSE_BRACKET)) {
                bracketCount--;
            }
            contents += charr;
            pos++;
        }
        return contents.substring(1, contents.length() - 1);
    }

}
