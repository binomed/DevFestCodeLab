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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.adapters.list.SessionsAdapter;
import com.binomed.breizhcamp.utils.BreizhCampCst;
import com.binomed.breizhcamp.utils.activities.AbstractRoboSherlockListFragment;

import fr.ybonnel.breizhcamppdf.model.ProgrammeJsonBean;
import fr.ybonnel.breizhcamppdf.model.Talk;

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
	private static final String PROGRAMME_KEY = "programme";

	private static final Map<String, Integer> dureeOfTalks = new HashMap<String, Integer>() {
		{
			put("universite", 180);
			put("quickie", 15);
			put("hands-on", 180);
			put("tools in action", 30);
			put("conference", 60);
			put("lab", 60);
			put("biglab", 120);
			put("keynote", 30);
		}
	};

	/*
	 * Instance vars
	 */
	SherlockFragmentActivity activity;
	SessionsAdapter adapter;
	private int track = -1;
	private int day = -1;

	// private SpiceManager spiceManager = new SpiceManager(BreizhCampSpiceService.class);

	public void setDay(int day) {
		this.day = day;
	}

	public void setTrack(int track) {
		this.track = track;
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
		activity = getSherlockActivity();
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminate(true);
		((SherlockFragmentActivity) getActivity()).setProgressBarIndeterminateVisibility(true);
		emptyView.setText(R.string.no_sessions);

		// Call RoboSpice service
		RequestQueue queue = Volley.newRequestQueue(getActivity());
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, BreizhCampCst.URL_PROGRAMME, null, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				ObjectMapper mapper = new ObjectMapper();
				try {
					ProgrammeJsonBean result = mapper.readValue(response.toString(), ProgrammeJsonBean.class);

					ArrayList<Talk> liste = new ArrayList<Talk>();
					for (Talk session : result.getProgramme().getJours().get(day).getTracks().get(track).getTalks()) {
						if (session.getTime().length() < 5) {
							session.setTime("0" + session.getTime());
						}
						int duree = dureeOfTalks.get(session.getFormat());
						int hDebut = Integer.parseInt(session.getTime().split(":")[0]);
						int mDebut = Integer.parseInt(session.getTime().split(":")[1]);
						int hFin = hDebut;
						int mFin = mDebut + duree;
						while (mFin >= 60) {
							mFin = mFin - 60;
							hFin++;
						}
						session.setEndTime((hFin < 10 ? "0" : "") + hFin + ":" + (mFin < 10 ? "0" : "") + mFin);
						liste.add(session);
					}
					Collections.sort(liste);
					adapter.setSessionList(liste);
					adapter.notifyDataSetChanged();
					activity.setProgressBarIndeterminateVisibility(false);
				} catch (JsonParseException e) {
					Log.e(TAG, "ParseException", e);
				} catch (JsonMappingException e) {
					Log.e(TAG, "JsonMappingException", e);
				} catch (IOException e) {
					Log.e(TAG, "IOException", e);
				}

			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, "Volley", error);

			}
		});
		queue.add(request);

	}

	@Override
	public void onStart() {
		super.onStart();
		// spiceManager.start(getActivity());
	}

	@Override
	public void onStop() {
		// spiceManager.shouldStop();
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
			intent.putExtra(BreizhCampCst.EXTRA_INTENT_SESSION, ((SessionView) view).getSession());
			startActivity(intent);
		}
	}

}
