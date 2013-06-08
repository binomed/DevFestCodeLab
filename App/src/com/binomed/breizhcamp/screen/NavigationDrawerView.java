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
package com.binomed.breizhcamp.screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binomed.breizhcamp.R;

/**
 * @author JefBinomed Navigation Drawer
 */
public class NavigationDrawerView extends RelativeLayout {

	ImageView iconNavigation;
	TextView text;
	Context context;

	public NavigationDrawerView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_navigation_drawer, this);

		iconNavigation = (ImageView) findViewById(R.id.iconNavigation);
		text = (TextView) findViewById(R.id.text);

	}

	/**
	 * @param icon
	 * @param text
	 */
	public void setNavigation(int drawable, String text) {
		iconNavigation.setBackgroundResource(drawable);
		this.text.setText(text);

	}

}
