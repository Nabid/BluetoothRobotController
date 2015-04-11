package com.prome.bluetoothdevicecontroller.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * @author Md. Nabid Imteaj
 * @version v1.0
 * @see android.support.v4.app.FragmentPagerAdapter
 * @see android.support.v4.view.PagerAdapter
 * Custom State ViewPager Adapter
 */
public class CustomStateViewPagerAdapter extends FragmentStatePagerAdapter {

	// fragment list
	private ArrayList<Fragment> fragments;
	// fragment titles
	private ArrayList<String> titles;

	// default constructor
	public CustomStateViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	// initialize fragment list
	public void setFragments(ArrayList<Fragment> fragments) {
		this.fragments = fragments;
	}

	// initialize fragment titles
	public void setTitles(ArrayList<String> titles) {
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
