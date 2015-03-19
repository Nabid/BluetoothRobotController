package com.prome.bluetoothdevicecontroller.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prome.bluetoothdevicecontroller.R;

/**
 * Home fragment
 */
public class CameraControllerFragment extends Fragment {
	// set tag
	public static final String TAG = "CameraControllerFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_camera_controller, container, false);
	}
}
