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
package com.binomed.breizhcamp.screen.sessions;

import java.util.ArrayList;
import java.util.Collections;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.adapters.list.SpeakersAdapter;
import com.binomed.breizhcamp.screen.sessions.requests.SessionJsonRequest;
import com.binomed.breizhcamp.service.BreizhCampSpiceService;
import com.binomed.breizhcamp.utils.activities.AbstractRoboSherlockFragment;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import fr.ybonnel.breizhcamppdf.model.Speaker;
import fr.ybonnel.breizhcamppdf.model.Talk;
import fr.ybonnel.breizhcamppdf.model.TalkDetail;

/**
 * @author JefBinomed
 * 
 *         The fragment for a session
 * 
 */
public class SessionFragment extends AbstractRoboSherlockFragment {

	/*
	 * Robo Guice vars
	 */

	@InjectView(R.id.listSpeaker)
	ListView list;
	@InjectView(android.R.id.empty)
	TextView emptyView;
	@InjectView(R.id.sessionTitle)
	TextView sessionTitle;
	@InjectView(R.id.hour)
	TextView hour;
	@InjectView(R.id.level)
	TextView level;
	@InjectView(R.id.lang)
	TextView lang;
	@InjectView(R.id.room)
	TextView room;
	@InjectView(R.id.desc)
	TextView desc;
	@InjectView(R.id.imgType)
	ImageView imgType;

	/*
	 * Static vars
	 */
	private static final String TAG = "SessionFragment";

	/*
	 * Instance vars
	 */

	private SpeakersAdapter adapter;
	private Talk session;
	private TalkDetail sessionDetail;
	private SpiceManager spiceManager = new SpiceManager(BreizhCampSpiceService.class);

	public void setSession(Talk session) {
		this.session = session;
	}

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.fragment_session, container, false);
		return mainView;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.binomed.devfest.utils.activities.AbstractRoboSherlockFragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// We do all stuf here because roboguice only injects views here

		list.setEmptyView(emptyView);
		adapter = new SpeakersAdapter(getActivity(), false);
		list.setAdapter(adapter);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminate(true);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminateVisibility(true);

		// Fill Informations
		// if (session.getImgType() != null) {
		// UrlImageViewHelper.setUrlDrawable(imgType, session.getImgType(), R.drawable.ic_launcher);
		// }
		sessionTitle.setText(session.getTitle());
		// if (session.getLang() != null) {
		// lang.setText(session.getLang());
		// } else {
		lang.setText("");
		// }
		// if (session.getLevel() != null) {
		// level.setText(session.getLevel());
		// } else {
		level.setVisibility(View.GONE);
		// }
		if (session.getRoom() != null) {
			room.setText(session.getRoom());
		} else {
			room.setVisibility(View.GONE);
		}
		if (session.getTime() != null && session.getEndTime() != null) {
			hour.setText(session.getTime() + " - " + session.getEndTime());
		} else {
			hour.setVisibility(View.GONE);
		}

		spiceManager.execute(new SessionJsonRequest(session.getId()) //
				, session.getTitle().trim() //
				, DurationInMillis.NEVER //
				, new RequestListener<TalkDetail>() {

					@Override
					public void onRequestSuccess(TalkDetail detail) {
						if (detail.getDescription() != null) {
							desc.setText(detail.getDescription());
						} else {
							desc.setVisibility(View.GONE);
						}

						ArrayList<Speaker> liste = new ArrayList<Speaker>();
						for (Speaker speaker : detail.getSpeakers()) {
							liste.add(speaker);
						}
						Collections.sort(liste);
						if (liste.size() == 0) {
							emptyView.setText(R.string.no_speaker);
						}
						adapter.setSpeakersList(liste);
						adapter.notifyDataSetChanged();
						((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminateVisibility(false);

					}

					@Override
					public void onRequestFailure(SpiceException arg0) {
						Log.e(TAG, "Error calling services ", arg0);
						emptyView.setText(R.string.error_loading);

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
