package com.bifel.testtaskforwork;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bifel.testtaskforwork.screens.Tab1;
import com.bifel.testtaskforwork.screens.Tab2;
import com.bifel.testtaskforwork.screens.Tab3;

class PageAdapter extends FragmentPagerAdapter {

    PageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return Tab1.getInstance();
            case 1:
                return Tab2.getInstance();
            case 2:
                return Tab3.getInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position + 1);
    }
}
