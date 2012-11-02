package com.binomed.devfest.screen.infos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binomed.devfest.R;
import com.binomed.devfest.utils.RoboSherlockFragment;

public class PartnairesFragment extends RoboSherlockFragment {

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_partenaires, container, false);

	}

}
