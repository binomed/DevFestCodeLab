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
package com.binomed.devfest.screen.speakers.requests;

import com.binomed.devfest.model.SpeakerBean;
import com.binomed.devfest.utils.DevFestCst;
import com.octo.android.robospice.request.springandroid.RestContentRequest;

/**
 * @author JefBinomed
 * 
 *         RoboSpice request
 * 
 */
public class SpeakersJsonRequest extends RestContentRequest<SpeakerBean[]> {

	public SpeakersJsonRequest() {
		super(SpeakerBean[].class);
	}

	@Override
	public SpeakerBean[] loadDataFromNetwork() throws Exception {
		return getRestTemplate().getForObject(DevFestCst.MONGO_URL_SPEAKERS, SpeakerBean[].class);
	}

}
