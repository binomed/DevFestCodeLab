package com.binomed.devfest.adapters.pager;

import roboguice.RoboGuice;
import roboguice.inject.InjectResource;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.binomed.devfest.R;
import com.binomed.devfest.screen.sessions.SessionsListFragment;

public class SessionsPagerAdapter extends FragmentPagerAdapter {// implements TitleProvider {

	private static final String TAG = "SessionsPageAdapter";

	private static final int NB_PAGES = 4;

	private final Context context;
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
		Fragment fragment = Fragment.instantiate(context, SessionsListFragment.class.getName());
		switch (index) {
		case 0: {
			// TODO
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
