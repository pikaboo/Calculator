package com.lenabru.lenascalculator.lenascalculator.calculator;

import com.lenabru.lenascalculator.lenascalculator.calculator.setup.CalculatorControllerSetup;
import com.lenabru.lenascalculator.lenascalculator.controllers.CalculatorController;
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

public class CalculatorControllerUnitTests extends CalculatorControllerSetup {




	@Before
	public void setUp() {
		super.setUp();
		sut = new CalculatorController();
	}


	@Test
	public void makeSureSutIsNotNull() throws Exception{
		assertThat(sut,is(notNullValue()));
	}


	@Test
	public void testCalculateCanAdd() throws Exception{
		setOperandsWithOperation(Operation.PLUS);
		sut.calculate();

		double expectedResult = x+y;

		assertThat(sut.getResultValue().doubleValue(),is(expectedResult));
	}



	@Test
	public void testCalculateCanSubtract() throws Exception{
		setOperandsWithOperation(Operation.MINUS);
		sut.calculate();

		double expectedResult = x-y;

		assertThat(sut.getResultValue().doubleValue(),is(expectedResult));
	}

	@Test
	public void testCalculateCanMultiply() throws Exception{
		setOperandsWithOperation(Operation.MULTIPLICATION);
		sut.calculate();

		double expectedResult = x*y;

		assertThat(sut.getResultValue().doubleValue(),is(expectedResult));
	}

	@Test
	public void testCalculateCanDivide() throws Exception{
		setOperandsWithOperation(Operation.DIVISION);
		sut.calculate();

		double expectedResult = x/y;

		assertThat(sut.getResultValue().doubleValue(),is(expectedResult));
	}

	@Test
	public void testOperationNoneDoesNothing() throws Exception{
		feedValue(x);
		sut.calculate();

		double expectedResult = x;

		assertThat(sut.getResultValue().doubleValue(),is(expectedResult));
	}


	@Test
	public void testClearEverythingShouldClearResult() throws Exception{
		setOperandsWithOperation(Operation.MULTIPLICATION);
		sut.calculate();

		sut.clear();

		assertThat(sut.getResultValue().toString(),is(EMPTY));
	}



	@Test
	public void testCalculateSendsAnUpdate() throws Exception{
		sut.calculate();

		assertThat(updateEventCallCount,is(1));
	}

	@Test
	public void testSetOperationSendsAnUpdate() throws Exception{
		sut.setOperation(Operation.PLUS);

		assertThat(updateEventCallCount,is(1));
	}

	@Test
	public void testAppendValueSendsAnUpdate() throws Exception{
		feedValue(x);
		assertThat(updateEventCallCount,is(1));
	}

	@Test
	public void testSettingValusTo0UpdatesResultToZero() throws Exception{
		setOperandsWithOperation(Operation.PLUS);
		sut.calculate();
		sut.set(X,"0");
		sut.set(Y,"0");

		sut.calculate();

		assertThat(sut.getResultValue().isZero(),is(true));
	}

	@Test
	public void testMinusResultLessThan1() throws Exception {
		sut.setOperation(Operation.MINUS);
		sut.set(X,"1.5");
		sut.set(Y,"1.6");

		sut.calculate();

		assertThat(sut.getResultValue().toString(),is("-0.1"));
	}

}