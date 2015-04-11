package com.prome.bluetoothdevicecontroller.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.prome.bluetoothdevicecontroller.R;
import com.prome.bluetoothdevicecontroller.helpers.DisplayHelper;
import com.squareup.picasso.Picasso;

/**
 * Home fragment
 */
public class TouchControllerFragment extends Fragment {
	// set tag
	public static final String TAG = "TouchControllerFragment";
	// 3 layouts containing controller buttons
	private LinearLayout linearLayout1, linearLayout2, linearLayout3;
	// control images
	private Button topLeft, topRight, bottomLeft, bottomRight, left, right, up, down;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_touch_controller, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// initialize private members
		init();

		// get display height and width
		int displayWidth = DisplayHelper.getInstance(getActivity()).width;
		int displayHeight = DisplayHelper.getInstance(getActivity()).height;

		// set control images resources using Picasso
		//setControlImages();

		// set resources of control images views
		//setControlImages();
	}

	/*
	private void setControlImages() {
		// topleft
		Picasso.with(getActivity())
				.load(R.drawable.arrow)
				.rotate((float)-45)
				.into(topLeft);
		// up
		Picasso.with(getActivity())
				.load(R.drawable.arrow)
				.into(up);
		// topright
		Picasso.with(getActivity())
				.load(R.drawable.arrow)
				.rotate((float)45)
				.into(topRight);
		// left
		Picasso.with(getActivity())
				.load(R.drawable.arrow)
				.rotate((float)-90)
				.into(left);
		//right
		Picasso.with(getActivity())
				.load(R.drawable.arrow)
				.rotate((float)90)
				.into(right);
		// bottomleft
		Picasso.with(getActivity())
				.load(R.drawable.arrow)
				.rotate((float)-135)
				.into(bottomLeft);
		//down
		Picasso.with(getActivity())
				.load(R.drawable.arrow)
				.rotate((float)180)
				.into(down);
		// bottomright
		Picasso.with(getActivity())
				.load(R.drawable.arrow)
				.rotate((float)135)
				.into(bottomRight);
	}
	*/

	private void init() {
		// initialize linear layouts
		linearLayout1 = (LinearLayout) getActivity().findViewById(R.id.linearlayout_touchcontroller_1);
		linearLayout2 = (LinearLayout) getActivity().findViewById(R.id.linearlayout_touchcontroller_2);
		linearLayout3 = (LinearLayout) getActivity().findViewById(R.id.linearlayout_touchcontroller_3);
		// initialize image views
		topLeft = (Button) getActivity().findViewById(R.id.touchcontrol_1_1);
		topRight = (Button) getActivity().findViewById(R.id.touchcontrol_1_3);
		bottomLeft = (Button) getActivity().findViewById(R.id.touchcontrol_3_1);
		bottomRight = (Button) getActivity().findViewById(R.id.touchcontrol_3_3);

		left = (Button) getActivity().findViewById(R.id.touchcontrol_left);
		right = (Button) getActivity().findViewById(R.id.touchcontrol_right);

		up = (Button) getActivity().findViewById(R.id.touchcontrol_1_4);
		down = (Button) getActivity().findViewById(R.id.touchcontrol_2_4);

		topLeft.setRotation(-45f);
		topLeft.setBackgroundResource(R.drawable.ic_action_collapse);

		topRight.setRotation(45f);
		topRight.setBackgroundResource(R.drawable.ic_action_collapse);

		bottomLeft.setRotation(-135f);
		bottomLeft.setBackgroundResource(R.drawable.ic_action_collapse);

		bottomRight.setRotation(135f);
		bottomRight.setBackgroundResource(R.drawable.ic_action_collapse);

		up.setRotation(-90f);
		up.setBackgroundResource(R.drawable.ic_action_forward);

		down.setRotation(90f);
		down.setBackgroundResource(R.drawable.ic_action_forward);
	}
}
