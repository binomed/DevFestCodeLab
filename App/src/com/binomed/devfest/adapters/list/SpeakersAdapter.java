package com.binomed.devfest.adapters.list;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.binomed.devfest.model.SpeakerBean;
import com.binomed.devfest.screen.speakers.SpeakerView;

public class SpeakersAdapter extends BaseAdapter {

	private List<SpeakerBean> speakerList;

	private Context context;

	public SpeakersAdapter(Context context) {
		super();
		this.context = context;
	}

	public void setSpeakersList(List<SpeakerBean> sessionList) {
		this.speakerList = sessionList;
	}

	public SpeakersAdapter() {
		super();
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
		SpeakerBean speaker = (SpeakerBean) getItem(index);
		SpeakerView speakerView = null;
		if (view != null) {
			speakerView = (SpeakerView) view;
		} else {
			speakerView = new SpeakerView(context);
		}

		if (speaker != null) {
			speakerView.setSpeaker(speaker.getPhotoUrl(), speaker.getDesc());
		} else {
			speakerView.setSpeaker("", "");
		}
		return speakerView;
	}

}
