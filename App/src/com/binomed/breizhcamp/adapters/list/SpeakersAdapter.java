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

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.binomed.breizhcamp.screen.speakers.SpeakerView;

import fr.ybonnel.breizhcamppdf.model.Speaker;

/**
 * @author JefBinomed
 * 
 *         List adpater for speakers
 * 
 */
public class SpeakersAdapter extends BaseAdapter {

	/*
	 * Instance vars
	 */
	private List<Speaker> speakerList;

	private Context context;
	private final boolean full;

	public SpeakersAdapter(Context context, boolean full) {
		super();
		this.context = context;
		this.full = full;
	}

	public void setSpeakersList(List<Speaker> sessionList) {
		this.speakerList = sessionList;
	}

	@Override
	public int getCount() {
		return speakerList != null ? speakerList.size() : 0;
	}

	@Override
	public Object getItem(int index) {
		if (speakerList != null && index < speakerList.size()) {
			return speakerList.get(index);
		}
		return null;
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		Speaker speaker = (Speaker) getItem(index);
		SpeakerView speakerView = null;
		if (view != null) {
			speakerView = (SpeakerView) view;
		} else {
			speakerView = new SpeakerView(context);
		}

		if (speaker != null) {
			speakerView.setSpeaker(speaker.getAvatar(), full ? speaker.getDescription() : speaker.getFullname(), speaker.getLiens());
		} else {
			speakerView.setSpeaker("", "", null);
		}
		return speakerView;
	}

}
