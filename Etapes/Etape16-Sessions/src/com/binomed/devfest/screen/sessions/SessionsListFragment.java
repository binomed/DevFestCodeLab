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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.binomed.devfest.R;
import com.binomed.devfest.adapters.list.SessionsAdapter;
import com.binomed.devfest.model.SessionBean;
import com.binomed.devfest.screen.sessions.requests.SessionsJsonRequest;
import com.binomed.devfest.service.DevFestSpiceService;
import com.binomed.devfest.utils.DevFestCst;
import com.binomed.devfest.utils.activities.AbstractRoboSherlockListFragment;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * @author JefBinomed
 * 
 *         Basic sessions list fragment
 * 
 */
public class SessionsListFragment extends AbstractRoboSherlockListFragment implements OnItemClickListener {

	/*
	 * Robo Guice vars
	 */

	@InjectView(android.R.id.list)
	ListView list;
	@InjectView(android.R.id.empty)
	TextView emptyView;

	/*
	 * Static vars
	 */

	private static final String TAG = "SessionsListFragment";
	private static final String SESSION_KEY = "sessions";

	/*
	 * Instance vars
	 */
	SessionsAdapter adapter;
	private int typeUrl = -1;
	private SpiceManager spiceManager = new SpiceManager(DevFestSpiceService.class);

	public void setTypeUrl(int typeUrl) {
		this.typeUrl = typeUrl;
	}

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.fragment_list, container, false);
		return mainView;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.binomed.devfest.utils.activities.AbstractRoboSherlockListFragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// We do the stuff here and not in OnCreateView because robo guice only inject the members at this moment

		list.setEmptyView(emptyView);
		adapter = new SessionsAdapter(getActivity());
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminate(true);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminateVisibility(true);
		emptyView.setText(R.string.no_sessions);

		// Call RoboSpice service
		spiceManager.execute(new SessionsJsonRequest(typeUrl), SESSION_KEY + typeUrl, DurationInMillis.NEVER, new RequestListener<SessionBean[]>() {

			@Override
			public void onRequestFailure(SpiceException arg0) {
				Log.e(TAG, "Error calling services ", arg0);
				emptyView.setText(R.string.error_loading);
			}

			@Override
			public void onRequestSuccess(SessionBean[] result) {
				ArrayList<SessionBean> liste = new ArrayList<SessionBean>();
				for (SessionBean session : result) {
					liste.add(session);
				}
				Collections.sort(liste);
				adapter.setSessionList(liste);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (view instanceof SessionView) {
			// We gives to the sessionActivity the session we are looking for
			Intent intent = new Intent(getActivity(), SessionActivity.class);
			intent.putExtra(DevFestCst.EXTRA_INTENT_SESSION, ((SessionView) view).getSession());
			startActivity(intent);
		}
	}

}
