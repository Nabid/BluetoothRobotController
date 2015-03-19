package com.prome.bluetoothdevicecontroller.activities;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.prome.bluetoothdevicecontroller.R;
import com.prome.bluetoothdevicecontroller.adapters.CustomViewPagerAdapter;
import com.prome.bluetoothdevicecontroller.fragments.AboutFragment;
import com.prome.bluetoothdevicecontroller.fragments.CameraControllerFragment;
import com.prome.bluetoothdevicecontroller.fragments.ConnectFragment;
import com.prome.bluetoothdevicecontroller.fragments.TouchControllerFragment;
import com.prome.bluetoothdevicecontroller.fragments.VoiceControllerFragment;
import com.prome.bluetoothdevicecontroller.helpers.NetworkHelper;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

	// private variables
	ViewPager pager;
	// fragment list
	ArrayList<Fragment>fragments;
	// fragment title list
	ArrayList<String>titles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// initiate variables
		init();

		// set viewpager
		pager = (ViewPager) findViewById(R.id.view_pager);
		CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
		customViewPagerAdapter.setFragments(fragments);
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
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
