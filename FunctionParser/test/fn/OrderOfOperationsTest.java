package fn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderOfOperationsTest {

	private OrderOfOperations o = new OrderOfOperations();

	@Test
	public void shouldOrderOperations1() {
		assertEquals("251", o.addBrackets("251"));
	}

	@Test
	public void shouldOrderOperations2() {
		assertEquals("25+1", o.addBrackets("25 + 1"));
	}

	@Test
	public void shouldOrderOperations3() {
		assertEquals("(25/((7+1)^9))*(sin(t)-4)", o.addBrackets("25 / (7 + 1) ^ 9 * (sin(t) - 4)"));
	}

	@Test
	public void shouldOrderOperations4() {
		assertEquals("((7*9)/7)*9", o.addBrackets("7 * 9 / 7 * 9"));
	}

	@Test
	public void shouldOrderOperations5() {
		assertEquals("((7/9)*7)/9", o.addBrackets("7 / 9 * 7 / 9"));
	}

	@Test
	public void shouldOrderOperations6() {
		assertEquals("2^(5^(6^4))", o.addBrackets("2 ^ 5 ^ 6 ^ 4"));
	}

	@Test
	public void shouldOrderOperations7() {
		assertEquals("(2^(5^(6^4)))+3", o.addBrackets("2 ^ 5 ^ 6 ^ 4 + 3"));
	}

	@Test
	public void shouldOrderOperations8() {
		assertEquals("(2+3)^(3*5)", o.addBrackets("(2 + 3) ^ (3 * 5)"));
	}

	@Test
	public void shouldOrderOperations9() {
		assertEquals("(((2+5)+6)+4)+3", o.addBrackets("2 + 5 + 6 + 4 + 3"));
	}

	@Test
	public void shouldOrderOperations10() {
		assertEquals("sin((2+5)+6)", o.addBrackets("sin(2 + 5 + 6)"));
	}

	@Test
	public void shouldOrderOperations11() {
		assertEquals("sin((2+5)+6)+4", o.addBrackets("sin(2 + 5 + 6) + 4"));
	}

	@Test
	public void shouldOrderOperations12() {
		assertEquals("(sin((2+5)+6)+4)+3", o.addBrackets("sin(2 + 5 + 6) + 4 + 3"));
	}

	@Test
	public void shouldOrderOperations13() {
		assertEquals("127*(sin((t+(x/5))+(6.243/1.5))+1)",
				o.addBrackets("127 * (sin(t + (x / 5) + (6.243 / 1.5)) + 1)"));
	}

	@Test
	public void shouldOrderOperations14() {
		assertEquals("sin((t+(x/5))+(6.243/1.5))", o.addBrackets("sin(t + (x / 5) + (6.243 / 1.5))"));
	}

	@Test
	public void shouldOrderOperations15() {
		assertEquals("sin(1^(2^3))", o.addBrackets("sin(1 ^ 2 ^ 3)"));
	}

	@Test
	public void shouldOrderOperations16() {
		assertEquals("sin(1^((2+1)^3))", o.addBrackets("sin(1 ^ (2 + 1) ^ 3)"));
	}

	@Test
	public void shouldOrderOperations17() {
		assertEquals("sin(1^((2+1)^(3*2)))", o.addBrackets("sin(1 ^ (2 + 1) ^ (3 * 2))"));
	}

	@Test
	public void shouldOrderOperations18() {
		assertEquals("127*(sin(t^((x/5)^(6.243/1.5)))^1)",
				o.addBrackets("127 * (sin(t ^ (x / 5) ^ (6.243 / 1.5)) ^ 1)"));
	}

	@Test
	public void shouldOrderOperations19() {
		assertEquals("(1+1)^((2+2)^(3+3))", o.addBrackets("(1+1) ^ (2+2) ^ (3+3)"));
	}

	@Test
	public void shouldOrderOperations20() {
		assertEquals("((1+1)^((2+2)^(3+3)))", o.addBrackets("((1+1) ^ (2+2) ^ (3+3))"));
	}

	@Test
	public void shouldOrderOperations21() {
		assertEquals("2+(sin(x)^y)", o.addBrackets("2 + (sin(x) ^ y)"));

	}

	@Test
	public void shouldOrderOperations22() {
		assertEquals("sin(((1*(2+3))*2)*(2^1))+sin(((1*(2+3))*2)*(2^1))",
				o.addBrackets("sin(1*(2+3)*2*2^1) + sin(1*(2+3)*2*2^1)"));
	}

}
