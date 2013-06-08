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
package com.binomed.breizhcamp.screen.sessions.requests;

import java.util.HashMap;
import java.util.Map;

import com.binomed.breizhcamp.model.SessionBean;
import com.binomed.breizhcamp.utils.BreizhCampCst;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * @author JefBinomed
 * 
 *         Sessions robospice request
 * 
 */
public class SessionsJsonRequest extends SpringAndroidSpiceRequest<SessionBean[]> {

	private final int type;
	private final int day;

	public SessionsJsonRequest(int type, int day) {
		super(SessionBean[].class);
		this.type = type;
		this.day = day;
	}

	@Override
	public SessionBean[] loadDataFromNetwork() throws Exception {
		String url = null;
		Map<String, String> vars = new HashMap<String, String>();
		// According to type of request we requesting the right url
		switch (type) {
		case BreizhCampCst.TYPE_ANDROID:
			vars.put("query", BreizhCampCst.QUERY_ANDROID);
			break;
		case BreizhCampCst.TYPE_CLOUD:
			vars.put("query", BreizhCampCst.QUERY_CLOUD);
			break;
		case BreizhCampCst.TYPE_WEB:
			vars.put("query", BreizhCampCst.QUERY_WEB);
			break;
		case BreizhCampCst.TYPE_CODELAB:
			vars.put("query", BreizhCampCst.QUERY_CODELAB);
			break;

		default:
			break;
		}
		url = BreizhCampCst.MONGO_URL_QUERY;
		SessionBean[] res = getRestTemplate().getForObject(url, SessionBean[].class, vars);

		return res;
	}

}
