package com.meghan.tabsswipe.adapter;

import com.meghan.endlessemoji.ContactFragment;
import com.meghan.endlessemoji.MessageFragment;
import com.meghan.endlessemoji.RepeatsFragment;
import com.meghan.endlessemoji.SendFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            return new MessageFragment();
        case 1:
            return new RepeatsFragment();
        case 2:
            return new ContactFragment();
        case 3:
        	return new SendFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }
 
}