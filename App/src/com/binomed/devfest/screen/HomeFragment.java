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
import com.binomed.devfest.screen.infos.InfosActivity;
import com.binomed.devfest.screen.sessions.SessionsActivity;
import com.binomed.devfest.screen.speakers.SpeakersActivity;
import com.binomed.devfest.utils.RoboSherlockFragment;
import com.binomed.devfest.utils.SherlockFragmentSlidingActivity;

public class HomeFragment extends RoboSherlockFragment implements OnClickListener {

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
		((SherlockFragmentSlidingActivity) getActivity()).toggle();
		int id = v.getId();
		switch (id) {
		case R.id.home_btn_speakers: {
			Intent intent = new Intent(getActivity(), SpeakersActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		}
		case R.id.home_btn_infos: {
			Intent intent = new Intent(getActivity(), InfosActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		}
		case R.id.home_btn_sessions: {
			Intent intent = new Intent(getActivity(), SessionsActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		}
		default:
			break;
		}
	}
}