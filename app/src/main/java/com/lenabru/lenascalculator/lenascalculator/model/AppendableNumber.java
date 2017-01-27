package com.lenabru.lenascalculator.lenascalculator.model;

/**
 * Created by Lena Brusilovski on 21-Jan 2017.
 */

public class AppendableNumber
		extends Number {

	private StringBuilder number = new StringBuilder();

	@Override
	public int intValue() {
		return Double.valueOf(toString()).intValue();
	}

	@Override
	public long longValue() {
		return Double.valueOf(toString()).longValue();
	}

	@Override
	public float floatValue() {
		return Double.valueOf(toString()).floatValue();
	}

	@Override
	public double doubleValue() {
		return Double.valueOf(toString());
	}

	@Override
	public String toString() {
		if (number.length() == 0) {
			return "0.0";
		}

		if (number.toString().startsWith(".")) {
			return "0" + number.toString();
		}

		return number.toString();
	}

	public boolean isZero() {return doubleValue() == 0.0;}

	public void clearDigit() {

		if (number.length() == 0) {
			return;
		}

		int numberOfCharsToDelete = 1;

		if (number.toString().endsWith(".0")) {
			numberOfCharsToDelete = 3;
		}

		number.deleteCharAt(number.length() - numberOfCharsToDelete);
	}

	public void clear() {
		number.delete(0, number.length());
	}

	public void set(Number x) {
		clear();
		number.append(x.toString());
	}

	public void set(String value) {

		String regex = "^[-+]?[0-9]*\\.?[0-9]*$";
		boolean isValid = value.matches(regex);

		if (isValid) {
			clear();
			number.append(value);
		}
	}

	public String currentValue(){
		return number.toString();
	}
}
