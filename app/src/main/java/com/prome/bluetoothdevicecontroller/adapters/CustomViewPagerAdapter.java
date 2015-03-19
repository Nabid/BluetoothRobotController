package com.prome.bluetoothdevicecontroller.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Custom ViewPager Adapter
 */
public class CustomViewPagerAdapter extends FragmentPagerAdapter {

	// fragment list
	private ArrayList<Fragment>fragments;
	// fragment titles
	private ArrayList<String>titles;

	// default constructor
	public CustomViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	// initialize fragment list
	public void setFragments(ArrayList<Fragment>fragments) {
		this.fragments = fragments;
	}

	// initialize fragment titles
	public void setTitles(ArrayList<String>titles) {
		this.titles = titles;
	}

	@Override
	public Fragment getItem(int i) {
		return fragments.get(i);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		//return super.getPageTitle(position);
		return titles.get(position);
	}
}
