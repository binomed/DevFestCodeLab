/*
 * Copyright (C) 2012 Binomed (http://blog.binomed.fr)
 *
 * Licensed under the Eclipse Public License - v 1.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.eclipse.org/legal/epl-v10.html
 *
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC 
 * LICENSE ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM 
 * CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 */
package com.binomed.devfest.screen.sessions;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.binomed.devfest.R;
import com.binomed.devfest.adapters.pager.SessionsPagerAdapter;
import com.binomed.devfest.utils.activities.AbstractDevFestRoboActivity;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @author JefBinomed
 * 
 *         Simple Activity hosting a viewPager
 * 
 */
public class SessionsActivity extends AbstractDevFestRoboActivity {

	/*
	 * Robo Guice vars
	 */

	@InjectView(R.id.pager)
	ViewPager viewPager;

	@InjectView(R.id.indicator)
	TabPageIndicator pageIndicator;

	/*
	 * Instances vars
	 */
	SessionsPagerAdapter adapter;
	ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide);

		// We activate the action bar
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		// We set the content of viewPager
		adapter = new SessionsPagerAdapter(getSupportFragmentManager(), this);
		viewPager.setAdapter(adapter);
		pageIndicator.setViewPager(viewPager, 0);
	}

}
