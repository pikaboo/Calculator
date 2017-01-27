package com.lenabru.lenascalculator.lenascalculator.model;

/**
 * Created by Lena Brusilovski on 21-Jan 2017.
 */

public class CalculatorModel {

	private AppendableNumber x = new AppendableNumber();

	private AppendableNumber y = new AppendableNumber();

	public enum Operand {
		X, Y;
	}

	;

	private AppendableNumber[] operands = new AppendableNumber[]{x, y};

	private Operation operation = Operation.NONE;



	public AppendableNumber getX() {
		return x;
	}

	public AppendableNumber getY() {
		return y;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public void clear() {
		x.clear();
		y.clear();
		operation = Operation.NONE;
	}

	@Override
	public String toString() {
		return "x:" + x + ",y:" + y + " op:" + operation;
	}


	public boolean isClear() {
		return x.isZero() && y.isZero() && operation == Operation.NONE;
	}


	public void set(Operand operand, Number value) {
		set(operand, value.toString());
	}

	public void set(Operand operand, String value) {
		int op = operand.ordinal();
		operands[op].clear();
		operands[op].set(value);
	}
}
