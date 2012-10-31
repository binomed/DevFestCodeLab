package com.binomed.devfest.screen.speakers;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binomed.devfest.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class SpeakerView extends RelativeLayout {

	ImageView photoSpeaker;
	TextView speakerDesc;

	public SpeakerView(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_speaker, this);

		photoSpeaker = (ImageView) findViewById(R.id.photoSpeaker);
		speakerDesc = (TextView) findViewById(R.id.speakerDesc);

	}

	public void setSpeaker(String photoUrl, String speakerDesc) {
		UrlImageViewHelper.setUrlDrawable(photoSpeaker, photoUrl, R.drawable.ic_contact_picture);
		this.speakerDesc.setText(speakerDesc);

	}

}
