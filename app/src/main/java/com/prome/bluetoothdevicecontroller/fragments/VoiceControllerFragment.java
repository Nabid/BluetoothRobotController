package com.prome.bluetoothdevicecontroller.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.prome.bluetoothdevicecontroller.R;
import com.prome.bluetoothdevicecontroller.activities.MainActivity;

import java.util.Locale;

/**
 * Home fragment
 */
public class VoiceControllerFragment extends Fragment {
	// set tag
	public static final String TAG = "VoiceControllerFragment";
	// static result code, random integer
	public static final int REQUEST_CODE_VOICE = 1010;

	// textview -> recognized text
	TextView tvRecognizedText;

	// imagebutton -> tap to start speech to text activity
	ImageButton btnTap;

	// set intent for recognize speech
	Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		View rootView = null;
		rootView = inflater.inflate(R.layout.fragment_voice_controller, container, false);

		// find view by ids
		tvRecognizedText = (TextView) rootView.findViewById(R.id.tv_recognized_text);
		btnTap = (ImageButton) rootView.findViewById(R.id.btn_voice_command);

		return rootView;
	}

	/*
04-10 23:11:13.005  29915-29915/? D/onActivityResult﹕ voice activity started
04-10 23:11:13.065  29915-29915/? D/onActivityResult﹕ voice activity finished
04-10 23:11:24.855  29915-29915/? D/onActivityResult﹕ request: 197618, result: -1
	 */

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// put language
		//intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		//intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				getString(R.string.speech_prompt));

		// tap button on click listener
		btnTap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				try {
					// start activity
					Log.d("onActivityResult", "voice activity started");
					startActivityForResult(intent, REQUEST_CODE_VOICE);
					Log.d("onActivityResult", "voice activity finished");
				} catch(ActivityNotFoundException e) {
					// the device does not support android speech
					showToast("Your Device Does Not Support Speech Recognition!");
				}
			}
		});
	}

	public void showToast( String str ) {
		Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
	}
}
