package com.lenabru.lenascalculator.lenascalculator.interfaces;

import com.lenabru.lenascalculator.lenascalculator.model.AppendableNumber;
import com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand;
import com.lenabru.lenascalculator.lenascalculator.model.Operation;

/**
 * Created by Lena Brusilovski on 23-Jan 2017.
 */
public interface Calculator {

	void calculate();

	void setOperation(Operation operation);

	void clear();

	AppendableNumber getResultValue();

	boolean isDividingByZero();

	void set(Operand x, String s);
}
