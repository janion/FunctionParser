package fn;

import java.util.Arrays;
import java.util.List;

public class NegativeNumberAdjuster {

	private static final String OPEN_BRACKET = "(";
	private static final String CLOSE_BRACKET = ")";
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";
	private static final String MODULO = "%";
	private static final String POWER = "^";
	private static final List<String> OPERATIONS = Arrays.asList(PLUS, MINUS, MULTIPLY, DIVIDE, POWER, MODULO);

	private static final String PLACE_HOLDER = "|";
	private static final String SPACE = " ";
	private static final String ZERO = "0";

	public String adjust(String fnString) {
		fnString = fnString.replace(SPACE, "");
		while (fnString.contains(MINUS)) {
			for (int x = 0; x < fnString.length(); x++) {
				if (fnString.substring(x, x + 1).equals(MINUS)) {
					if (x == 0 || OPERATIONS.contains(fnString.substring(x - 1, x))
							|| fnString.substring(x - 1, x - 1 + 1).equals(OPEN_BRACKET)
							|| fnString.substring(x - 1, x - 1 + 1).equals(PLACE_HOLDER) || x == 0) {
						String followingOperand = findFollowingOperand(fnString, x + 1);
						fnString = (fnString.substring(0, x) + OPEN_BRACKET + ZERO + PLACE_HOLDER + followingOperand
								+ CLOSE_BRACKET + fnString.substring(x + 1 + followingOperand.length()));
						break;
					} else {
						fnString = (fnString.substring(0, x) + PLACE_HOLDER + fnString.substring(x + 1));
					}
				}
			}
		}
		return fnString.replace(PLACE_HOLDER, MINUS);
	}

	private String findFollowingOperand(String string, int pos) {
		int bracketCount = 0;
		for (int x = pos; x < string.length(); x++) {
			String charr = string.substring(x, x + 1);
			if (charr.equals(OPEN_BRACKET)) {
				bracketCount++;
			} else if (charr.equals(CLOSE_BRACKET)) {
				bracketCount--;
			}
			if (OPERATIONS.contains(charr) && bracketCount == 0) {
				return string.substring(pos, x);
			}
		}
		return string.substring(pos);
	}

}
