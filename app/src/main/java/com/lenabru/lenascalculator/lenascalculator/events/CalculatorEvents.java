package com.lenabru.lenascalculator.lenascalculator.events;

import com.lenabru.lenascalculator.lenascalculator.interfaces.Calculator;

import rx.subjects.PublishSubject;

/**
 * Created by Lena Brusilovski on 23-Jan 2017.
 */

public class CalculatorEvents {

	public static PublishSubject<Calculator> update = PublishSubject.create();
}
