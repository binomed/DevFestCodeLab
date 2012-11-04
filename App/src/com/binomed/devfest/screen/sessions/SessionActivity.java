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
package com.binomed.devfest.screen.sessions;

import android.os.Bundle;

import com.binomed.devfest.R;
import com.binomed.devfest.model.SessionBean;
import com.binomed.devfest.utils.DevFestCst;
import com.binomed.devfest.utils.activities.AbstractDevFestRoboActivity;

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
		fragment.setSession((SessionBean) getIntent().getExtras().getParcelable(DevFestCst.EXTRA_INTENT_SESSION));
		getSupportFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
	}

}
