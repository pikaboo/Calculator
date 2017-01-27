package com.lenabru.lenascalculator;

import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.lenabru.lenascalculator.screen.CalculatorRowView;
import com.lenabru.lenascalculator.screen.ValueViewHolder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.functions.Action1;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Lena Brusilovski on 26-Jan 2017.
 */

@RunWith(AndroidJUnit4.class)
public class CalculatorRowViewUnitTests
		extends ViewWithLayout {

	private CalculatorRowView sut;

	@Override
	public int getLayoutResId() {
		return R.layout.calc_row;
	}

	@Before
	public void setUp() {
		super.setUp();
		sut = new CalculatorRowView(layout);
	}

	private void subscribeToTextChanges(final ValueViewHolder field) {
		getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				field.textChangesSubscription = RxTextView.textChangeEvents(field.value)//
						.subscribe(new Action1<TextViewTextChangeEvent>() {
							@Override
							public void call(TextViewTextChangeEvent textViewTextChangeEvent) {

							}
						});
			}
		});
	}

	@Test
	public void testXFieldIsNotNull()
			throws Exception {
		assertThat(sut.xField, is(notNullValue()));
	}

	@Test
	public void testYFieldIsNotNull()
			throws Exception {
		assertThat(sut.yField, is(notNullValue()));
	}

	@Test
	public void testResultIsNotNull()
			throws Exception {
		assertThat(sut.result, is(notNullValue()));
	}

	@Test
	public void testOperationIsNotNull()
			throws Exception {
		assertThat(sut.operation, is(notNullValue()));
	}

	@Test
	public void testEqualsIsSet()
			throws Exception {
		String text = ((TextView) layout.findViewById(R.id.equals)).getText().toString();
		String expectedValue = context.getString(R.string.equals);

		assertThat(text, is(expectedValue));
	}

	@Test
	public void testUnsubscribexFieldIsForwarded()
			throws Exception {

		subscribeToTextChanges(sut.xField);

		sut.unsubscribe();
		assertThat(sut.xField.textChangesSubscription.isUnsubscribed(), is(true));
	}

	@Test
	public void testUnsubscribeyFieldIsForwarded()
			throws Exception {
		subscribeToTextChanges(sut.yField);

		sut.unsubscribe();
		assertThat(sut.yField.textChangesSubscription.isUnsubscribed(), is(true));
	}

	@Test
	public void testUnsubscribeResultIsForwarded()
			throws Exception {
		subscribeToTextChanges(sut.result);


		sut.unsubscribe();
		assertThat(sut.result.textChangesSubscription.isUnsubscribed(), is(true));
	}
}
