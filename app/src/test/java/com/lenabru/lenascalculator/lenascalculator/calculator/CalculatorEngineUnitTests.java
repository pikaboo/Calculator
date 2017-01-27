package com.lenabru.lenascalculator.lenascalculator.calculator;

import com.lenabru.lenascalculator.lenascalculator.controllers.CalculatorEngine;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CalculatorEngineUnitTests extends TestWithDescription{

	private CalculatorEngine sut;
	private int calculatorPrecision = 8;

	@Before
	public void setUp(){
		sut = new CalculatorEngine(calculatorPrecision);
	}

	@Test
	public void addition_isCorrect()
			throws Exception {
		double x = 1;
		double y = 1;


		double actualSum = sut.sum(x,y);

		double expectedSum = 2;
		assertThat(actualSum,is(expectedSum));
	}

	@Test
	public void subtraction_isCorrect() throws Exception{
		double x = 1;
		double y = 1;

		double actualSubtraction = sut.subtract(x,y);

		double expectedSubtraction = 0;
		assertThat(actualSubtraction,is(expectedSubtraction));
	}

	@Test
	public void multiplication_isCorrect() throws Exception{
		double x = 20;
		double y = 50;

		double actualMult = sut.multiply(x,y);

		double expectedMult = 1000;
		assertThat(actualMult,is(expectedMult));
	}

	@Test
	public void testMultiplyNonWholeNumbers() throws Exception {

		double x = 1.2;
		double y = 1.5;


		double actualResult = sut.multiply(x,y);

		double expectedResult = 1.8;
		assertThat(actualResult,is(expectedResult));
	}
	@Test
	public void testDivideNonWholeNumbers() throws Exception {

		double x = 1.2;
		double y = 1.5;


		double actualResult = sut.divide(x,y);

		double expectedResult = 0.8;
		assertThat(actualResult,is(expectedResult));
	}

	@Test
	public void division_isCorrect() throws Exception {
		double x = 20;
		double y = 5;

		double actualDiv = sut.divide(x,y);

		double expectedDiv = 4;
		assertThat(actualDiv,is(expectedDiv));
	}

	@Test(expected = ArithmeticException.class)
	public void divideByZeroShouldThrowException() throws Exception{

		double x = 1;
		double y = 0;

		sut.divide(x,y);

	}
}