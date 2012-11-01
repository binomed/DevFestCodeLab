package com.binomed.devfest.utils;

import android.os.Bundle;

import com.binomed.devfest.R;
import com.cyrilmottier.polaris.PolarisMapView;
import com.google.android.maps.MapActivity;

public class InnerMapActivity extends MapActivity {

	private PolarisMapView mapView;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_map);

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
