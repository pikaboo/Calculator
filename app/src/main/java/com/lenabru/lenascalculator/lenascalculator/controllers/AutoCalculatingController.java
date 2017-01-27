package com.lenabru.lenascalculator.lenascalculator.controllers;

import com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand;

/**
 * Created by Lena Brusilovski on 26-Jan 2017.
 */

public class AutoCalculatingController extends CalculatorController {

	@Override
	public void set(Operand op, String value) {
		super.set(op, value);
		if (isDividingByZero()){
			getResultValue().clear();
			return;
		}
		calculate();
	}
}
