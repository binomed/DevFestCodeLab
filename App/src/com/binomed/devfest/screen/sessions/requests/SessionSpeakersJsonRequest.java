package com.binomed.devfest.screen.sessions.requests;

import java.util.HashMap;
import java.util.Map;

import com.binomed.devfest.model.SpeakerBean;
import com.binomed.devfest.utils.DevFestCst;
import com.octo.android.robospice.request.springandroid.RestContentRequest;

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

			} else {
				vars.put("query", idQuery + idSpeakers[0] + "\"}}");
			}
			return getRestTemplate().getForObject(DevFestCst.MONGO_URL_SESSION_SPEAKERS, SpeakerBean[].class, vars);
		} else {
			return new SpeakerBean[0];
		}
	}
}
