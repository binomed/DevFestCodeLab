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

	private static final int NB_PAGES = 2;

	private final Context context;
	@InjectResource(R.string.tab_infos)
	String infosTabName;
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
		case 0: {
			return Fragment.instantiate(context, InfosFragment.class.getName());
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
			return infosTabName;
		default:
			return partenairesTabName;
		}
	}

}
