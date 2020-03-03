package com.mi.global.bbs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private String[] mTitles;

    public MyViewPagerAdapter(FragmentManager fragmentManager, String[] strArr, List<Fragment> list) {
        super(fragmentManager);
        this.mTitles = strArr;
        this.mFragments = list;
    }

    public CharSequence getPageTitle(int i) {
        return this.mTitles[i];
    }

    public Fragment getItem(int i) {
        return this.mFragments.get(i);
    }

    public int getCount() {
        if (this.mFragments == null) {
            return 0;
        }
        return this.mFragments.size();
    }
}
