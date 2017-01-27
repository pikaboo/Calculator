package com.lenabru.lenascalculator.screen;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lenabru.lenascalculator.R;

/**
 * Created by Lena Brusilovski on 26-Jan 2017.
 */

public class CalculatorRowView {

	public ValueViewHolder xField;

	public ValueViewHolder yField;

	public ValueViewHolder result;


	public TextView operation;

	public CalculatorRowView(View itemView) {

		xField = new ValueViewHolder(itemView, R.id.xValue);
		yField = new ValueViewHolder(itemView, R.id.yValue);
		result = new ValueViewHolder(itemView, R.id.result);
		operation = (TextView) itemView.findViewById(R.id.operation);
		((TextView)itemView.findViewById(R.id.equals)).setText(R.string.equals);


	}


	public void unsubscribe() {
		xField.unsubscribe();
		yField.unsubscribe();
		result.unsubscribe();
	}


	public EditText getX(){
		return xField.value;
	}

	public EditText getY(){
		return yField.value;
	}

	public EditText getResult(){
		return result.value;
	}

}
