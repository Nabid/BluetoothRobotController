package com.prome.bluetoothdevicecontroller.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Md. Nabid Imteaj
 * @version 1.0
 * Helper class on network connectivity i.e internet, bluetooth
 * @see android.net.ConnectivityManager
 */
public class NetworkHelper {
	/**
	 * Checks the internet connection availability of device
	 * @param context
	 * @return true if internet connection available, false otherwise
	 */
	public static boolean isInternetAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
