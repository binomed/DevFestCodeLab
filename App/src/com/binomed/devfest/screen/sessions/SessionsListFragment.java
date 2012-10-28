package com.binomed.devfest.screen.sessions;

import java.net.URI;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import roboguice.inject.InjectView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.binomed.devfest.R;
import com.binomed.devfest.adapters.list.SessionsAdapter;
import com.binomed.devfest.model.SessionBean;
import com.binomed.devfest.utils.DevFestCst;
import com.binomed.devfest.utils.RoboSherlockListFragment;

public class SessionsListFragment extends RoboSherlockListFragment {

	@InjectView(android.R.id.list)
	ListView list;
	@InjectView(android.R.id.empty)
	TextView emptyView;

	SessionsAdapter adapter;

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
		adapter = new SessionsAdapter();

		AsyncTask<Void, Void, SessionBean[]> async = new AsyncTask<Void, Void, SessionBean[]>() {

			@Override
			protected SessionBean[] doInBackground(Void... params) {
				try {
					RestTemplate restTemplate = new RestTemplate();
					restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
					String url = DevFestCst.MONGO_URL_SESSIONS;
					return restTemplate.getForObject(new URI(url), SessionBean[].class);
				} catch (Exception e) {
					Log.e("SessionsListFragment", e.getMessage(), e);
				}
				return null;
			}

			@Override
			protected void onPostExecute(SessionBean[] result) {
				adapter.setSessionList(result);
				adapter.notifyDataSetChanged();
			}

		};
		async.execute();
	}

}
