package com.binomed.devfest.screen.infos;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.binomed.devfest.R;
import com.binomed.devfest.adapters.pager.InfosPagerAdapter;
import com.binomed.devfest.utils.DevFestRoboActivity;
import com.viewpagerindicator.TabPageIndicator;

public class InfosActivity extends DevFestRoboActivity {

	@InjectView(R.id.pager)
	ViewPager viewPager;

	@InjectView(R.id.indicator)
	TabPageIndicator pageIndicator;

	InfosPagerAdapter adapter;
	ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide);

		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		adapter = new InfosPagerAdapter(getSupportFragmentManager(), this);
		viewPager.setAdapter(adapter);
		pageIndicator.setViewPager(viewPager, 0);
	}

}
