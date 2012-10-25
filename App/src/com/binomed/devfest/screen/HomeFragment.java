package com.binomed.devfest.screen;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.binomed.devfest.R;
import com.binomed.devfest.utils.RoboSherlockFragment;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeFragment extends RoboSherlockFragment implements OnClickListener {

	@InjectView(R.id.home_btn_speakers)
	Button speakersBtn;
	@InjectView(R.id.home_btn_agenda)
	Button agendaBtn;
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
		agendaBtn.setOnClickListener(this);
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
		((SlidingFragmentActivity) getActivity()).toggle();
		int id = v.getId();
		switch (id) {
		case R.id.home_btn_speakers: {
			// Intent intent = new Intent(getActivity(), TripsActivity.class);
			// intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			// startActivity(intent);
			Toast.makeText(getActivity(), "Speakers", Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.home_btn_agenda:
			// Intent intent = new Intent(getActivity(), EditionActivity.class);
			// intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			// startActivity(intent);
			Toast.makeText(getActivity(), "Agenda", Toast.LENGTH_SHORT).show();
			break;
		case R.id.home_btn_sessions:
			// TODO
			Toast.makeText(getActivity(), "Sessions", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}