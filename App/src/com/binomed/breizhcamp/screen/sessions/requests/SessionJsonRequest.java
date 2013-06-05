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

import com.binomed.breizhcamp.utils.BreizhCampCst;
import com.octo.android.robospice.request.springandroid.RestContentRequest;

import fr.ybonnel.breizhcamppdf.model.TalkDetail;

/**
 * @author JefBinomed
 * 
 *         Speakers of session robo spice request
 * 
 */
public class SessionJsonRequest extends RestContentRequest<TalkDetail> {

	private final String id;

	public SessionJsonRequest(String id) {
		super(TalkDetail.class);
		this.id = id;
	}

	@Override
	public TalkDetail loadDataFromNetwork() throws Exception {
		String url = null;
		Map<String, String> vars = new HashMap<String, String>();
		// According to type of request we requesting the right url
		vars.put("talkId", id);
		url = BreizhCampCst.URL_TALK_DETAIL;
		TalkDetail res = getRestTemplate().getForObject(url, TalkDetail.class, vars);

		return res;
	}
}
