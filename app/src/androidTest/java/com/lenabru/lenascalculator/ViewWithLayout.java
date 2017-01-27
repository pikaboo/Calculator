package com.lenabru.lenascalculator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.view.LayoutInflater;
import android.view.View;

import org.junit.Before;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Lena Brusilovski on 26-Jan 2017.
 */

public abstract class ViewWithLayout {


	protected View layout;
	protected Context context;
	@Before
	public void setUp(){
		context = InstrumentationRegistry.getTargetContext();
		assertThat(context, is(notNullValue()));
		int layoutResId = getLayoutResId();
		assertThat(layoutResId,is(not(0)));
		layout = LayoutInflater.from(context).inflate(layoutResId, null, false);
		assertThat(layout, is(notNullValue()));
	}

	public abstract int getLayoutResId();
}
