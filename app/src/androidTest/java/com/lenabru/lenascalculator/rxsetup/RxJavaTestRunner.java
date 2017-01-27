package com.lenabru.lenascalculator.rxsetup;

import org.junit.Rule;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

/**
 * Created by Lena Brusilovski on 27-Jan 2017.
 */

public class RxJavaTestRunner extends BlockJUnit4ClassRunner {

	@Rule
	// Must be added to every test class that targets app code that uses RxJava
	public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

	/**
	 * Creates a BlockJUnit4ClassRunner to run {@code klass}
	 *
	 * @param klass
	 * @throws InitializationError if the test class is malformed.
	 */
	public RxJavaTestRunner(Class<?> klass)
			throws InitializationError {
		super(klass);

		RxAndroidPlugins.getInstance().reset();
		RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook(){
			@Override
			public Scheduler getMainThreadScheduler() {
				return Schedulers.immediate();
			}
		});

	}


}
