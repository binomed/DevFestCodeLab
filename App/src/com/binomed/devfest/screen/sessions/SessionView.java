package com.binomed.devfest.screen.sessions;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binomed.devfest.R;

public class SessionView extends RelativeLayout {

	TextView sessionName;
	TextView sessionHour;

	public SessionView(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_sessions, this);

		sessionName = (TextView) findViewById(R.id.sessionName);
		sessionHour = (TextView) findViewById(R.id.sessionHour);

	}

	public void setSession(String sessionName, String startTime, String endTime) {
		this.sessionName.setText(sessionName);
		this.sessionHour.setText(startTime + " - " + endTime);

	}

}
