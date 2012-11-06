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

import com.binomed.devfest.R;
import com.binomed.devfest.screen.infos.InfosFragment;
import com.binomed.devfest.screen.infos.PartnairesFragment;

/**
 * @author JefBinomed
 * 
 *         PageAdapter for the viewPager informations
 * 
 */
public class InfosPagerAdapter extends FragmentPagerAdapter {// implements TitleProvider {

	/*
	 * Static vars
	 */
	private static final String TAG = "InfosPageAdapter";
	private static final int NB_PAGES = 5;

	/*
	 * Instance vars
	 */

	private final Context context;

	/*
	 * RoboGuice vars
	 */
	@InjectResource(R.string.tab_place)
	String placeTabName;
	@InjectResource(R.string.tab_car)
	String carTabName;
	@InjectResource(R.string.tab_sleep)
	String sleepTabName;
	@InjectResource(R.string.tab_after_party)
	String afterPartyTabName;
	@InjectResource(R.string.tab_partenaires)
	String partenairesTabName;

	public InfosPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
		RoboGuice.getInjector(context).injectMembers(this);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
		case 1:
		case 2:
		case 3: {
			InfosFragment fragment = (InfosFragment) Fragment.instantiate(context, InfosFragment.class.getName());
			fragment.setType(index);
			return fragment;
		}
		default: {
			return Fragment.instantiate(context, PartnairesFragment.class.getName());
		}
		}
	}

	@Override
	public int getCount() {
		return NB_PAGES;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return placeTabName;
		case 1:
			return carTabName;
		case 2:
			return sleepTabName;
		case 3:
			return afterPartyTabName;
		default:
			return partenairesTabName;
		}
	}

}
