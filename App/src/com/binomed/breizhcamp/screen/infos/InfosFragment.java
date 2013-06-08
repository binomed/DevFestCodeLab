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
import java.util.List;
import java.util.Locale;

import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.binomed.breizhcamp.R;
import com.binomed.breizhcamp.utils.BreizhCampCst;
import com.binomed.breizhcamp.utils.activities.AbstractRoboSherlockFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * @author JefBinomed
 * 
 *         Generic fragment for informations
 * 
 */
public class InfosFragment extends AbstractRoboSherlockFragment {

	/*
	 * RoboGuice vars
	 */
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
	Marker curentPosition;
	SupportMapFragment mapFragment;
	View mainView;
	private Geocoder geoCoder;
	private int type = -1;

	public void setType(int type) {
		this.type = type;
	}

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mainView = inflater.inflate(R.layout.fragment_infos, container, false);
		mapFragment = SupportMapFragment.newInstance();
		FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.mapFragment, mapFragment);
		fragmentTransaction.commit();

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
			FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
			transaction.hide(mapFragment);
			transaction.commit();
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
		// mapFragment = new SupportMapFragment();
		// FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		// transaction.add(R.id.mapFragment, mapFragment);
		// transaction.commit();

		// mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
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

		final String namePlaceFinal = namePlace;
		final LatLng point = getGeoPoint(address);
		if (point != null) {
			final Handler mHandler = new Handler();
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					GoogleMap map = mapFragment.getMap();
					if (map != null) {
						map.setMyLocationEnabled(true);
						// INIT HERE
						map.getUiSettings().setMyLocationButtonEnabled(false);
						// ...

						curentPosition = mapFragment.getMap() //
								.addMarker(new MarkerOptions() //
										.position(point) //
										.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_holed_blue_normal)) //
										.title(namePlaceFinal)//
								);
						mapFragment.getMap().animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
						mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(point, 17));
					} else
						mHandler.post(this);
				}
			});
		}

	}

	/**
	 * Retrieve the geopoint corresponding to the adress
	 * 
	 * @param address
	 * @return
	 */
	private LatLng getGeoPoint(String address) {

		LatLng latLng = null;
		Address ad = null;
		try {
			List<Address> addresses = geoCoder.getFromLocationName(address, 1);
			if (addresses != null && addresses.size() > 0) {

				ad = addresses.get(0);
				latLng = new LatLng(ad.getLatitude(), ad.getLongitude());
			}
		} catch (IOException e) {
			Log.e(TAG, "Error getting place", e);
		}
		return latLng;
	}
}
