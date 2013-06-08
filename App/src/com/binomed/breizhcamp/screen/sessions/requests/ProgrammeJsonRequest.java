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

import com.binomed.breizhcamp.utils.BreizhCampCst;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import fr.ybonnel.breizhcamppdf.model.ProgrammeJsonBean;

/**
 * @author JefBinomed
 * 
 *         Programme robospice request
 * 
 */
public class ProgrammeJsonRequest extends SpringAndroidSpiceRequest<ProgrammeJsonBean> {

	public ProgrammeJsonRequest() {
		super(ProgrammeJsonBean.class);
	}

	@Override
	public ProgrammeJsonBean loadDataFromNetwork() throws Exception {

		return getRestTemplate().getForObject(BreizhCampCst.URL_PROGRAMME, // URL
				ProgrammeJsonBean.class, // Output class
				new HashMap<String, String>() // vars
				);
	}

}
