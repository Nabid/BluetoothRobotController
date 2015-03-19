package com.prome.bluetoothdevicecontroller.helpers;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;

/**
 * @author Nabid
 * @version 1.0
 * @see android.net.ConnectivityManager
 * @ref http://developer.android.com/guide/topics/connectivity/bluetooth.html
 * Helper class on network connectivity i.e internet, bluetooth
 */
public class NetworkHelper {

	// bluetooth adapter
	private static BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	// local integer
	private static int REQUEST_ENABLE_BT = 0;

	/**
	 * Checks the internet connection availability of device
	 * @param context
	 * @return true if internet connection available, false otherwise
	 */
	public static boolean isInternetAvailable( Context context ) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	/**
	 * Checks the device is Bluetooth supported or not
	 * @return true if the device is Bluetooth supported, false otherwise
	 */
	public static boolean isBluetoothSupported() {
		if( bluetoothAdapter == null ) return false;
		return true;
	}

	/**
	 * Enables Bluetooth
	 * @param context
	 */
	public static void enableBluetooth( ActionBarActivity context) {
		if( !bluetoothAdapter.isEnabled() ) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			context.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
	}
}
