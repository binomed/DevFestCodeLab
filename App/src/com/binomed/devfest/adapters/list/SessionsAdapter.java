package com.binomed.devfest.adapters.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.binomed.devfest.model.SessionBean;

public class SessionsAdapter extends BaseAdapter {

	private SessionBean[] sessionList;

	private Context context;

	public SessionsAdapter(Context context) {
		super();
		this.context = context;
	}

	public void setSessionList(SessionBean[] sessionList) {
		this.sessionList = sessionList;
	}

	public SessionsAdapter() {
		super();
	}

	@Override
	public int getCount() {
		return sessionList != null ? sessionList.length : 0;
	}

	@Override
	public Object getItem(int index) {
		if (sessionList != null && index < sessionList.length) {
			return sessionList[index];
		}
		return null;
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		SessionBean session = (SessionBean) getItem(index);
		TextView sessionView = null;
		if (view != null) {
			sessionView = (TextView) view;
		} else {
			sessionView = new TextView(context);
		}

		if (session != null) {
			sessionView.setText(session.getTitle());
		} else {
			sessionView.setText("");
		}
		return sessionView;
	}

}