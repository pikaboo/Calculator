package com.lenabru.lenascalculator.rxsetup;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Lena Brusilovski on 27-Jan 2017.
 */

@RunWith(RxJavaTestRunner.class)
public class RxJavaUnitTest {



	private void test(final boolean onMainThread) {
		InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
			@Override
			public void run() {
				Context context = InstrumentationRegistry.getTargetContext();
				final EditText editText = new EditText(context);
				Observable<TextViewTextChangeEvent> observable = RxTextView.textChangeEvents(editText);
				if(onMainThread){
					observable.observeOn(AndroidSchedulers.mainThread());
				}
				final TestSubscriber<TextViewTextChangeEvent> testSubscriber = new TestSubscriber<>();
				observable.skip(1).subscribe(testSubscriber);

				assertThat(testSubscriber.isUnsubscribed(), is(false));

				editText.setText("1");

				List<TextViewTextChangeEvent> events = testSubscriber.getOnNextEvents();
				String firstString = events.get(0).text().toString();

				assertThat(firstString, is("1"));
			}
		});
	}

	@Test
	public void testSubscription()
			throws Exception {
		test(false);
	}

	@Test
	public void testSubscriptionWithMainThread()
			throws Exception {
				test(true);
	}
}
