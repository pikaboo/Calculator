package com.lenabru.lenascalculator.lenascalculator.model;

/**
 * Created by Lena Brusilovski on 21-Jan 2017.
 */

public enum Operation {

	NONE(""),
	PLUS("+"),
	MINUS("-"),
	MULTIPLICATION("*"),
	DIVISION("/")
	;

	String display;

	Operation (String display){
		this.display = display;
	}

	@Override
	public String toString() {
		return display;
	}
}
