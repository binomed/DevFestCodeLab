package com.binomed.devfest.screen.sessions;

import roboguice.RoboGuice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binomed.devfest.R;

public class SessionView extends RelativeLayout implements OnClickListener {

	ImageView actionGoSession;
	TextView sessionName;
	TextView sessionHour;

	public SessionView(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_sessions, this);
		RoboGuice.getInjector(context).injectMembers(this);

		actionGoSession = (ImageView) findViewById(R.id.actionGoSession);
		sessionName = (TextView) findViewById(R.id.sessionName);
		sessionHour = (TextView) findViewById(R.id.sessionHour);

		// Init listener
		actionGoSession.setOnClickListener(this);
	}

	public void setSession(String sessionName, String startTime, String endTime) {
		this.sessionName.setText(sessionName);
		this.sessionHour.setText(startTime + " - " + endTime);

	}

	@Override
	public void onClick(View view) {
		// TODO

	}

}
