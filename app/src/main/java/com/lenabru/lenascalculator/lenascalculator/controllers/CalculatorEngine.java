package com.lenabru.lenascalculator.lenascalculator.controllers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by Lena Brusilovski on 19-Jan 2017.
 */

public class CalculatorEngine {

	private int precision;
	private MathContext mathContext;


	public CalculatorEngine(int precision) {
		this.precision = precision;
		mathContext = new MathContext(precision, RoundingMode.HALF_EVEN);
	}

	public Double sum(Double x, Double y){
		return wrap(x).add(wrap(y)).doubleValue();
	}

	public Double subtract(Double x, Double y){
		return wrap(x).subtract(wrap(y)).doubleValue();
	}

	public Double multiply(Double x, Double y){
		BigDecimal result = wrap(x).multiply(wrap(y), mathContext);
		return result.doubleValue();
	}

	public Double divide(Double x, Double y){
		BigDecimal result = wrap(x).divide(wrap(y),mathContext);
		return result.doubleValue();
	}

	private BigDecimal wrap(Double d){
		return BigDecimal.valueOf(d);
	}
}
