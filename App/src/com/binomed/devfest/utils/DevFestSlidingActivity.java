package com.binomed.devfest.utils;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.binomed.devfest.R;
import com.binomed.devfest.screen.HomeFragment;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public abstract class DevFestSlidingActivity extends SlidingFragmentActivity {

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

	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			// final Intent intent = new Intent(this, DontForgetMomActivity.class);
			// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// startActivity(intent);
			return true;
		case ITEM_ABOUT:
			Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();

		default:
			return super.onMenuItemSelected(featureId, item);
		}

	}

	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, ITEM_ABOUT, 1, R.string.action_about) //
				.setIcon(R.drawable.ic_action_about) //
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return true;
	}
}
