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
package com.binomed.breizhcamp.screen.sessions;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.adapters.pager.SessionsPagerAdapter;
import com.binomed.breizhcamp.utils.activities.AbstractRoboSherlockFragment;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @author JefBinomed
 * 
 *         Simple Activity hosting a viewPager
 * 
 */
public class SessionsActivityFragment extends AbstractRoboSherlockFragment {

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
		actionBar = getSherlockActivity().getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		final String[] dropdownvalues = getResources().getStringArray(R.array.days);
		ArrayAdapter<String> adapterActionBar = new ArrayAdapter<String>(actionBar.getThemedContext(), android.R.layout.simple_spinner_item, android.R.id.text1, dropdownvalues);
		adapterActionBar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		actionBar.setListNavigationCallbacks(adapterActionBar, new ActionBar.OnNavigationListener() {

			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId) {
				// We set the content of viewPager
				adapter.setDay(itemPosition);
				adapter.notifyDataSetChanged();
				viewPager.invalidate();
				pageIndicator.setCurrentItem(0);
				pageIndicator.notifyDataSetChanged();
				return true;
			}
		});

		// We set the content of viewPager
		adapter = new SessionsPagerAdapter(getActivity().getSupportFragmentManager(), getActivity());
		adapter.setDay(0);
		viewPager.setAdapter(adapter);
		pageIndicator.setViewPager(viewPager, 0);
	}

}
