package com.lenabru.lenascalculator;

import android.support.test.InstrumentationRegistry;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lenabru.lenascalculator.lenascalculator.events.CalculatorEvents;
import com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand;
import com.lenabru.lenascalculator.lenascalculator.model.Operation;
import com.lenabru.lenascalculator.rxsetup.RxJavaTestRunner;
import com.lenabru.lenascalculator.screen.ScreenRowFragment;
import com.lenabru.lenascalculator.screen.ValueViewHolder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand.X;
import static com.lenabru.lenascalculator.lenascalculator.model.CalculatorModel.Operand.Y;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Lena Brusilovski on 26-Jan 2017.
 */
@RunWith(RxJavaTestRunner.class)
public class ScreenRowFragmentUnitTests {

	private ScreenRowFragment sut;

	@Before
	public void setUp() {
		setupFragmentWithOperation(Operation.PLUS);
	}

	private void setupFragmentWithOperation(Operation op) {
		sut = ScreenRowFragment.withOperation(op);
		sut.onCreate(null);

		InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				ViewGroup vg = new ViewGroup(InstrumentationRegistry.getTargetContext()) {
					@Override
					protected void onLayout(boolean changed, int l, int t, int r, int b) {

					}
				};
				sut.onCreateView(LayoutInflater.from(InstrumentationRegistry.getTargetContext()), vg, null);
				sut.onResume();
			}
		});
	}

	private void testEditingValueFor(final ValueViewHolder field) {
		InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				assertThat(field.textChangesSubscription, is(notNullValue()));
				assertThat(field.textChangesSubscription.isUnsubscribed(), is(false));

				field.value.setText("1");

				assertThat(sut.getResult().getText().toString(), is("1.0"));
			}
		});
	}

	private void feedInputValue(Operand op, String value) {
		EditText fieldToEdit = null;
		switch (op) {
			case X:
				fieldToEdit = sut.getX();
				break;
			case Y:
				fieldToEdit = sut.getY();
				break;
		}
		fieldToEdit.setText(value);
	}

	private void destroyFragment(final Runnable onDestroyedAction) {
		InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				sut.onPause();
				sut.onDestroyView();
				if (onDestroyedAction != null) {
					onDestroyedAction.run();
				}
			}
		});
	}

	@Test
	public void testEditingXValueChangesResultText()
			throws Exception {
		testEditingValueFor(sut.getRowView().xField);
	}

	@Test
	public void testEditingYValueChangesResultText()
			throws Exception {
		testEditingValueFor(sut.getRowView().yField);
	}

	@Test
	public void testEnteringValuesIntoXAndY_YieldsCorrectResult()
			throws Exception {
		Map<Operation, String> opsToResults = new HashMap<>();
		opsToResults.put(Operation.PLUS, "3.1");
		opsToResults.put(Operation.MINUS, "-0.1");
		opsToResults.put(Operation.MULTIPLICATION, "2.4");
		opsToResults.put(Operation.DIVISION, "0.9375");

		for (Operation op : opsToResults.keySet()) {
			setupFragmentWithOperation(op);
			feedInputValue(X, "1.5");
			feedInputValue(Y, "1.6");

			assertThat(sut.getResult().getText().toString(), is(opsToResults.get(op)));
		}
	}

	@Test
	public void testEnteringValuesIntoXAndYAndDeleteThem_clearsTheResult()
			throws Exception {
		feedInputValue(X, "1.5");
		feedInputValue(Y, "1.5");

		feedInputValue(X, "");
		feedInputValue(Y, "");

		assertThat(sut.getResult().getText().toString(), is("0.0"));
	}

	@Test
	public void testEnteringValuesIntoXAndYAndDeleteX_YieldsCorrectResult()
			throws Exception {
		feedInputValue(X, "1.5");
		feedInputValue(Y, "1.5");

		feedInputValue(X, "");

		assertThat(sut.getResult().getText().toString(), is("1.5"));
	}

	@Test
	public void testSubscriptionIsUnsubscribed() {
		destroyFragment(new Runnable() {
			@Override
			public void run() {
				assertThat(sut.getRowView().xField.textChangesSubscription.isUnsubscribed(),is(true));
			}
		});
	}



	@Test
	public void testYFieldSubscriptionIsUnsubscribed() throws Exception{
		destroyFragment(new Runnable() {
			@Override
			public void run() {
				assertThat(sut.getRowView().yField.textChangesSubscription.isUnsubscribed(),is(true));
			}
		});
	}
	@Test
	public void testUnsubscribedFromCalculatorUpdated()
			throws Exception {
		destroyFragment(new Runnable() {
			@Override
			public void run() {
				assertThat(CalculatorEvents.update.hasObservers(),is(false));
			}
		});

	}
}
