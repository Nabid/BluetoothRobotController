package com.prome.bluetoothdevicecontroller.helpers;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * DisplayHelper (Singleton) class computes information
 * about the screen and returns them.
 */

public class DisplayHelper {
	// private fields
	private Context context;
	// width and height
	public static int width, height;

	// singleton
	private static DisplayHelper displayHelper = null;

	// get an instance of singleton
	public static DisplayHelper getInstance( Context context ) {
		// check if null
		if( displayHelper == null ) {
			// create new object
			displayHelper = new DisplayHelper(context);
		}

		return displayHelper;
	}

	// constructor
	private DisplayHelper(Context context) {
		this.context = context;

		// compute width and height
		getDisplayMetrics();
	}

	private void getDisplayMetrics() {
		// define display metrics
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		// set width
		width = displayMetrics.widthPixels;
		// set height
		height = displayMetrics.heightPixels;
	}
}
