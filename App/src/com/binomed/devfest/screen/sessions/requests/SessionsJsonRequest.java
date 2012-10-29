package com.binomed.devfest.screen.sessions.requests;

import android.util.Log;

import com.binomed.devfest.model.SessionBean;
import com.binomed.devfest.utils.DevFestCst;
import com.octo.android.robospice.request.springandroid.RestContentRequest;

public class SessionsJsonRequest extends RestContentRequest<SessionBean[]> {

	public SessionsJsonRequest() {
		super(SessionBean[].class);
	}

	@Override
	public SessionBean[] loadDataFromNetwork() throws Exception {
		Log.i("SessionsJsonRequest", "prepare to execute request : " + DevFestCst.MONGO_URL_SESSIONS);
		SessionBean[] res = getRestTemplate().getForObject(DevFestCst.MONGO_URL_SESSIONS, SessionBean[].class);
		Log.i("SessionsJsonRequest", "nb results found : " + res.length);
		return res;
	}

}
