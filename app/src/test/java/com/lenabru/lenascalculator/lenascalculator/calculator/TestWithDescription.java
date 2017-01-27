package com.lenabru.lenascalculator.lenascalculator.calculator;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Lena Brusilovski on 25-Jan 2017.
 */

public class TestWithDescription implements TestRule {

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		@Override
		protected void starting(final Description description) {
			String methodName = description.getMethodName();
			String className = description.getClassName();
			className = className.substring(className.lastIndexOf('.') + 1);
			System.out.println("******************************************");
			System.out.println("Starting: " + className + "." + methodName);
		}

		@Override
		protected void finished(Description description) {
			super.finished(description);
			String methodName = description.getMethodName();
			String className = description.getClassName();
			className = className.substring(className.lastIndexOf('.') + 1);
			System.out.println("Finished: " + className + "." + methodName);
		}
	};

	@Override
	public Statement apply(Statement base, Description description) {
		return base;
	}
}
