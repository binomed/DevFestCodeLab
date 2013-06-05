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
package com.binomed.breizhcamp.screen.sessions;

import android.os.Bundle;

import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.utils.BreizhCampCst;
import com.binomed.breizhcamp.utils.activities.AbstractDevFestRoboActivity;

import fr.ybonnel.breizhcamppdf.model.Talk;

/**
 * @author JefBinomed
 * 
 *         Simple activity hosting the fragment for sessions
 * 
 */
public class SessionActivity extends AbstractDevFestRoboActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_session);
		// We replace the content with the right fragment
		SessionFragment fragment = new SessionFragment();
		fragment.setSession((Talk) getIntent().getExtras().getParcelable(BreizhCampCst.EXTRA_INTENT_SESSION));
		getSupportFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
	}

}
