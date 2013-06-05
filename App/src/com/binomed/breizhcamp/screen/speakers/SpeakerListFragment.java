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
package com.binomed.breizhcamp.screen.speakers;

import java.util.ArrayList;
import java.util.Collections;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.adapters.list.SpeakersAdapter;
import com.binomed.breizhcamp.screen.speakers.requests.SpeakersJsonRequest;
import com.binomed.breizhcamp.service.BreizhCampSpiceService;
import com.binomed.breizhcamp.utils.activities.AbstractRoboSherlockListFragment;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import fr.ybonnel.breizhcamppdf.model.Speaker;

/**
 * @author JefBinomed Simple List of speakers
 */
public class SpeakerListFragment extends AbstractRoboSherlockListFragment {

	/*
	 * Robo Guice Bindings
	 */

	@InjectView(android.R.id.list)
	ListView list;
	@InjectView(android.R.id.empty)
	TextView emptyView;

	/*
	 * Static vars
	 */

	private static final String TAG = "SpeakerListFragment";
	private static final String SPEAKER_KEY = "sessions";

	/*
	 * Instance vars
	 */

	private SpiceManager spiceManager = new SpiceManager(BreizhCampSpiceService.class);
	private SpeakersAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.fragment_list, container, false);
		return mainView;

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// We do all stuff when the views are created because they are only inject by robo guice at this moment
		list.setEmptyView(emptyView);

		adapter = new SpeakersAdapter(getActivity(), true);
		list.setAdapter(adapter);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminate(true);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminateVisibility(true);
		emptyView.setText(R.string.no_sessions);

		// Execute RoboSpice service
		spiceManager.execute(new SpeakersJsonRequest(), SPEAKER_KEY, DurationInMillis.NEVER, new RequestListener<Speaker[]>() {

			@Override
			public void onRequestFailure(SpiceException arg0) {
				Log.e(TAG, "Error calling services ", arg0);
				emptyView.setText(R.string.error_loading);
			}

			@Override
			public void onRequestSuccess(Speaker[] result) {
				ArrayList<Speaker> liste = new ArrayList<Speaker>();
				for (Speaker session : result) {
					liste.add(session);
				}
				Collections.sort(liste);
				adapter.setSpeakersList(liste);
				adapter.notifyDataSetChanged();
				((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminateVisibility(false);

			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
		spiceManager.start(getActivity());
	}

	@Override
	public void onStop() {
		spiceManager.shouldStop();
		super.onStop();
	}
}
