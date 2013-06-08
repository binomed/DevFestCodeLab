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
package com.binomed.breizhcamp.screen.speakers;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.utils.activities.AbstractRoboSherlockActivity;

public class SpeakersActivity extends AbstractRoboSherlockActivity {

	ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// We enabled the action bar
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		actionBar.setTitle(R.string.title_speakers);

		// We replace the content by the right fragment
		setContentView(R.layout.activity_speakers);
		getSupportFragmentManager().beginTransaction().add(R.id.content, new SpeakerListFragment()).commit();
	}

}
