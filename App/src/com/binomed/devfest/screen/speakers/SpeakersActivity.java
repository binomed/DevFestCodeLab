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
package com.binomed.devfest.screen.speakers;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.binomed.devfest.R;
import com.binomed.devfest.utils.activities.AbstractDevFestRoboActivity;

public class SpeakersActivity extends AbstractDevFestRoboActivity {

	ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// We enabled the action bar
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		// We replace the content by the right fragment
		setContentView(R.layout.activity_speakers);
		getSupportFragmentManager().beginTransaction().add(R.id.content, new SpeakerListFragment()).commit();
	}

}
