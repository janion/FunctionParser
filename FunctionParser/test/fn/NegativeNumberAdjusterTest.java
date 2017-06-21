package fn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NegativeNumberAdjusterTest {

	private NegativeNumberAdjuster a = new NegativeNumberAdjuster();

	@Test
	public void shouldAdjust1() {
		assertEquals("(0-2)", a.adjust("-2"));
	}

	@Test
	public void shouldAdjust2() {
		assertEquals("2*((0-2)*4)", a.adjust("2 * (-2 * 4)"));
	}

	@Test
	public void shouldAdjust3() {
		assertEquals("2*(x^(0-2)*4)", a.adjust("2 * (x^-2 * 4)"));
	}

	@Test
	public void shouldAdjust4() {
		assertEquals("sin(sqrt(((x-4.5)*(x-4.5))+((y-4.5)*(y-4.5)))-(2*sin(t)))+1",
				a.adjust("sin(sqrt(((x - 4.5) * (x - 4.5)) + ((y - 4.5) * (y - 4.5))) - (2 * sin(t))) + 1"));
	}

	@Test
	public void shouldAdjust5() {
		assertEquals("2*(x-(0-2)*(4/(3+2))+1)", a.adjust("2 * (x--2 * (4 / (3 + 2)) + 1)"));
	}

	@Test
	public void shouldAdjust6() {
		assertEquals("2*(x+(0-(4/(3+2)))+1)", a.adjust("2 * (x+-(4 / (3 + 2)) + 1)"));
	}
}
