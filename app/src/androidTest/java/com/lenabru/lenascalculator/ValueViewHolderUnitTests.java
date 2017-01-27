package com.lenabru.lenascalculator;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.lenabru.lenascalculator.screen.ValueViewHolder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import rx.functions.Action1;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Lena Brusilovski on 26-Jan 2017.
 */

@RunWith(Parameterized.class)
public class ValueViewHolderUnitTests
		extends ViewWithLayout{

	private int resId;

	private ValueViewHolder sut;



	public ValueViewHolderUnitTests(int resId) {
		this.resId = resId;
	}

	@Before
	public void setUp() {
		super.setUp();
		sut = sutForId(resId);
	}

	@Override
	public int getLayoutResId() {
		return R.layout.calc_row;
	}

	private ValueViewHolder sutForId(int resId) {
		return new ValueViewHolder(layout, resId);
	}

	@Parameters
	public static Collection ids() {
		return Arrays.asList(new Object[][]{{R.id.xValue}, {R.id.yValue}, {R.id.result}});
	}

	@Test
	public void testSutIsNotNull()
			throws Exception {
		assertThat(sut, is(notNullValue()));
	}

	@Test
	public void testValueIsNotNull()
			throws Exception {
		assertThat(sut.value, is(notNullValue()));
	}

	@Test
	public void testTextChangesSubscriptionIsUnsubscribed() {
		getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				sut.textChangesSubscription = RxTextView.textChangeEvents(sut.value).subscribe(new Action1<TextViewTextChangeEvent>() {
					@Override
					public void call(TextViewTextChangeEvent textViewTextChangeEvent) {

					}
				});
			}
		});

		sut.unsubscribe();

		assertThat(sut.textChangesSubscription.isUnsubscribed(), is(true));
	}


}
