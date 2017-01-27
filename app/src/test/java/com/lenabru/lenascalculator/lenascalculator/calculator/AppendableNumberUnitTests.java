package com.lenabru.lenascalculator.lenascalculator.calculator;

import com.lenabru.lenascalculator.lenascalculator.model.AppendableNumber;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Lena Brusilovski on 21-Jan 2017.
 */

public class AppendableNumberUnitTests extends TestWithDescription{

	@Rule
	public TestName name = new TestName();

	private AppendableNumber sut;

	@Before
	public void setUp() {
		sut = new AppendableNumber();
	}

	private void feedInputValue(String valueString) {
		System.out.println("feedInputValue Input value:" + valueString);
		for (char c : valueString.toCharArray()) {
			String currentValue = sut.currentValue();
			System.out.println("currentValue:" + currentValue);
			System.out.println("appending: " + c);
			String newValue = currentValue + c;
			System.out.println("new value:" + newValue);
			sut.set(newValue);
		}
		System.out.println("feedInputValue Output value: " + sut.toString());
	}

	@Test
	public void testEmptyNumberGives0Double()
			throws Exception {
		assertThat(sut.doubleValue(), is(0.0));
	}

	@Test
	public void testEmptyNumberGives0Long()
			throws Exception {
		assertThat(sut.longValue(), is(0L));
	}

	@Test
	public void testEmptyNumberGives0Integer()
			throws Exception {
		assertThat(sut.intValue(), is(0));
	}

	@Test
	public void testEmptyNumberGives0Short()
			throws Exception {
		short value = 0;

		assertThat(sut.shortValue(), is(value));
	}

	@Test
	public void testEmptyNumberGives0Byte()
			throws Exception {
		byte value = 0;

		assertThat(sut.byteValue(), is(value));
	}

	@Test
	public void testValueIsCorrectInt()
			throws Exception {
		String valueString = "123";

		feedInputValue(valueString);

		assertThat(sut.intValue(), is(Integer.valueOf(valueString)));
	}

	@Test
	public void testValueIsCorrectIntWithInputDecimal()
			throws Exception {
		String valueString = "123.0";

		feedInputValue(valueString);

		assertThat(sut.intValue(), is(Integer.valueOf("123")));
	}

	@Test
	public void testNegativeValueIsCorrectInt()
			throws Exception {
		String valueString = "-234";

		feedInputValue(valueString);

		assertThat(sut.intValue(), is(Integer.valueOf(valueString)));
	}

	@Test
	public void testValueIsCorrectDouble()
			throws Exception {
		String valueString = "123.123";

		feedInputValue(valueString);

		assertThat(sut.doubleValue(), is(Double.valueOf(valueString)));
	}

	@Test
	public void testNegativeValueIsCorrectDouble()
			throws Exception {
		String valueString = "-234.123";

		feedInputValue(valueString);

		assertThat(sut.doubleValue(), is(Double.valueOf(valueString)));
	}

	@Test
	public void testValueIsCorrectFloat()
			throws Exception {
		String valueString = "123.123";

		feedInputValue(valueString);

		assertThat(sut.floatValue(), is(Float.valueOf(valueString)));
	}

	@Test
	public void testNegativeValueIsCorrectFloat()
			throws Exception {
		String valueString = "-234.123";

		feedInputValue(valueString);

		assertThat(sut.floatValue(), is(Float.valueOf(valueString)));
	}

	@Test
	public void testMoreThanOneMinusSignIgnored()
			throws Exception {
		String valueString = "--123";

		feedInputValue(valueString);

		Integer expectedResult = -123;
		assertThat(sut.intValue(), is(expectedResult));
	}

	@Test
	public void testMoreThanOneDotSignIgnored()
			throws Exception {
		String valueString = "123..123";

		feedInputValue(valueString);

		Double expectedResult = 123.123;
		assertThat(sut.doubleValue(), is(expectedResult));
	}

	@Test
	public void testOnlyFloatingPointIsCorrect()
			throws Exception {
		String valueString = ".123";

		sut.set(valueString);

		Double expectedResult = 0.123;
		assertThat(sut.doubleValue(), is(expectedResult));
	}

	@Test
	public void testOnlyZeroCorrect()
			throws Exception {
		String valueString = "0";

		feedInputValue(valueString);

		String expectedResult = "0";
		assertThat(sut.toString(), is(expectedResult));
	}

	@Test
	public void testClearDigitOnEmptyNumber()
			throws Exception {
		sut.clearDigit();

		assertThat(sut.intValue(), is(0));
	}

	@Test
	public void testClearDigitIsCorrect()
			throws Exception {
		String valueString = "123";
		feedInputValue(valueString);

		sut.clearDigit();

		Integer expectedResult = 12;
		assertThat(sut.intValue(), is(expectedResult));
	}

	@Test
	public void testClearWholeEmptyNumber()
			throws Exception {
		sut.clear();

		assertThat(sut.intValue(), is(0));
	}

	@Test
	public void testClearWholeNumber()
			throws Exception {
		String valueString = "123";
		feedInputValue(valueString);

		sut.clear();

		assertThat(sut.intValue(), is(0));
	}

	@Test
	public void testClearDigitClearsDoubleCorrectly()
			throws Exception {
		sut.set(123.0);

		sut.clearDigit();

		int expectedValue = 12;

		assertThat(sut.intValue(), is(expectedValue));
	}

	@Test
	public void testSetNumber()
			throws Exception {
		Integer value = 123;

		sut.set(value);

		assertThat(sut.toString(), is(value.toString()));
	}

	@Test
	public void testDotNumberConvertsToZeroDotNumber()
			throws Exception {
		String value = ".1";
		sut.set(value);

		assertThat(sut.toString(), is("0.1"));
	}
}
