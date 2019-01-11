package com.bifel.testtaskforwork;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bifel.testtaskforwork.screens.tab1.Tab1;
import com.bifel.testtaskforwork.screens.tab2.Tab2;
import com.bifel.testtaskforwork.screens.tab3.Tab3;

class PageAdapter extends FragmentPagerAdapter {

    private final int numberOfTabs;
    private final Fragment[] tabs;

    PageAdapter(FragmentManager fragmentManager, int numberOfTabs) {
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
        tabs = new Fragment[]{new Tab1(), new Tab2(), new Tab3()};
    }

    @Override
    public Fragment getItem(int i) {
        return tabs[i];
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position + 1);
    }
}
