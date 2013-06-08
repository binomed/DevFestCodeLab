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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.adapters.pager.InfosPagerAdapter;
import com.binomed.breizhcamp.utils.activities.AbstractRoboSherlockFragment;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @author JefBinomed
 * 
 *         Basic activity that will host the viewPager of informations
 * 
 */
public class InfosFragmentActivity extends AbstractRoboSherlockFragment {

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
	InfosPagerAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.activity_slide, container, false);
		return mainView;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.binomed.devfest.utils.activities.AbstractRoboSherlockFragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// We activate the action bar
		getSherlockActivity().getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

		// We fill the view pager
		adapter = new InfosPagerAdapter(getSherlockActivity().getSupportFragmentManager(), getActivity());
		viewPager.setAdapter(adapter);
		pageIndicator.setViewPager(viewPager, 0);
	}

}
