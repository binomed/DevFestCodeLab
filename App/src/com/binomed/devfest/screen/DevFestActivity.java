package com.binomed.devfest.screen;

import android.app.Activity;
import android.os.Bundle;

public class DevFestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Test
		if (true) {
			// startActivity(new Intent(getApplicationContext(), TripsActivity.class));
			finish();
		}
	}

}