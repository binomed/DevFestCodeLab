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
package com.binomed.breizhcamp.screen.speakers.requests;

import com.binomed.breizhcamp.utils.BreizhCampCst;
import com.octo.android.robospice.request.springandroid.RestContentRequest;

import fr.ybonnel.breizhcamppdf.model.Speaker;

/**
 * @author JefBinomed
 * 
 *         RoboSpice request
 * 
 */
public class SpeakersJsonRequest extends RestContentRequest<Speaker[]> {

	public SpeakersJsonRequest() {
		super(Speaker[].class);
	}

	@Override
	public Speaker[] loadDataFromNetwork() throws Exception {
		return getRestTemplate().getForObject(BreizhCampCst.URL_SPEAKERS, Speaker[].class);
	}

}
