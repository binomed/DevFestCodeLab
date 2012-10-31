package com.binomed.devfest.screen.speakers.requests;

import com.binomed.devfest.model.SpeakerBean;
import com.binomed.devfest.utils.DevFestCst;
import com.octo.android.robospice.request.springandroid.RestContentRequest;

public class SpeakersJsonRequest extends RestContentRequest<SpeakerBean[]> {

	public SpeakersJsonRequest() {
		super(SpeakerBean[].class);
	}

	@Override
	public SpeakerBean[] loadDataFromNetwork() throws Exception {
		return getRestTemplate().getForObject(DevFestCst.MONGO_URL_SPEAKERS, SpeakerBean[].class);
	}

}
