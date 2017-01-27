package com.lenabru.lenascalculator.lenascalculator.controllers;

import com.lenabru.lenascalculator.lenascalculator.events.CalculatorEvents;
import com.lenabru.lenascalculator.lenascalculator.interfaces.Calculator;
import com.lenabru.lenascalculator.lenascalculator.model.AppendableNumber;
import com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel;
import com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand;
import com.lenabru.lenascalculator.lenascalculator.model.Operation;

/**
 * Created by Lena Brusilovski on 21-Jan 2017.
 */

public class CalculatorController
		implements Calculator {

	private CalculatorModel model = new CalculatorModel();

	private CalculatorEngine engine = new CalculatorEngine(8);

	private AppendableNumber resultValue = new AppendableNumber();

	@Override
	public void calculate() {
		Double result = null;
		switch (model.getOperation()) {
			case NONE:
				result = model.getX().doubleValue();
				break;
			case PLUS:
				result = engine.sum(model.getX().doubleValue(), model.getY().doubleValue());
				break;
			case MINUS:
				result = engine.subtract(model.getX().doubleValue(), model.getY().doubleValue());
				break;
			case MULTIPLICATION:
				result = engine.multiply(model.getX().doubleValue(), model.getY().doubleValue());
				break;
			case DIVISION:
				result = engine.divide(model.getX().doubleValue(), model.getY().doubleValue());
				break;
		}
		resultValue.set(result);
		update();
	}


	@Override
	public void setOperation(Operation operation) {
		model.setOperation(operation);
		update();
	}


	@Override
	public void clear() {
		model.clear();
		resultValue.clear();
		update();
	}


	@Override
	public AppendableNumber getResultValue() {
		return resultValue;
	}


	@Override
	public boolean isDividingByZero(){
		return model.getOperation() == Operation.DIVISION && model.getY().isZero();
	}

	@Override
	public void set(Operand x, String s) {
		model.set(x,s);
		if(model.getX().isZero() && model.getY().isZero()){
			resultValue.clear();
		}

		update();
	}

	private void update() {CalculatorEvents.update.onNext(this);}
}
