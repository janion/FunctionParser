package fn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunctionParserTest {
	
	private static final double ACCURACY = 1e-8;

	private FunctionParser parser = new FunctionParser();

	@Test
	public void shouldParseFunctionRegardlessOfSpacesOrCase() {
		Function fn = parser.parseFunction("((sin((X+1)/t)) * (sin((x+1)/T))) + ((cos((x+1)/t)) * (cos((x+1)/t)))");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowIllegalStateExceptionWhenEvaluatedWithoutParameterSpecified() {
		Function fn = parser.parseFunction("((sin((X+1)/t)) * (sin((x+1)/T))) + ((cos((x+1)/t)) * (cos((x+1)/t)))");
		fn.evaluate(new String[] {"x", "y"} , new double[] {1., 2.});
	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowIllegalStateExceptionWhenEvaluatedWithWrongNumberOfParameterSpecified() {
		Function fn = parser.parseFunction("((sin((X+1)/t)) * (sin((x+1)/T))) + ((cos((x+1)/t)) * (cos((x+1)/t)))");
		fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2.});
	}

	@Test
	public void shouldParseFunction1() {
		Function fn = parser.parseFunction("((sin((x+1)/t)) * (sin((x+1)/t))) + ((cos((x+1)/t)) * (cos((x+1)/t)))");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction2() {  
    Function fn = parser.parseFunction("sin((x+1)/t) * sin((x+1)/t)");
	assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
	double val = Math.sin(0.5);
    assertEquals(val * val, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction3() {
		Function fn = parser.parseFunction("cos((x+1)/t) * cos((x+1)/t)");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		double val = Math.cos(0.5);
	    assertEquals(val * val, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction4() {
		Function fn = parser.parseFunction("(sin((x+1)/t) * sin((x+1)/t)) + (cos((x+1)/t) * cos((x+1)/t))");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction5() {
		Function fn = parser.parseFunction("(1+x)/t");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(0.5, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction6() {
		Function fn = parser.parseFunction("(y-1)/t");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(0.25, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction7() {
		Function fn = parser.parseFunction("1.12*x");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(1.12, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction8() {
		Function fn = parser.parseFunction("(1.12*x)+y");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(3.12, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction9() {
		Function fn = parser.parseFunction("sin(t)");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(Math.sin(4), fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction10() {
		Function fn = parser.parseFunction("sqrt(t)");
		assertEquals(2, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(2, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
		assertEquals(-2, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(1), ACCURACY);
	}

	@Test
	public void shouldParseFunction11() {
		Function fn = parser.parseFunction("sqrt((2*x) + y) / t");
		assertEquals(2, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(0.5, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
		assertEquals(-0.5, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(1), ACCURACY);
	}

	@Test
	public void shouldParseFunction12() {
		Function fn = parser.parseFunction("sqrt(4) + 2");
		assertEquals(2, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(4, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
		assertEquals(0, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(1), ACCURACY);
	}

	@Test
	public void shouldParseFunction13() {
		Function fn = parser.parseFunction("0");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(0, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction14() {
		Function fn = parser.parseFunction("tan(3.14159265358979 / 2)");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(6.189863256179242e14, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction15() {
		Function fn = parser.parseFunction("y + (x / 0)");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(Double.POSITIVE_INFINITY, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

//	@Test
//	public void shouldParseFunction16() {
//		Function fn = parser.parseFunction("3 + sqrt( 1 - ( ( (x-3) * (x-3)) + ( (y-3) * (y-3) ) ) )");
//		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
//		assertEquals(fn.evaluate(3., 3., 4.).get(0), ACCURACY);
//		assertEquals(fn.evaluate(2., 3., 4.).get(0), ACCURACY);
//		assertEquals(fn.evaluate(2., 2., 4.).get(0), ACCURACY);
//	}

	@Test
	public void shouldParseFunction17() {
		Function fn = parser.parseFunction("sqrt(sqrt(x))");
		assertEquals(4, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
		assertEquals(-1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(1), ACCURACY);
		assertEquals(Double.NaN, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(2), ACCURACY);
		assertEquals(Double.NaN, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(3), ACCURACY);
	}

	@Test
	public void shouldParseFunction18() {
		Function fn = parser.parseFunction("((1 / 0) * 0) + 7");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(Double.NaN, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction19() {
		Function fn = parser.parseFunction("abs(0-7)");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(7, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction20() {
		Function fn = parser.parseFunction("exp(1)");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(Math.exp(1), fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction21() {
		Function fn = parser.parseFunction("log(exp(1))");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction22() {
		Function fn = parser.parseFunction("11 % 7");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(4, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}

	@Test
	public void shouldParseFunction23() {
		Function fn = parser.parseFunction("2 ^ 9");
		assertEquals(1, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).size());
		assertEquals(512, fn.evaluate(new String[] {"x", "y", "t"} , new double[] {1., 2., 4.}).get(0), ACCURACY);
	}
}
