package com.prome.bluetoothdevicecontroller.activities;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.prome.bluetoothdevicecontroller.R;
import com.prome.bluetoothdevicecontroller.adapters.CustomStateViewPagerAdapter;
import com.prome.bluetoothdevicecontroller.dialogs.DeviceListDialog;
import com.prome.bluetoothdevicecontroller.fragments.AboutFragment;
import com.prome.bluetoothdevicecontroller.fragments.CameraControllerFragment;
import com.prome.bluetoothdevicecontroller.fragments.ConnectFragment;
import com.prome.bluetoothdevicecontroller.fragments.TouchControllerFragment;
import com.prome.bluetoothdevicecontroller.fragments.VoiceControllerFragment;
import com.prome.bluetoothdevicecontroller.helpers.BluetoothHelper;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

	// private variables
	ViewPager pager;
	// fragment list
	ArrayList<Fragment> fragments;
	// fragment title list
	ArrayList<String> titles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// initiate variables
		init();

		// set viewpager
		pager = (ViewPager) findViewById(R.id.view_pager);
		// create new adapter
		CustomStateViewPagerAdapter customViewPagerAdapter = new CustomStateViewPagerAdapter(getSupportFragmentManager());
		// pass fragments into adapter
		customViewPagerAdapter.setFragments(fragments);
		// pass titles of fragments into adapter
		customViewPagerAdapter.setTitles(titles);
		pager.setAdapter(customViewPagerAdapter);
		pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int i, float v, int i2) {

			}

			@Override
			public void onPageSelected(int i) {

			}

			@Override
			public void onPageScrollStateChanged(int i) {

			}
		});
	}

	// initializes variables
	private void init() {
		// initiate fragments
		fragments = new ArrayList<>();
		fragments.add(new ConnectFragment());
		fragments.add(new TouchControllerFragment());
		fragments.add(new VoiceControllerFragment());
		fragments.add(new CameraControllerFragment());
		fragments.add(new AboutFragment());

		// initiate titles
		titles = new ArrayList<>();
		titles.add("Connect");
		titles.add("Touch Control");
		titles.add("Voice Command");
		titles.add("Camera Control");
		titles.add("ABOUT");
	}


	/**
	 * Bluetooth enable result
	 *
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Log.d("onActivityResult", "request: "+requestCode+", result: "+resultCode);

		switch(requestCode) {
			// BluetoothHelper requestCode
			case BluetoothHelper.REQUEST_ENABLE_BT:
				if(resultCode == RESULT_OK) {
					// result ok
					Log.d(BluetoothHelper.TAG, "bluetooth enabled, resultCode: OK ->" + resultCode);
				} else if(resultCode == RESULT_CANCELED) {
					// result cancelled
					Log.d(BluetoothHelper.TAG, "bluetooth enable error, resultCode: CANCELED ->" + resultCode);
				}
				break;

			// Speech to Text
			case VoiceControllerFragment.REQUEST_CODE_VOICE:
				// get the results
				ArrayList<String> text
						= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				// show results
				TextView tvRecognizedText = (TextView) findViewById(R.id.tv_recognized_text);
				// tmp string
				//String tmpStr = "";
				tvRecognizedText.setText( "Success: " );

				Log.d("onActivityResult voice", "result: "+RESULT_OK);

				if(resultCode == RESULT_OK && data != null) {
					// concatenate all results
					for(String str : text) {
						tvRecognizedText.setText( tvRecognizedText.getText().toString() + str );
					}
					Log.d("speech", text.get(0));
				} else {
					tvRecognizedText.setText( "Sorry! Unrecognizable speech.\nTry again!!" );
				}
				break;
		}
	}

	/**
	 * shows DeviceListDialog
	 *
	 * @param title
	 * @param deviceList
	 */
	public void startDeviceListDialog(String title, ArrayList<backport.android.bluetooth.BluetoothDevice> deviceList) {
		DeviceListDialog.getInstance(title, deviceList)
				.show(getSupportFragmentManager(), DeviceListDialog.TAG);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if(id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
