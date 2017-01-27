package com.lenabru.lenascalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.lenabru.lenascalculator.activities.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Lena Brusilovski on 27-Jan 2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityUnitTests {

	private MainActivity sut;

	@Before
	public void setUp() {
		Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class);
		sut = (MainActivity) InstrumentationRegistry.getInstrumentation().startActivitySync(intent);
		InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				sut.getSupportFragmentManager().executePendingTransactions();
			}
		});
	}

	@Test
	public void testHave4ScreenRowFragments()
			throws Exception {
		List<Fragment> fragments = sut.getSupportFragmentManager().getFragments();

		assertThat(fragments, is(notNullValue()));
		assertThat(fragments.size(), is(4));
	}

	@Test
	public void testScreenRotated()
			throws Exception {
		InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {

				FieldsOnScreen fields = new FieldsOnScreen(sut);
				fields.firstXValueOnScreen.setText("1");
				fields.firstYValueOnScreen.setText("1");

				sut.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

				fields = new FieldsOnScreen(sut);

				assertThat(fields.firstXValueOnScreen.getText().toString(), is("1"));
				assertThat(fields.firstYValueOnScreen.getText().toString(), is("1"));
				assertThat(fields.firstResultOnScreen.getText().toString(), is("2.0"));
			}
		});
	}

	private static class FieldsOnScreen {

		public EditText firstXValueOnScreen;

		public EditText firstYValueOnScreen;

		public EditText firstResultOnScreen;

		public FieldsOnScreen(Activity sut) {
			firstXValueOnScreen = (EditText) sut.findViewById(R.id.xValue);
			firstYValueOnScreen = (EditText) sut.findViewById(R.id.yValue);
			firstResultOnScreen = (EditText) sut.findViewById(R.id.result);
		}
	}
}
