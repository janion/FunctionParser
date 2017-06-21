package fn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderOfOperations {

	private static final String ESCAPE_CHAR = "\\";

	private static final String OPEN_BRACKET = "(";
	private static final String CLOSE_BRACKET = ")";

	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";
	private static final String MODULO = "%";
	private static final String POWER = "^";
	private static final List<String> OPERATIONS = Arrays.asList(PLUS, MINUS, MULTIPLY, DIVIDE, POWER, MODULO);

	private static final String PLACE_HOLDER1 = "|";
	private static final String PLACE_HOLDER2 = "&";
	private static final String SPACE = " ";

	public String addBrackets(String fnString) {
		String copy = fnString.replace(SPACE, "");

		// Invoke order of operations
		copy = prioritiseOperationRightToLeft(copy, POWER);
		copy = prioritiseOperationsLeftToRight(copy, MULTIPLY, DIVIDE);
		copy = prioritiseOperationsLeftToRight(copy, PLUS, MINUS);
		copy = prioritiseOperationsLeftToRight(copy, MODULO);

		return copy;
	}

	private String prioritiseOperationRightToLeft(String string, String operationChar) {
		while (string.contains(operationChar)
				&& needsMoreBracketsForOperation(string, Arrays.asList(operationChar), Arrays.asList(PLACE_HOLDER1))) {
			for (int x = string.length() - 1; x >= 0; x--) {
				if (string.substring(x, x + 1).equals(operationChar)) {
					int start = getContainingBracketStart(string, x - 1);
					int end = getContainingBracketEnd(string, x + 1);

					String firstOperand = OPEN_BRACKET + string.substring(start, x);
					String secondOperand = string.substring(x + 1, end) + CLOSE_BRACKET;

					string = (string.substring(0, start) + firstOperand + PLACE_HOLDER1 + secondOperand
							+ string.substring(end));
					break;
				}
			}
		}
		return string.replace(PLACE_HOLDER1, operationChar);
	}

	private String prioritiseOperationsLeftToRight(String string, String operationChar1, String operationChar2) {
		while ((string.contains(operationChar1) || string.contains(operationChar2)) && needsMoreBracketsForOperation(
				string, Arrays.asList(operationChar1, operationChar2), Arrays.asList(PLACE_HOLDER1, PLACE_HOLDER2))) {
			for (int x = 0; x < string.length(); x++) {
				String charr = string.substring(x, x + 1);
				if (charr.equals(operationChar1)) {
					string = bracketiseString(string, x, PLACE_HOLDER1);
					break;
				} else if (charr.equals(operationChar2)) {
					string = bracketiseString(string, x, PLACE_HOLDER2);
					break;
				}
			}
		}
		return string.replace(PLACE_HOLDER1, operationChar1).replace(PLACE_HOLDER2, operationChar2);
	}

	private String prioritiseOperationsLeftToRight(String string, String operationChar1) {
		while ((string.contains(operationChar1))
				&& needsMoreBracketsForOperation(string, Arrays.asList(operationChar1), Arrays.asList(PLACE_HOLDER1))) {
			for (int x = 0; x < string.length(); x++) {
				String charr = string.substring(x, x + 1);
				if (charr.equals(operationChar1)) {
					string = bracketiseString(string, x, PLACE_HOLDER1);
					break;
				}
			}
		}
		return string.replace(PLACE_HOLDER1, operationChar1);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private String bracketiseString(String string, int pos, String placeHolderChar) {
		int start = getContainingBracketStart(string, pos - 1);
		int end = getContainingBracketEnd(string, pos + 1);

		String firstOperand = string.substring(start, pos);
		String secondOperand = string.substring(pos + 1, end);

		if (!string.substring(start, start + 1).equals(OPEN_BRACKET)
				|| (end < string.length() && !string.substring(end, end + 1).equals(CLOSE_BRACKET))
						&& !(start == 0 && end == string.length())) {
			firstOperand = OPEN_BRACKET + firstOperand;
			secondOperand = secondOperand + CLOSE_BRACKET;
		}

		return string.substring(0, start) + firstOperand + placeHolderChar + secondOperand + string.substring(end);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private int getContainingBracketStart(String string, int pos) {
		int bracketCount = 0;
		for (int x = pos; x >= 0; x--) {
			String charr = string.substring(x, x + 1);
			if (charr.equals(OPEN_BRACKET)) {
				bracketCount++;
			} else if (charr.equals(CLOSE_BRACKET)) {
				bracketCount--;
			}
			if (OPERATIONS.contains(charr) && bracketCount == 0) {
				return x + 1;
			} else if ((bracketCount == 1)) {
				return x;
			}
		}
		return 0;
	}

	private int getContainingBracketEnd(String string, int pos) {
		int bracketCount = 0;
		for (int x = pos; x < string.length(); x++) {
			String charr = string.substring(x, x + 1);
			if (charr.equals(OPEN_BRACKET)) {
				bracketCount++;
			} else if (charr.equals(CLOSE_BRACKET)) {
				bracketCount--;
			}

			if (OPERATIONS.contains(charr) && bracketCount == 0) {
				return x;
			} else if ((bracketCount == -1)) {
				return x;
			}
		}
		return string.length();
	}

	private boolean needsMoreBracketsForOperation(String string, List<String> operations, List<String> placeHolders) {
		int bracketCount = 0;
		int openBracketCount = string.split(ESCAPE_CHAR + OPEN_BRACKET).length + 1;
		List<Boolean> hasSeenAnyOperation = new ArrayList<>();
		for (int x = 0; x < openBracketCount; x++) {
			hasSeenAnyOperation.add(false);
		}
		List<Boolean> hasSeenTheseOperations = new ArrayList<>(hasSeenAnyOperation);

		for (int i = 0; i < string.length(); i++) {
			String charr = string.substring(i, i + 1);
			if (charr.equals(OPEN_BRACKET)) {
				bracketCount++;
			} else if (charr.equals(CLOSE_BRACKET)) {
				hasSeenAnyOperation.set(bracketCount, false);
				hasSeenTheseOperations.set(bracketCount, false);
				bracketCount--;
			} else if (operations.contains(charr) || placeHolders.contains(charr)) {
				if (hasSeenTheseOperations.get(bracketCount)) {
					return true;
				}
				hasSeenTheseOperations.set(bracketCount, true);
			} else if (OPERATIONS.contains(charr)) {
				if (hasSeenAnyOperation.get(bracketCount)) {
					return true;
				}
				hasSeenAnyOperation.set(bracketCount, true);
			}

			if (hasSeenTheseOperations.get(bracketCount) && hasSeenAnyOperation.get(bracketCount)) {
				return true;
			}
		}
		return false;
	}

}
