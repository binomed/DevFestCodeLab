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
package com.binomed.devfest.screen.sessions;

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
import com.binomed.devfest.R;
import com.binomed.devfest.adapters.list.SpeakersAdapter;
import com.binomed.devfest.model.SessionBean;
import com.binomed.devfest.model.SpeakerBean;
import com.binomed.devfest.screen.sessions.requests.SessionSpeakersJsonRequest;
import com.binomed.devfest.service.DevFestSpiceService;
import com.binomed.devfest.utils.activities.AbstractRoboSherlockFragment;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

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
	private SessionBean session;
	private SpiceManager spiceManager = new SpiceManager(DevFestSpiceService.class);

	public void setSession(SessionBean session) {
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
		((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminate(true);
		((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(true);

		// Fill Informations
		if (session.getImgType() != null) {
			UrlImageViewHelper.setUrlDrawable(imgType, session.getImgType(), R.drawable.ic_launcher);
		}
		sessionTitle.setText(session.getTitle());
		if (session.getLang() != null) {
			lang.setText(session.getLang());
		} else {
			lang.setText("");
		}
		if (session.getLevel() != null) {
			level.setText(session.getLevel());
		} else {
			level.setVisibility(View.GONE);
		}
		if (session.getRoom() != null) {
			room.setText(session.getRoom());
		} else {
			room.setVisibility(View.GONE);
		}
		if (session.getStartTime() != null && session.getEndTime() != null) {
			hour.setText(session.getStartTime() + " - " + session.getEndTime());
		} else {
			hour.setVisibility(View.GONE);
		}
		if (session.getDesc() != null) {
			desc.setText(session.getDesc());
		} else {
			desc.setVisibility(View.GONE);
		}

		// We call the robospice service
		spiceManager.execute(new SessionSpeakersJsonRequest(session.getSpeaker())//
				, session.getTitle().trim() //
				, DurationInMillis.NEVER //
				, new RequestListener<SpeakerBean[]>() {

					@Override
					public void onRequestFailure(SpiceException arg0) {
						Log.e(TAG, "Error calling services ", arg0);
						emptyView.setText(R.string.error_loading);
					}

					@Override
					public void onRequestSuccess(SpeakerBean[] result) {
						ArrayList<SpeakerBean> liste = new ArrayList<SpeakerBean>();
						for (SpeakerBean speaker : result) {
							liste.add(speaker);
						}
						Collections.sort(liste);
						if (liste.size() == 0) {
							emptyView.setText(R.string.no_speaker);
						}
						adapter.setSpeakersList(liste);
						adapter.notifyDataSetChanged();
						((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(false);

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
