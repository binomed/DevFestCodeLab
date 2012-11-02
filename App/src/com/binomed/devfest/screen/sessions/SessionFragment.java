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
import com.binomed.devfest.utils.RoboSherlockFragment;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class SessionFragment extends RoboSherlockFragment {

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

	private SpeakersAdapter adapter;
	private static final String TAG = "SessionFragment";
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

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		list.setEmptyView(emptyView);
		adapter = new SpeakersAdapter(getActivity(), false);
		list.setAdapter(adapter);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminate(true);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminateVisibility(true);

		// Fill Informations
		if (session.getImgType() != null) {
			UrlImageViewHelper.setUrlDrawable(imgType, session.getImgType(), android.R.drawable.spinner_background);
		} else {
			imgType.setVisibility(View.GONE);
		}
		sessionTitle.setText(session.getTitle());
		if (session.getLang() != null) {
			lang.setText(session.getLang());
		} else {
			lang.setVisibility(View.GONE);
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

		spiceManager.execute(new SessionSpeakersJsonRequest(session.getSpeaker())//
				, session.getTitle().trim() //
				, DurationInMillis.NEVER //
				, new RequestListener<SpeakerBean[]>() {

					@Override
					public void onRequestFailure(SpiceException arg0) {
						Log.e(TAG, "Error calling services ", arg0);
					}

					@Override
					public void onRequestSuccess(SpeakerBean[] result) {
						ArrayList<SpeakerBean> liste = new ArrayList<SpeakerBean>();
						for (SpeakerBean speaker : result) {
							liste.add(speaker);
						}
						Collections.sort(liste);
						Log.i(TAG, "Recieve List : " + liste.size());
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
