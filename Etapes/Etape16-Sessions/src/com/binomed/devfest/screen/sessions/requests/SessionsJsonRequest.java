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
package com.binomed.devfest.screen.sessions.requests;

import java.util.HashMap;
import java.util.Map;

import com.binomed.devfest.model.SessionBean;
import com.binomed.devfest.utils.DevFestCst;
import com.octo.android.robospice.request.springandroid.RestContentRequest;

/**
 * @author JefBinomed
 * 
 *         Sessions robospice request
 * 
 */
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
		// According to type of request we requesting the right url
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
		SessionBean[] res = getRestTemplate().getForObject(url, SessionBean[].class, vars);
		return res;
	}

}
