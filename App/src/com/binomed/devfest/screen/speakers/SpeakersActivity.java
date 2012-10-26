package com.binomed.devfest.screen.speakers;

import android.os.Bundle;

import com.binomed.devfest.R;
import com.binomed.devfest.utils.DevFestRoboActivity;

public class SpeakersActivity extends DevFestRoboActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_speakers);
		getSupportFragmentManager().beginTransaction().add(R.id.content, new SpeakerListFragment()).commit();
	}

}
