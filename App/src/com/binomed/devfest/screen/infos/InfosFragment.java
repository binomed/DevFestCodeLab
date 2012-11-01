package com.binomed.devfest.screen.infos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
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

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.binomed.devfest.R;
import com.binomed.devfest.utils.DevFestCst;
import com.binomed.devfest.utils.InnerMapActivity;
import com.binomed.devfest.utils.LocalActivityManagerFragment;
import com.cyrilmottier.polaris.Annotation;
import com.cyrilmottier.polaris.PolarisMapView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;

public class InfosFragment extends LocalActivityManagerFragment {

	PolarisMapView mapView;
	MapController mapController;
	View innerMapView, mainView;
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

	private Geocoder geoCoder;
	private SherlockFragmentActivity activity;
	private final static String ACTIVITY_TAG = "hosted";
	private final static String TAG = "InfosFragment";
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

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ScrollView scrollView = (ScrollView) mainView.findViewById(R.id.scrollView);
		scrollView.smoothScrollTo(0, 0);
		geoCoder = new Geocoder(getActivity(), Locale.getDefault());
		initTextAndLayout();
		if (type != DevFestCst.TYPE_CAR) {
			manageMapView(mainView);
		}
	}

	private void initTextAndLayout() {
		switch (type) {
		case DevFestCst.TYPE_PLACE:
			lbl.setText(R.string.infos_place);
			desc.setText(R.string.infos_place_desc);
			break;
		case DevFestCst.TYPE_CAR:
			lbl.setText(R.string.infos_car);
			desc.setText(R.string.infos_car_desc);
			mapViewContainer.setVisibility(View.GONE);
			break;
		case DevFestCst.TYPE_SLEEP:
			lbl.setText(R.string.infos_sleep);
			desc.setText(R.string.infos_sleep_desc);
			break;
		case DevFestCst.TYPE_AFTER_PARTY:
			lbl.setText(R.string.infos_after_party);
			desc.setText(R.string.infos_after_party_desc);
			break;

		default:
			break;
		}
	}

	private void manageMapView(View mainView) {

		Intent intent = new Intent(getActivity(), InnerMapActivity.class);
		final Window w = getLocalActivityManager().startActivity(ACTIVITY_TAG, intent);
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

		mapViewContainer.addView(innerMapView);

		// We retrieve the mapView
		InnerMapActivity activityMap = (InnerMapActivity) getLocalActivityManager().getActivity(ACTIVITY_TAG);
		if (activityMap != null) {
			mapView = activityMap.getMapView();
			mapController = mapView.getController();
			mapController.setZoom(17);
			List<Annotation> annotations = new ArrayList<Annotation>();
			String namePlace = null;
			String address = null;
			switch (type) {
			case DevFestCst.TYPE_PLACE:
				namePlace = placeName;
				address = placePlace;
				break;
			case DevFestCst.TYPE_SLEEP:
				namePlace = sleepName;
				address = sleepPlace;
				break;
			case DevFestCst.TYPE_AFTER_PARTY:
				namePlace = afterPartyName;
				address = afterPartyPlace;
				break;

			default:
				break;
			}
			GeoPoint point = getGeoPoint(address);
			mapController.animateTo(point);
			annotations.add(new Annotation(point, namePlace));
			mapView.setAnnotations(annotations, R.drawable.map_pin_holed_blue_normal);

		}
	}

	private GeoPoint getGeoPoint(String address) {

		GeoPoint gp = null;
		Address ad = null;
		try {
			List<Address> addresses = geoCoder.getFromLocationName(address, 1);
			if (addresses != null && addresses.size() > 0) {

				ad = addresses.get(0);
				gp = new GeoPoint((int) (ad.getLatitude() * 1E6), (int) (ad.getLongitude() * 1E6));
				Log.i(TAG, "Place found : " + gp.getLatitudeE6() + " , " + gp.getLongitudeE6());
			}
		} catch (IOException e) {
			Log.e(TAG, "Error getting place", e);
		}
		return gp;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (innerMapView != null) {
			mapView.getOverlays().clear();
			mapView = null;
			mapViewContainer.removeView(innerMapView);
			innerMapView = null;
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (SherlockFragmentActivity) activity;
	}

}
