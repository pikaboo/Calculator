package com.lenabru.lenascalculator.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.lenabru.lenascalculator.R;
import com.lenabru.lenascalculator.lenascalculator.model.Operation;
import com.lenabru.lenascalculator.screen.ScreenRowFragment;

import java.util.List;

public class MainActivity
		extends FragmentActivity {

	private final static String FRAGMENT_KEY = "FRAGMENT_KEY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			Operation[] ops = {Operation.PLUS, Operation.MINUS, Operation.MULTIPLICATION, Operation.DIVISION};
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			for (Operation op : ops) {
				ScreenRowFragment screenRowFragment = ScreenRowFragment.withOperation(op);
				transaction.add(R.id.calc_screen, screenRowFragment);
			}
			transaction.commit();
		}
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		List<Fragment> fragments = getSupportFragmentManager().getFragments();

		for (int i = 0; i < fragments.size(); i++) {
			getSupportFragmentManager().putFragment(outState, FRAGMENT_KEY + i, fragments.get(i));
		}
	}
}
