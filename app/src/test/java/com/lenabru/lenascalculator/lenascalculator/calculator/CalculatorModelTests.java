package com.lenabru.lenascalculator.lenascalculator.calculator;

import com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel;
import com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand;
import com.lenabru.lenascalculator.lenascalculator.model.Operation;

import org.junit.Before;
import org.junit.Test;

import static com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand.X;
import static com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand.Y;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Lena Brusilovski on 22-Jan 2017.
 */

public class CalculatorModelTests extends TestWithDescription implements NumberSetup{


	private static class CalculatorModelCustomToString
			extends CalculatorModel {

		@Override
		public String toString() {
			if (getOperation() == Operation.NONE)
				return getX().toString();
			return getX() + " " + getOperation() + " " + getY();
		}
	}

	private CalculatorModel sut;

	@Before
	public void setUp() {
		sut = new CalculatorModelCustomToString();
	}

	private void feedInputValue(Operand op, String value) {
		System.out.println("feedInputValue Input value:" + value);
		sut.set(op,value);
		System.out.println("feedInputValue Output value:" + sut);
	}

	private void feedXValueWithOperation(String value, Operation operation) {
		feedInputValue(X,value);
		sut.setOperation(operation);
	}

	@Test
	public void testModelIsEmpty()
			throws Exception {
		assertThat(sut.toString(), is(EMPTY));
		assertThat(sut.getX().isZero(),is(true));
		assertThat(sut.getY().isZero(),is(true));
		assertThat(sut.getOperation(),is(Operation.NONE));
	}

	@Test
	public void testAppendValuesToX()
			throws Exception {
		String value = "123";

		feedInputValue(X,value);

		String expectedResult = "123";
		assertThat(sut.toString(), is(expectedResult));
	}

	@Test
	public void testAddPlusOperation()
			throws Exception {
		String value = "123.123";
		Operation operation = Operation.PLUS;

		feedXValueWithOperation(value, operation);

		String expectedToString = "123.123 + 0.0";
		assertThat(sut.toString(), is(expectedToString));
	}

	@Test
	public void testAddMinusOperation()
			throws Exception {
		String value = "123.123";
		Operation operation = Operation.MINUS;

		feedXValueWithOperation(value, operation);

		String expectedToString = "123.123 - 0.0";
		assertThat(sut.toString(), is(expectedToString));
	}

	@Test
	public void testAddMultiplyOperation()
			throws Exception {
		String value = "123.123";
		Operation operation = Operation.MULTIPLICATION;

		feedXValueWithOperation(value, operation);

		String expectedToString = "123.123 * 0.0";
		assertThat(sut.toString(), is(expectedToString));
	}

	@Test
	public void testAddDivisionOperation()
			throws Exception {
		String value = "123.123";
		Operation operation = Operation.DIVISION;

		feedXValueWithOperation(value, operation);

		String expectedToString = "123.123 / 0.0";
		assertThat(sut.toString(), is(expectedToString));
	}

	@Test
	public void testFeedsSecondValue()
			throws Exception {
		String value = "123.123";
		Operation operation = Operation.PLUS;
		feedXValueWithOperation(value, operation);

		feedInputValue(Y,value);

		String expectedToString = "123.123 + 123.123";
		assertThat(sut.toString(), is(expectedToString));
	}


	@Test
	public void testClearEntireValue()
			throws Exception {
		String value = "324";
		feedInputValue(X,value);

		sut.clear();

		assertThat(sut.toString(), is(EMPTY));
	}

	@Test
	public void testClearModel()
			throws Exception {

		sut.clear();

		assertThat(sut.toString(), is(EMPTY));
	}

	@Test
	public void testClearWithOneOperand()
			throws Exception {

		String value = "123";
		feedInputValue(X,value);

		sut.clear();

		assertThat(sut.toString(), is(EMPTY));
	}

	@Test
	public void testClearWithOneOperandAndOperator()
			throws Exception {

		String value = "123";
		Operation op = Operation.DIVISION;
		feedXValueWithOperation(value, op);

		sut.clear();

		assertThat(sut.toString(), is(EMPTY));
	}

	@Test
	public void testClearWithTwoOperandAndOperator()
			throws Exception {

		String value = "123";
		Operation op = Operation.DIVISION;
		feedXValueWithOperation(value, op);
		feedInputValue(Y,value);

		sut.clear();

		assertThat(sut.toString(), is(EMPTY));
	}

	@Test
	public void testSetXNumber()
			throws Exception {
		Integer value = 123;

		sut.set(X,value);

		assertThat(sut.getX().toString(), is(value.toString()));
	}
	@Test
	public void testSetYNumber()
			throws Exception {
		Integer value = 123;

		sut.set(Y,value);

		assertThat(sut.getY().toString(), is(value.toString()));
	}

	@Test
	public void testIsClearReturnsTrueWhenNothingIsSet() throws Exception {
		assertThat(sut.isClear(),is(true));
	}

	@Test
	public void testIsClearReturnsFalseWhenXIsSet() throws Exception {
		feedInputValue(X,"123");

		assertThat(sut.isClear(),is(false));
	}

	@Test
	public void testIsClearReturnsFalseWhenOperationIsSet() throws Exception {
		sut.setOperation(Operation.MULTIPLICATION);

		assertThat(sut.isClear(),is(false));
	}

    @Test
	public void testIsClearReturnsFalseWhenYIsSet() throws Exception {
		feedInputValue(Y,"123");

		assertThat(sut.isClear(),is(false));
	}

	@Test
	public void testIsClearReturnsFalseWhenOperationISSet() throws Exception{
		sut.setOperation(Operation.PLUS);

		assertThat(sut.isClear(),is(false));
	}

	@Test
	public void testToStringIsntNull() throws Exception{
		sut = new CalculatorModel();

		assertThat(sut.toString(),is(notNullValue()));
	}

}
