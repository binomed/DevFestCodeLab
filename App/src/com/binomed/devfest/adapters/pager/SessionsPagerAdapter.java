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
package com.binomed.devfest.adapters.pager;

import roboguice.RoboGuice;
import roboguice.inject.InjectResource;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.binomed.devfest.R;
import com.binomed.devfest.screen.sessions.SessionsListFragment;
import com.binomed.devfest.utils.DevFestCst;

/**
 * @author JefBinomed
 * 
 *         Session pageAdapter for viewPager
 * 
 */
public class SessionsPagerAdapter extends FragmentPagerAdapter {// implements TitleProvider {

	/*
	 * Static vars
	 */
	private static final String TAG = "SessionsPageAdapter";
	private static final int NB_PAGES = 4;

	/*
	 * Instances vars
	 */
	private final Context context;

	/*
	 * Roboguice vars
	 */
	@InjectResource(R.string.tab_android)
	String androidTabName;
	@InjectResource(R.string.tab_web)
	String webTabName;
	@InjectResource(R.string.tab_cloud)
	String cloudTabName;
	@InjectResource(R.string.tab_codelab)
	String codeLabTabName;

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
		switch (index) {
		case 0: {
			fragment.setTypeUrl(DevFestCst.TYPE_ANDROID);
			break;
		}
		case 1: {
			fragment.setTypeUrl(DevFestCst.TYPE_WEB);
			break;
		}
		case 2: {
			fragment.setTypeUrl(DevFestCst.TYPE_CLOUD);
			break;
		}
		case 3: {
			fragment.setTypeUrl(DevFestCst.TYPE_CODELAB);
			break;
		}
		default: {
		}
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return NB_PAGES;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return androidTabName;
		case 1:
			return webTabName;
		case 2:
			return cloudTabName;
		default:
			return codeLabTabName;
		}
	}

}
