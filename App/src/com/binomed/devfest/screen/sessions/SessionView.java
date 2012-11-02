package com.binomed.devfest.screen.sessions;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binomed.devfest.R;
import com.binomed.devfest.model.SessionBean;

public class SessionView extends RelativeLayout {

	TextView sessionName;
	TextView sessionHour;
	SessionBean session;

	public SessionBean getSession() {
		return session;
	}

	public SessionView(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_sessions, this);

		sessionName = (TextView) findViewById(R.id.sessionName);
		sessionHour = (TextView) findViewById(R.id.sessionHour);

	}

	public void setSession(SessionBean session) {
		this.session = session;
		if (session == null) {
			return;
		}
		this.sessionName.setText(session.getTitle());
		this.sessionHour.setText(session.getStartTime() + " - " + session.getEndTime());

	}

}
