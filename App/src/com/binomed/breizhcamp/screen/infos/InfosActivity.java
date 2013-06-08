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
package com.binomed.breizhcamp.screen.infos;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.adapters.pager.InfosPagerAdapter;
import com.binomed.breizhcamp.utils.activities.AbstractDevFestRoboActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.MapsInitializer;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @author JefBinomed
 * 
 *         Basic activity that will host the viewPager of informations
 * 
 */
public class InfosActivity extends AbstractDevFestRoboActivity {

	/*
	 * Robo guice vars
	 */

	@InjectView(R.id.pager)
	ViewPager viewPager;

	@InjectView(R.id.indicator)
	TabPageIndicator pageIndicator;

	/*
	 * Instance vars
	 */

	InfosPagerAdapter adapter;
	ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide);
		try {
			MapsInitializer.initialize(this);
		} catch (GooglePlayServicesNotAvailableException e) {
			Log.e("InfosActivity", "error maps service not available", e);
		}
		Log.i("InfosActivity", "Service available : " + GooglePlayServicesUtil.isGooglePlayServicesAvailable(this));

		// We add the action bar
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		// We fill the view pager
		adapter = new InfosPagerAdapter(getSupportFragmentManager(), this);
		viewPager.setAdapter(adapter);
		pageIndicator.setViewPager(viewPager, 0);
	}

}
