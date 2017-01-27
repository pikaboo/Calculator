package com.lenabru.lenascalculator.screen;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import rx.Subscription;


/**
 * Created by Lena Brusilovski on 26-Jan 2017.
 */

public class ValueViewHolder {

	private static final String TAG = ValueViewHolder.class.getSimpleName();

	public EditText value;

	public Subscription textChangesSubscription;

	public ValueViewHolder(View layout, int resId) {
		this.value = (EditText) layout.findViewById(resId);
	}

	public void unsubscribe() {
		if (textChangesSubscription != null) {
			Log.d(TAG,"unsubscribed");
			this.textChangesSubscription.unsubscribe();
		}
	}
}
