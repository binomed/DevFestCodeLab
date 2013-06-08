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
package com.binomed.breizhcamp.adapters.pager;

import roboguice.RoboGuice;
import roboguice.inject.InjectResource;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.screen.sessions.SessionsListFragment;

/**
 * @author JefBinomed
 * 
 *         Session pageAdapter for viewPager
 * 
 */
public class SessionsPagerAdapter extends FragmentStatePagerAdapter {// implements TitleProvider {

	/*
	 * Static vars
	 */
	private static final String TAG = "SessionsPageAdapter";
	private int day;

	public void setDay(int day) {
		this.day = day;
	}

	/*
	 * Instances vars
	 */
	private final Context context;

	/*
	 * Roboguice vars
	 */
	@InjectResource(R.string.tab_web)
	String webMobileTabName;
	@InjectResource(R.string.tab_agilite)
	String agileTabName;
	@InjectResource(R.string.tab_cloud)
	String cloudTabName;
	@InjectResource(R.string.tab_devops)
	String devopsTabName;
	@InjectResource(R.string.tab_extreme)
	String extremeTabName;
	@InjectResource(R.string.tab_langages)
	String langagesTabName;
	@InjectResource(R.string.tab_decouverte)
	String decouverteTabName;
	@InjectResource(R.string.tab_tooling)
	String toolingTabName;
	@InjectResource(R.string.tab_keynote)
	String keynoteTabName;

	public SessionsPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
		RoboGuice.getInjector(context).injectMembers(this);
	}

	@Override
	public Fragment getItem(int index) {
		SessionsListFragment fragment = (SessionsListFragment) Fragment.instantiate(context, SessionsListFragment.class.getName());
		Log.i(TAG, "Instanciate Fragment : " + fragment);
		Log.i(TAG, "Instanciate Fragment for index : " + index);
		fragment.setDay(day);
		fragment.setTrack(index);
		return fragment;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public int getCount() {
		return day == 0 ? 5 : 6;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		if (day == 0) {
			switch (position) {
			case 0:
				return webMobileTabName;
			case 1:
				return cloudTabName;
			case 2:
				return agileTabName;
			case 3:
				return devopsTabName;
			default:
				return extremeTabName;
			}
		} else {
			switch (position) {
			case 0:
				return keynoteTabName;
			case 1:
				return cloudTabName;
			case 2:
				return langagesTabName;
			case 3:
				return decouverteTabName;
			case 4:
				return webMobileTabName;
			default:
				return toolingTabName;
			}

		}
	}

}
