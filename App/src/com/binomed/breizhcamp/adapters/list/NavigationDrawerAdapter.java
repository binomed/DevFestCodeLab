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
package com.binomed.breizhcamp.adapters.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.screen.NavigationDrawerView;

/**
 * @author JefBinomed
 * 
 *         Sessions Adapter
 * 
 */
public class NavigationDrawerAdapter extends BaseAdapter {

	private Context context;

	public NavigationDrawerAdapter(Context context) {
		super();
		this.context = context;
	}

	public NavigationDrawerAdapter() {
		super();
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public Object getItem(int index) {
		return null;
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		String text = null;
		int resource = -1;
		switch (index) {
		case 0:
			text = context.getResources().getString(R.string.action_speakers);
			resource = R.drawable.ic_action_speakers;
			break;
		case 1:
			text = context.getResources().getString(R.string.action_infos);
			resource = R.drawable.ic_action_infos;

			break;
		case 2:
		default:
			text = context.getResources().getString(R.string.action_sessions);
			resource = R.drawable.ic_action_sessions;

			break;

		}
		NavigationDrawerView navigationView = null;
		if (view != null) {
			navigationView = (NavigationDrawerView) view;
		} else {
			navigationView = new NavigationDrawerView(context);
		}
		navigationView.setNavigation(resource, text);

		return navigationView;
	}

}
