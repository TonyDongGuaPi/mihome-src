package com.mi.global.bbs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.mi.global.bbs.ui.HeaderViewPagerFragment;
import java.util.List;

public class MyHeadViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<HeaderViewPagerFragment> mFragments;
    private String[] mTitles;

    public MyHeadViewPagerAdapter(FragmentManager fragmentManager, String[] strArr, List<HeaderViewPagerFragment> list) {
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
