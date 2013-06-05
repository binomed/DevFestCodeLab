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
package com.binomed.breizhcamp.utils.activities;

import android.os.Bundle;

import com.binomed.breizhcamp.R;
import com.cyrilmottier.polaris.PolarisMapView;
import com.google.android.maps.MapActivity;

public class InnerMapActivity extends MapActivity {

	private PolarisMapView mapView;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_map);

		// We don't want to let the user interact with the map
		mapView = (PolarisMapView) findViewById(R.id.mapView);
		mapView.setClickable(false);
	}

	public PolarisMapView getMapView() {
		return mapView;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onStop() {
		mapView.onStop();
		super.onStop();
	}

	@Override
	public void onStart() {
		super.onStart();
		mapView.onStart();
	}

}
