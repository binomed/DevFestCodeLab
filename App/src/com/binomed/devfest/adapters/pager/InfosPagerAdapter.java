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

public class InfosPagerAdapter extends FragmentPagerAdapter {// implements TitleProvider {

	private static final String TAG = "InfosPageAdapter";

	private static final int NB_PAGES = 5;

	private final Context context;
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
