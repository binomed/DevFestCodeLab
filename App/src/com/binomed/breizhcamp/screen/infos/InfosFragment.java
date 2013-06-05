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
package com.binomed.breizhcamp.screen.infos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;

import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.utils.BreizhCampCst;
import com.binomed.breizhcamp.utils.activities.InnerActivityManagerFragment;
import com.binomed.breizhcamp.utils.activities.InnerMapActivity;
import com.cyrilmottier.polaris.Annotation;
import com.cyrilmottier.polaris.PolarisMapView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;

/**
 * @author JefBinomed
 * 
 *         Generic fragment for informations
 * 
 */
public class InfosFragment extends InnerActivityManagerFragment {

	/*
	 * RoboGuice vars
	 */
	@InjectView(R.id.mapViewContainer)
	ViewGroup mapViewContainer;
	@InjectView(R.id.lbl)
	TextView lbl;
	@InjectView(R.id.desc)
	TextView desc;

	@InjectResource(R.string.infos_place_name)
	String placeName;
	@InjectResource(R.string.infos_place_place)
	String placePlace;
	@InjectResource(R.string.infos_sleep_name)
	String sleepName;
	@InjectResource(R.string.infos_sleep_place)
	String sleepPlace;
	@InjectResource(R.string.infos_after_party_name)
	String afterPartyName;
	@InjectResource(R.string.infos_after_party_place)
	String afterPartyPlace;

	/*
	 * static vars
	 */
	private final static String ACTIVITY_TAG = "hosted";
	private final static String TAG = "InfosFragment";

	/*
	 * Instance vars
	 */
	PolarisMapView mapView;
	MapController mapController;
	View innerMapView, mainView;
	private Geocoder geoCoder;
	private int type = -1;

	public void setType(int type) {
		this.type = type;
	}

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mainView = inflater.inflate(R.layout.fragment_infos, container, false);

		return mainView;

	}

	/**
	 * @param view
	 * @param savedInstanceState
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// We do all stuff here because roboguice only inject members here
		ScrollView scrollView = (ScrollView) mainView.findViewById(R.id.scrollView);
		scrollView.smoothScrollTo(0, 0);
		geoCoder = new Geocoder(getActivity(), Locale.getDefault());
		initTextAndLayout();
		if (type != BreizhCampCst.TYPE_CAR) {
			manageMapView(mainView);
		}
	}

	/**
	 * According to type of information we show the right information
	 */
	private void initTextAndLayout() {

		switch (type) {
		case BreizhCampCst.TYPE_PLACE:
			lbl.setText(R.string.infos_place);
			desc.setText(R.string.infos_place_desc);
			break;
		case BreizhCampCst.TYPE_CAR:
			lbl.setText(R.string.infos_car);
			desc.setText(R.string.infos_car_desc);
			mapViewContainer.setVisibility(View.GONE);
			break;
		case BreizhCampCst.TYPE_SLEEP:
			lbl.setText(R.string.infos_sleep);
			desc.setText(R.string.infos_sleep_desc);
			break;
		case BreizhCampCst.TYPE_AFTER_PARTY:
			lbl.setText(R.string.infos_after_party);
			desc.setText(R.string.infos_after_party_desc);
			break;

		default:
			break;
		}
	}

	/**
	 * Manage the injection of MapView
	 * 
	 * @param mainView
	 */
	private void manageMapView(View mainView) {

		// We have to get an instance of the MapActivity
		Intent intent = new Intent(getActivity(), InnerMapActivity.class);
		final Window w = getLocalActivityManager().startActivity(ACTIVITY_TAG + type, intent);
		// We inject the MapActivity layer into the specified place
		innerMapView = w != null ? w.getDecorView() : null;

		if (innerMapView != null) {
			ViewParent parent = innerMapView.getParent();
			if (parent != null) {
				ViewGroup v = (ViewGroup) parent;
				v.removeView(innerMapView);
			}

			innerMapView.setVisibility(View.VISIBLE);
			innerMapView.setFocusableInTouchMode(true);
			if (innerMapView instanceof ViewGroup) {
				((ViewGroup) innerMapView).setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
			}
		}
		// When the mapView is well created, we add it to the container
		mapViewContainer.addView(innerMapView);

		// We retrieve the mapView in order to manipulate it
		InnerMapActivity activityMap = (InnerMapActivity) getLocalActivityManager().getActivity(ACTIVITY_TAG + type);
		if (activityMap != null) {
			// We init it and place Annotations (Overlays)
			mapView = activityMap.getMapView();
			mapController = mapView.getController();
			mapController.setZoom(17);
			List<Annotation> annotations = new ArrayList<Annotation>();
			String namePlace = null;
			String address = null;
			switch (type) {
			case BreizhCampCst.TYPE_PLACE:
				namePlace = placeName;
				address = placePlace;
				break;
			case BreizhCampCst.TYPE_SLEEP:
				namePlace = sleepName;
				address = sleepPlace;
				break;
			case BreizhCampCst.TYPE_AFTER_PARTY:
				namePlace = afterPartyName;
				address = afterPartyPlace;
				break;

			default:
				break;
			}
			GeoPoint point = getGeoPoint(address);
			if (point != null) {
				mapController.animateTo(point);
				annotations.add(new Annotation(point, namePlace));
				mapView.setAnnotations(annotations, R.drawable.map_pin_holed_blue_normal);
			}

		}
	}

	/**
	 * Retrieve the geopoint corresponding to the adress
	 * 
	 * @param address
	 * @return
	 */
	private GeoPoint getGeoPoint(String address) {

		GeoPoint gp = null;
		Address ad = null;
		try {
			List<Address> addresses = geoCoder.getFromLocationName(address, 1);
			if (addresses != null && addresses.size() > 0) {

				ad = addresses.get(0);
				gp = new GeoPoint((int) (ad.getLatitude() * 1E6), (int) (ad.getLongitude() * 1E6));
			}
		} catch (IOException e) {
			Log.e(TAG, "Error getting place", e);
		}
		return gp;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// We have to clean the map view
		if (innerMapView != null) {
			mapView = null;
			mapViewContainer.removeView(innerMapView);
			innerMapView = null;
		}
	}

}
