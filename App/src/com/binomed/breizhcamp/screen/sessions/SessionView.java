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

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binomed.breizhcamp.R;

import fr.ybonnel.breizhcamppdf.model.Talk;

/**
 * @author JefBinomed
 * 
 *         Session View
 * 
 */
public class SessionView extends RelativeLayout {

	TextView sessionName;
	TextView sessionHour;
	Talk session;

	public Talk getSession() {
		return session;
	}

	public SessionView(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_sessions, this);

		sessionName = (TextView) findViewById(R.id.sessionName);
		sessionHour = (TextView) findViewById(R.id.sessionHour);

	}

	public void setSession(Talk session) {
		this.session = session;
		if (session == null) {
			return;
		}
		this.sessionName.setText(session.getTitle());
		this.sessionHour.setText(session.getTime() + " - " + session.getEndTime());

	}

}
