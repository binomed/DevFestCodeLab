package com.binomed.devfest.screen.sessions;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.binomed.devfest.R;
import com.binomed.devfest.model.SessionBean;
import com.binomed.devfest.utils.DevFestCst;
import com.binomed.devfest.utils.DevFestRoboActivity;

public class SessionActivity extends DevFestRoboActivity {

	ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		setContentView(R.layout.activity_session);
		SessionFragment fragment = new SessionFragment();
		fragment.setSession((SessionBean) getIntent().getExtras().getParcelable(DevFestCst.EXTRA_INTENT_SESSION));
		getSupportFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
	}

}
