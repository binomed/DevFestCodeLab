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
			vars.put("query", "{type:\"Android\"}");
			url = DevFestCst.MONGO_URL_SESSIONS_ANDROID;
			break;
		case DevFestCst.TYPE_CLOUD:
			vars.put("query", "{type:\"Cloud\"}");
			url = DevFestCst.MONGO_URL_SESSIONS_CLOUD;

			break;
		case DevFestCst.TYPE_WEB:
			vars.put("query", "{type:\"Web\"}");
			url = DevFestCst.MONGO_URL_SESSIONS_WEB;

			break;
		case DevFestCst.TYPE_CODELAB:
			vars.put("query", "{type:\"CodeLab\"}");
			url = DevFestCst.MONGO_URL_SESSIONS_CODELAB;

			break;

		default:
			break;
		}
		Log.i("SessionsJsonRequest", "prepare to execute request : " + url);
		url = DevFestCst.MONGO_URL_QUERY;
		SessionBean[] res = getRestTemplate().getForObject(url, SessionBean[].class, vars);
		Log.i("SessionsJsonRequest", "nb results found : " + res.length);
		return res;
	}

}
