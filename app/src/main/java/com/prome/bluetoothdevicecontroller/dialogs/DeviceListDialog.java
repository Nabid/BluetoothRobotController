package com.prome.bluetoothdevicecontroller.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.prome.bluetoothdevicecontroller.helpers.BluetoothHelper;

import java.util.ArrayList;

/**
 * Created by root on 4/11/15.
 */
public class DeviceListDialog extends DialogFragment {
	// tag
	public static final String TAG = "DeviceListDialog";

	//
	public static DeviceListDialog deviceListDialog = null;

	// device array list
	private static ArrayList<backport.android.bluetooth.BluetoothDevice> deviceArrayList;
	// title of the didalog
	private static String title;
	// charsequence of item
	private static CharSequence[] items;

	public DeviceListDialog() {
	}

	// new instance
	public static DeviceListDialog getInstance(String title, ArrayList<backport.android.bluetooth.BluetoothDevice> deviceArrayList) {
		DeviceListDialog.deviceArrayList = deviceArrayList;
		DeviceListDialog.title = title;

		DeviceListDialog.items = new CharSequence[deviceArrayList.size()];
		for(int i = 0; i < deviceArrayList.size(); i++) {
			items[i] = deviceArrayList.get(i).getName() + "\n" + deviceArrayList.get(i).getAddress();
		}
		if(deviceListDialog == null) {
			deviceListDialog = new DeviceListDialog();
		}
		return deviceListDialog;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setTitle(title)
				.setItems(items, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// start connecting to device
						BluetoothHelper.getInstance(getActivity()).connectToBTDevice(deviceArrayList.get(which));
					}
				})
				.setPositiveButton("Hide", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				})
				.create();
	}
}
