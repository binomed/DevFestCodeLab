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
import com.binomed.devfest.utils.RoboSherlockListFragment;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class SessionsListFragment extends RoboSherlockListFragment implements OnItemClickListener {

	@InjectView(android.R.id.list)
	ListView list;
	@InjectView(android.R.id.empty)
	TextView emptyView;

	SessionsAdapter adapter;

	private static final String TAG = "SessionsListFragment";

	private int typeUrl = -1;

	public void setTypeUrl(int typeUrl) {
		this.typeUrl = typeUrl;
	}

	private static final String SESSION_KEY = "sessions";
	private SpiceManager spiceManager = new SpiceManager(DevFestSpiceService.class);

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.fragment_list, container, false);
		return mainView;

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		list.setEmptyView(emptyView);
		adapter = new SessionsAdapter(getActivity());
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminate(true);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminateVisibility(true);

		Log.i(TAG, "Call Service : " + this);
		Log.i(TAG, "Call Service : " + SESSION_KEY + typeUrl);
		emptyView.setText(R.string.no_sessions);
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
				Log.i(TAG, "Recieve List : " + liste.size());
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (view instanceof SessionView) {
			Intent intent = new Intent(getActivity(), SessionActivity.class);
			intent.putExtra(DevFestCst.EXTRA_INTENT_SESSION, ((SessionView) view).getSession());
			startActivity(intent);
		}
	}

}
