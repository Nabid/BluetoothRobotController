package com.prome.bluetoothdevicecontroller.fragments;

import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.prome.bluetoothdevicecontroller.R;
import com.prome.bluetoothdevicecontroller.activities.MainActivity;
import com.prome.bluetoothdevicecontroller.helpers.BluetoothHelper;

import java.util.ArrayList;

/**
 * Home fragment
 */
public class ConnectFragment extends Fragment {
	// set tag
	public static final String TAG = "ConnectFragment";

	// bluetooth status textview
	TextView tvBTStatus;

	// connect bluetooth button
	Button btnBTConnect;

	// get paired device list button
	Button btnBTPairedDevices;

	// bluetooth helper
	BluetoothHelper bluetoothHelper;

	// scan for devices
	Button btnBTScan;

	// disable bluetooth
	Button btnBTDisable;

	// discoverable
	Button btnBTDiscoverable;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_connect, container, false);

		// find view by id
		btnBTConnect = (Button) rootView.findViewById(R.id.btn_bt_connect);
		btnBTPairedDevices = (Button) rootView.findViewById(R.id.btn_bt_get_paired_devices);
		btnBTScan = (Button) rootView.findViewById(R.id.btn_bt_start_discovery);
		btnBTDisable = (Button) rootView.findViewById(R.id.btn_bt_disable);
		btnBTDiscoverable = (Button) rootView.findViewById(R.id.btn_bt_make_discoverable);

		tvBTStatus = (TextView) rootView.findViewById(R.id.tv_bt_conntivity_status);

		// get new instance of BluetoothHelper class
		bluetoothHelper = BluetoothHelper.getInstance(getActivity());
		bluetoothHelper.setBluetoothAdapter();

		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		//new CheckBTConnectivity().execute();
		// check -> listen to ACTION_STATE_CHANGED broadcast intent


		// turn on bluetooth
		btnBTConnect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// check the device is bluetooth supported or not
				if(bluetoothHelper.isBluetoothSupported()) {
					// bluetooth supported
					// now enable bluetooth
					Log.d(ConnectFragment.TAG, "bluetooth supported");
					Toast.makeText(getActivity(), "bluetooth supported", Toast.LENGTH_SHORT).show();
					bluetoothHelper.enableBluetooth(getActivity());
					Log.d(ConnectFragment.TAG, "bluetooth enabled");
					Toast.makeText(getActivity(), "bluetooth enabled", Toast.LENGTH_SHORT).show();
				} else {
					// not supported
					Log.d(ConnectFragment.TAG, "bluetooth not supported");
					Toast.makeText(getActivity(), "bluetooth not supported", Toast.LENGTH_SHORT).show();
				}
			}
		});

		// get paired devices
		btnBTPairedDevices.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<backport.android.bluetooth.BluetoothDevice> deviceList = bluetoothHelper.getPairedDevices();
				for(backport.android.bluetooth.BluetoothDevice tmp : deviceList) {
					Log.d("paired_device", "name: " + tmp.getName() + ", address: " + tmp.getAddress());
				}

				// show paired devices
				((MainActivity) getActivity()).startDeviceListDialog("Paired Devices", deviceList);
			}
		});

		// scan for devices
		btnBTScan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// start discovery
				bluetoothHelper.startDiscovery(getActivity());
			}
		});

		// disable bluetooth
		btnBTDisable.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bluetoothHelper.disableBluetooth();
			}
		});

		// make discoverable
		btnBTDiscoverable.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bluetoothHelper.makeDiscoverable();
				Log.d(BluetoothHelper.TAG, "bluetooth is discoverable");
			}
		});
	}

	/**
	 * Background thread identifies bluetooth connection available or lost
	 */
	private class CheckBTConnectivity extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// do something
			// ...
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			// create new handler to start this thread again
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					// create new thread after 5 seconds
					new CheckBTConnectivity().execute();
				}
			}, 3000);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// cancel discovering bluetooth devices
		bluetoothHelper.cancelDiscovery(getActivity());
	}
}
