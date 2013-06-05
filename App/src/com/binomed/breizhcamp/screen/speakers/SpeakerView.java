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
package com.binomed.breizhcamp.screen.speakers;

import java.util.List;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;
import net.londatiga.android.QuickAction.OnActionItemClickListener;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binomed.breizhcamp.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import fr.ybonnel.breizhcamppdf.model.Lien;

/**
 * @author JefBinomed Speaker View
 */
public class SpeakerView extends RelativeLayout implements OnClickListener {

	ImageView photoSpeaker;
	TextView speakerDesc;
	Context context;
	List<Lien> liens;

	public SpeakerView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_speaker, this);

		photoSpeaker = (ImageView) findViewById(R.id.photoSpeaker);
		speakerDesc = (TextView) findViewById(R.id.speakerDesc);

		// Add the proper listener for proposing the google plus link
		photoSpeaker.setOnClickListener(this);
	}

	/**
	 * @param photoUrl
	 * @param speakerDesc
	 * @param liens
	 *            Method for changing the content of the view
	 */
	public void setSpeaker(String photoUrl, String speakerDesc, List<Lien> liens) {
		this.liens = liens;
		UrlImageViewHelper.setUrlDrawable(photoSpeaker, photoUrl, R.drawable.ic_contact_picture);
		this.speakerDesc.setText(speakerDesc);

	}

	@Override
	public void onClick(View view) {

		int index = Menu.FIRST;
		if (liens != null) {
			QuickAction quickAction = new QuickAction(context);
			for (Lien lien : liens) {
				final Lien lienFinal = lien;
				int drawable = -1;
				if ("Twitter".toLowerCase().equals(lien.getLabel().toLowerCase())) {
					drawable = R.drawable.twitter;
				} else if ("Blog".toLowerCase().equals(lien.getLabel().toLowerCase())) {
					drawable = R.drawable.blog;
				} else if ("Github".toLowerCase().equals(lien.getLabel().toLowerCase())) {
					drawable = R.drawable.github;
				}
				if (drawable != -1) {
					ActionItem gPlusActionItem = new ActionItem(index, context.getResources().getDrawable(R.drawable.google_plus));
					quickAction.addActionItem(gPlusActionItem);
					quickAction.setOnActionItemClickListener(new OnActionItemClickListener() {

						@Override
						public void onItemClick(QuickAction source, int pos, int actionId) {
							String url = lienFinal.getUrl();
							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setData(Uri.parse(url));
							context.startActivity(intent);

						}
					});
					quickAction.show(view);
				}
				index++;
			}
		}

	}

}
