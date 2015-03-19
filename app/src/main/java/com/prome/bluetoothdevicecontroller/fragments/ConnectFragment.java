package com.prome.bluetoothdevicecontroller.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkmmte.view.CircularImageView;
import com.prome.bluetoothdevicecontroller.R;

/**
 * Home fragment
 */
public class ConnectFragment extends Fragment {
	// set tag
	public static final String TAG = "ConnectFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_connect, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		/*CircularImageView circularImageView = (CircularImageView)getActivity().findViewById(R.id.imageview_bluetooth_connect);
		circularImageView.setBorderColor(getResources().getColor(R.color.accent_material_dark));
		circularImageView.setBorderWidth(20);
		//circularImageView.setSelectorColor(getResources().getColor(R.color.material_blue_grey_950));
		circularImageView.setSelectorStrokeColor(getResources().getColor(R.color.bright_foreground_disabled_material_light));
		circularImageView.setSelectorStrokeWidth(20);
		circularImageView.addShadow();*/
	}
}
