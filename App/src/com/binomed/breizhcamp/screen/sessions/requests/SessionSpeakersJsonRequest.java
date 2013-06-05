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

import com.binomed.breizhcamp.model.SpeakerBean;
import com.binomed.breizhcamp.utils.BreizhCampCst;
import com.octo.android.robospice.request.springandroid.RestContentRequest;

/**
 * @author JefBinomed
 * 
 *         Speakers of session robo spice request
 * 
 */
public class SessionSpeakersJsonRequest extends RestContentRequest<SpeakerBean[]> {

	private final String[] idSpeakers;

	private final String idQuery = "{\"_id\":{\"$oid\":\"";

	public SessionSpeakersJsonRequest(String[] idSpeakers) {
		super(SpeakerBean[].class);
		this.idSpeakers = idSpeakers;
	}

	@Override
	public SpeakerBean[] loadDataFromNetwork() throws Exception {
		Map<String, String> vars = new HashMap<String, String>();
		StringBuilder query = new StringBuilder();
		// According to the number of speakers, we have to construct the right query string
		if (idSpeakers != null) {
			if (idSpeakers.length > 1) {
				int compt = idSpeakers.length;
				int index = 0;
				int nbOr = 1;
				while (compt > 1) {
					query.append("{$or:[").append(idQuery).append(idSpeakers[index]).append("\"}}").append(",");
					compt--;
					index++;
				}
				query.append(idQuery).append(idSpeakers[index]).append("\"}}");
				for (int i = 0; i < nbOr; i++) {
					query.append("]}");
				}

				vars.put("query", query.toString());
			} else {
				vars.put("query", idQuery + idSpeakers[0] + "\"}}");
			}
			return getRestTemplate().getForObject(BreizhCampCst.MONGO_URL_SESSION_SPEAKERS, SpeakerBean[].class, vars);
		} else {
			return new SpeakerBean[0];
		}
	}
}
