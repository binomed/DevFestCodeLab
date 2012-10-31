package com.binomed.devfest.screen.sessions.requests;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.binomed.devfest.model.SessionBean;
import com.binomed.devfest.utils.DevFestCst;
import com.octo.android.robospice.request.springandroid.RestContentRequest;

public class SessionsJsonRequest extends RestContentRequest<SessionBean[]> {

	private final int type;

	public SessionsJsonRequest(int type) {
		super(SessionBean[].class);
		this.type = type;
	}

	@Override
	public SessionBean[] loadDataFromNetwork() throws Exception {
		String url = null;
		Map<String, String> vars = new HashMap<String, String>();
		switch (type) {
		case DevFestCst.TYPE_ANDROID:
			vars.put("query", DevFestCst.QUERY_ANDROID);
			break;
		case DevFestCst.TYPE_CLOUD:
			vars.put("query", DevFestCst.QUERY_CLOUD);
			break;
		case DevFestCst.TYPE_WEB:
			vars.put("query", DevFestCst.QUERY_WEB);
			break;
		case DevFestCst.TYPE_CODELAB:
			vars.put("query", DevFestCst.QUERY_CODELAB);
			break;

		default:
			break;
		}
		url = DevFestCst.MONGO_URL_QUERY;
		Log.i("SessionsJsonRequest", "prepare to execute request : " + url);
		Log.i("SessionsJsonRequest", "prepare to execute request with params : " + vars.get("query"));
		SessionBean[] res = getRestTemplate().getForObject(url, SessionBean[].class, vars);
		Log.i("SessionsJsonRequest", "nb results found : " + res.length);
		return res;
	}

}
