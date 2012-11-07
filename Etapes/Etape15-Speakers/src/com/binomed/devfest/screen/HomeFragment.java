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
package com.binomed.devfest.screen;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.binomed.devfest.R;
//import com.binomed.devfest.screen.infos.InfosActivity;
//import com.binomed.devfest.screen.sessions.SessionsActivity;
import com.binomed.devfest.screen.speakers.SpeakersActivity;
import com.binomed.devfest.utils.activities.AbstractRoboSherlockFragment;
import com.binomed.devfest.utils.activities.AbstractSherlockFragmentSlidingActivity;

/**
 * @author JefBinomed
 * 
 *         Fragment for sliding menu
 * 
 */
public class HomeFragment extends AbstractRoboSherlockFragment implements OnClickListener {

	@InjectView(R.id.home_btn_speakers)
	Button speakersBtn;
	@InjectView(R.id.home_btn_infos)
	Button infosBtn;
	@InjectView(R.id.home_btn_sessions)
	Button sessionsBtn;

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// We add manualy the listener because the onclik bind offers by android refers to activity and not fragment...
		speakersBtn.setOnClickListener(this);
		infosBtn.setOnClickListener(this);
		sessionsBtn.setOnClickListener(this);

	}

	/**
	 * Handle the click of a Feature button.
	 * 
	 * @param v
	 *            View
	 * @return void
	 */

	@Override
	public void onClick(View v) {
		((AbstractSherlockFragmentSlidingActivity) getActivity()).toggle();
		int id = v.getId();
		switch (id) {
		case R.id.home_btn_speakers: {
			Intent intent = new Intent(getActivity(), SpeakersActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		}
		case R.id.home_btn_infos: {
//			Intent intent = new Intent(getActivity(), InfosActivity.class);
//			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//			startActivity(intent);
			break;
		}
		case R.id.home_btn_sessions: {
//			Intent intent = new Intent(getActivity(), SessionsActivity.class);
//			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//			startActivity(intent);
			break;
		}
		default:
			break;
		}
	}
}