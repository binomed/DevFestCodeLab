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
package com.binomed.devfest.adapters.list;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.binomed.devfest.model.SessionBean;
import com.binomed.devfest.screen.sessions.SessionView;

/**
 * @author JefBinomed
 * 
 *         Sessions Adapter
 * 
 */
public class SessionsAdapter extends BaseAdapter {

	private List<SessionBean> sessionList;

	private Context context;

	public SessionsAdapter(Context context) {
		super();
		this.context = context;
	}

	public void setSessionList(List<SessionBean> sessionList) {
		this.sessionList = sessionList;
	}

	public SessionsAdapter() {
		super();
	}

	@Override
	public int getCount() {
		return sessionList != null ? sessionList.size() : 0;
	}

	@Override
	public Object getItem(int index) {
		if (sessionList != null && index < sessionList.size()) {
			return sessionList.get(index);
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
		SessionView sessionView = null;
		if (view != null) {
			sessionView = (SessionView) view;
		} else {
			sessionView = new SessionView(context);
		}

		if (session != null) {
			sessionView.setSession(session);
		} else {
			sessionView.setSession(null);
		}
		return sessionView;
	}

}
