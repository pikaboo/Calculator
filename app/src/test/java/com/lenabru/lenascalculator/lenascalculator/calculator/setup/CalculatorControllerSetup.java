package com.lenabru.lenascalculator.lenascalculator.calculator.setup;

import com.lenabru.lenascalculator.lenascalculator.calculator.TestWithDescription;
import com.lenabru.lenascalculator.lenascalculator.calculator.NumberSetup;
import com.lenabru.lenascalculator.lenascalculator.events.CalculatorEvents;
import com.lenabru.lenascalculator.lenascalculator.interfaces.Calculator;
import com.lenabru.lenascalculator.lenascalculator.model.Operation;

import org.junit.Before;
import org.junit.Rule;

import rx.functions.Action1;

import static com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand.X;
import static com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand.Y;

/**
 * Created by Lena Brusilovski on 26-Jan 2017.
 */


public  class CalculatorControllerSetup  implements NumberSetup {

	protected Calculator sut;
	protected double x = 123;
	protected double y = 10;

	protected int updateEventCallCount;

	@Rule
	public TestWithDescription description = new TestWithDescription();

	@Before
	public void setUp() {
		updateEventCallCount = 0;
		CalculatorEvents.update.subscribe(new Action1<Calculator>() {
			@Override
			public void call(Calculator calculator) {
				updateEventCallCount++;
			}
		});
	}


	protected void setOperandsWithOperation(Operation op) {
		sut.set(X,String.valueOf(x));
		sut.setOperation(op);
		sut.set(Y,String.valueOf(y));
	}

	protected void feedValue(double x) {
		sut.set(X,String.valueOf(x));
	}

}
