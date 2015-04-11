package com.prome.bluetoothdevicecontroller.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.prome.bluetoothdevicecontroller.activities.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Bluetooth helper class
 *
 * @author Md. Nabid Imteaj
 * @version 1.0
 * @see android.bluetooth.BluetoothAdapter
 * @see <a href="http://developer.android.com/guide/topics/connectivity/bluetooth.html">http://developer.android.com/guide/topics/connectivity/bluetooth.html</a>
 */
public class BluetoothHelper implements Runnable {
	// tag
	public static final String TAG = "BluetoothHelper";

	// make it singleton
	private static BluetoothHelper bluetoothHelper = null;

	// bluetooth adapter
	private static BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

	// local integer > 0, taking random int which may not conflict
	// with other requestCode
	public static final int REQUEST_ENABLE_BT = 1001;

	// save found bluetooth devices in range
	ArrayList<BluetoothDevice> foundDevices = new ArrayList<>();

	// progress dialog
	ProgressDialog progress;

	// socket
	private BluetoothSocket mBluetoothSocket;

	// uuid
	private UUID uuid = UUID.randomUUID();

	// bluetooth device
	private BluetoothDevice bluetoothDevice;

	// save context
	private Context context;

	// constructor
	private BluetoothHelper(Context context) {
		this.context = context;
	}

	/**
	 * Returns new instance if not created, previous instance otherwise
	 *
	 * @return bluetoothHelper
	 */
	public static BluetoothHelper getInstance(Context context) {
		if(bluetoothHelper == null) bluetoothHelper = new BluetoothHelper(context);
		return bluetoothHelper;
	}

	/**
	 * Checks the device is Bluetooth supported or not
	 *
	 * @return true if the device is Bluetooth supported, false otherwise
	 */
	public boolean isBluetoothSupported() {
		if(bluetoothAdapter == null) return false;
		return true;
	}

	/**
	 * Enables Bluetooth
	 *
	 * @param context
	 * @see android.app.Activity
	 */
	public void enableBluetooth(Activity context) {
		if(!bluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			// note: onActivityResult() must be implemented in the parent activity
			// in our case it is defined in MainActivity
			context.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

			// save context for future use
			//this.context = context;
		}
	}

	/**
	 * Returns paired devices connected with this device
	 *
	 * @return deviceList
	 */
	public ArrayList<BluetoothDevice> getPairedDevices() {
		Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
		ArrayList<BluetoothDevice> deviceList = new ArrayList<>();

		// check has paired devices or not
		if(pairedDevices.size() > 0) {
			// initiate array list
			//deviceList = new ArrayList<>();

			// get names, address and its type
			for(BluetoothDevice device : pairedDevices) {
				//deviceList.add(device.getName() + "\n" + device.getAddress());
				deviceList.add(device);
			}
		}

		return deviceList;
	}

	/**
	 * Disables bluetooth
	 */
	public void disableBluetooth() {
		bluetoothAdapter.disable();
	}

	/**
	 * Cancel discovering devices
	 * Must add it in onDestroy() of an activity or fragment
	 */
	public void cancelDiscovery(Context context) {
		// if bluetooth is supported and is discovering devices
		// then cancel discovering devices
		if(bluetoothAdapter != null && bluetoothAdapter.isDiscovering()) {
			bluetoothAdapter.cancelDiscovery();
			// unregister receiver
			context.unregisterReceiver(mReceiver);
		}
	}

	/**
	 * Start discovering bluetooth devices in range
	 *
	 * @param context
	 */
	public void startDiscovery(Context context) {
		// get a new IntentFilter
		IntentFilter filter = new IntentFilter();

		filter.addAction(BluetoothDevice.ACTION_FOUND);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

		// register broadcast receiver
		context.registerReceiver(mReceiver, filter);

		bluetoothAdapter.startDiscovery();
	}

	private void showProgress(Context context, String message) {
		progress = new ProgressDialog(context);
		progress.setMessage(message);
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setIndeterminate(true);
		progress.setCancelable(false);
		progress.setCanceledOnTouchOutside(false);
		progress.show();
	}

	private void hideProgress() {
		if(progress.isShowing()) {
			progress.dismiss();
		}
	}

	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
				//discovery starts, we can show progress dialog or perform other tasks
				Log.d(BluetoothHelper.TAG, "discovery started");

				// clear previous list
				foundDevices.clear();

				// show loading dialog
				showProgress(context, "Scanning devices...");

			} else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				//discovery finishes, dismis progress dialog
				Log.d(BluetoothHelper.TAG, "discovery finished");

				// hide progress dialog
				hideProgress();

				// show found devices
				((MainActivity) context).startDeviceListDialog("Paired Devices", foundDevices);

			} else if(BluetoothDevice.ACTION_FOUND.equals(action)) {
				//bluetooth device found
				BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

				// insert this device into foundDevice array list
				foundDevices.add(device);

				//showToast("Found device " + device.getName());
				Log.d(BluetoothHelper.TAG, "found: " + device.getName() + ", " + device.getAddress());
			}
		}
	};

	/**
	 * Returns found devices by searching in range
	 *
	 * @return ArrayList<BluetoothDevice>
	 */
	public ArrayList<BluetoothDevice> getFoundDevices() {
		return foundDevices;
	}

	public void connectToBTDevice(BluetoothDevice device) {
		// get bluetooth device by address
		bluetoothDevice = bluetoothAdapter.getRemoteDevice(device.getAddress());

		// show dialog connecting
		showProgress(context, "Connecting..."+"\n"+device.getName()+"\n"+device.getAddress());
		Log.d(BluetoothHelper.TAG, "Connecting..."+"name: "+device.getName()+", address: "+device.getAddress());

		// create new thread
		Thread bluetoothConnectThread = new Thread(this);
		// start thread
		bluetoothConnectThread.start();
		//pairToDevice(mBluetoothDevice); This method is replaced by progress dialog with thread
	}

	// thread to connect with bluetooth device
	@Override
	public void run() {
		try {
			// open socket
			uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
			mBluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);

			// cancel discovering
			//cancelDiscovery(context);
			// already cancelled

			// connect through socket
			mBluetoothSocket.connect();

			Log.d(BluetoothHelper.TAG, "connected to device");

			// send empty message
			//mHandler.sendEmptyMessage(0);

		} catch(IOException e) {
			e.printStackTrace();
			Log.d(BluetoothHelper.TAG, "could not connect to device");
			//hide progress bar
			hideProgress();
			// close the socket
			try {
				mBluetoothSocket.close();
				Log.d(BluetoothHelper.TAG, "socket closed");
			} catch(IOException e1) {
				e1.printStackTrace();
				Log.d(BluetoothHelper.TAG, "socket could not be closed");
			}
		}
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			hideProgress();
			Log.d(BluetoothHelper.TAG, "connected with device");
		}
	};

	/**
	 * make the device discoverable within 300 seconds
	 */
	public void makeDiscoverable() {
		// create new intent
		Intent discoverableIntent = new
				Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
		context.startActivity(discoverableIntent);
	}
}