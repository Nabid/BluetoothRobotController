package com.prome.bluetoothdevicecontroller.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prome.bluetoothdevicecontroller.R;

/**
 * Created by root on 3/6/15.
 */
public class AboutFragment extends Fragment {
	// set tag
	public static final String TAG = "AboutFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_about, container, false);
	}
}
