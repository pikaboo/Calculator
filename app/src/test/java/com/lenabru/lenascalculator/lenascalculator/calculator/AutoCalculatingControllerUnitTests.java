package com.lenabru.lenascalculator.lenascalculator.calculator;

import com.lenabru.lenascalculator.lenascalculator.calculator.setup.CalculatorControllerSetup;
import com.lenabru.lenascalculator.lenascalculator.controllers.AutoCalculatingController;
import com.lenabru.lenascalculator.lenascalculator.model.Operation;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Lena Brusilovski on 26-Jan 2017.
 */

public class AutoCalculatingControllerUnitTests
		extends CalculatorControllerSetup {

	@Before
	public void setUp() {
		super.setUp();
		sut = new AutoCalculatingController();
	}


	@Test
	public void testEventWasCalledTwiceForAppendValueAndCalculate()
			throws Exception {
		feedValue(1);
		assertThat(updateEventCallCount,is(2));
	}




	@Test
	public void testCalculateNotCalledWhenDividingByZero() throws Exception{
		sut.setOperation(Operation.DIVISION);
		feedValue(x);

		assertThat(sut.getResultValue().isZero(),is(true));
	}
}
