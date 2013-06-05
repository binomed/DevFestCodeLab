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
package com.binomed.breizhcamp.utils.activities;

import android.os.Bundle;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.screen.HomeFragment;
import com.binomed.breizhcamp.screen.about.AboutDialogFragment;
import com.slidingmenu.lib.SlidingMenu;

public abstract class AbstractDevFestSlidingActivity extends AbstractSherlockFragmentSlidingActivity {

	private static final int ITEM_ABOUT = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBehindContentView(R.layout.view_empty_slide);
		getSupportFragmentManager().beginTransaction().add(R.id.emptySlide, new HomeFragment()).commit();
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.actionbar_home_width);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);

		setSlidingActionBarEnabled(true);

	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			// Default actions
			toggle();
			return true;
		case ITEM_ABOUT:
			AboutDialogFragment aboutFragment = new AboutDialogFragment();
			aboutFragment.show(getSupportFragmentManager(), "ABOUT");

		default:
			return super.onMenuItemSelected(featureId, item);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Basic menu about
		menu.add(0, ITEM_ABOUT, 1, R.string.action_about) //
				.setIcon(R.drawable.ic_action_about) //
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return true;
	}
}
