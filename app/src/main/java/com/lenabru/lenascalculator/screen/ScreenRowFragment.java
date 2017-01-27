package com.lenabru.lenascalculator.screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.lenabru.lenascalculator.R;
import com.lenabru.lenascalculator.lenascalculator.controllers.AutoCalculatingController;
import com.lenabru.lenascalculator.lenascalculator.events.CalculatorEvents;
import com.lenabru.lenascalculator.lenascalculator.interfaces.Calculator;
import com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand;
import com.lenabru.lenascalculator.lenascalculator.model.Operation;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lena Brusilovski on 21-Jan 2017.
 */

public class ScreenRowFragment
		extends Fragment {

	private static final String KEY_OPERATION = "KEY_Operation";

	private CalculatorRowView rowView;

	private Subscription statusUpdatesSubscription;

	private Calculator calc = new AutoCalculatingController();

	private Operation operation;

	public static ScreenRowFragment withOperation(Operation operation) {
		Bundle args = new Bundle();
		args.putInt(KEY_OPERATION, operation.ordinal());
		ScreenRowFragment screenRowFragment = new ScreenRowFragment();
		screenRowFragment.setArguments(args);
		return screenRowFragment;
	}

	public EditText getX() {
		return rowView.getX();
	}

	public EditText getY() {
		return rowView.getY();
	}

	public EditText getResult() {
		return rowView.getResult();
	}

	public Operation getOperation() {
		return operation;
	}

	public CalculatorRowView getRowView() {
		return rowView;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		operation = Operation.values()[(getArguments().getInt(KEY_OPERATION, Operation.PLUS.ordinal()))];
		calc.setOperation(operation);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.calc_row, container, false);
		rowView = new CalculatorRowView(layout);
		rowView.operation.setText(operation.toString());


		rowView.xField.textChangesSubscription = subscribeForTextChanges(rowView.xField.value, textChangeAction);
		rowView.yField.textChangesSubscription = subscribeForTextChanges(rowView.yField.value, textChangeAction);

		statusUpdatesSubscription = CalculatorEvents.update//
				.filter(new Func1<Calculator, Boolean>() {
					@Override
					public Boolean call(Calculator calculator) {
						return calc == calculator;
					}
				})//
				.observeOn(AndroidSchedulers.mainThread())//
				.subscribe(new Action1<Calculator>() {
					@Override
					public void call(Calculator calculator) {
						rowView.result.value.setText(calculator.getResultValue().toString());
					}
				});

		return layout;
	}

	private Subscription subscribeForTextChanges(EditText v, Action1 textChangeAction) {
		return RxTextView.textChangeEvents(v)//
				.skip(1)//ignore the initial value
				.observeOn(AndroidSchedulers.mainThread())//
				.subscribe(textChangeAction);
	}

	private Action1 textChangeAction = new Action1<TextViewTextChangeEvent>() {
		@Override
		public void call(TextViewTextChangeEvent event) {
			calc.set(Operand.X,rowView.xField.value.getText().toString());

			calc.set(Operand.Y,rowView.yField.value.getText().toString());
		}
	};

	@Override
	public void onDestroyView() {
		rowView.unsubscribe();
		statusUpdatesSubscription.unsubscribe();
		super.onDestroyView();
	}
}
