package com.mi.global.bbs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import java.util.List;

public class ScrollFragmentAdapter extends CacheFragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private String[] mTitles;

    public ScrollFragmentAdapter(FragmentManager fragmentManager, String[] strArr, List<Fragment> list) {
        super(fragmentManager);
        this.mTitles = strArr;
        this.mFragments = list;
    }

    public CharSequence getPageTitle(int i) {
        return this.mTitles[i];
    }

    /* access modifiers changed from: protected */
    public Fragment createItem(int i) {
        return this.mFragments.get(i);
    }

    public int getCount() {
        if (this.mFragments == null) {
            return 0;
        }
        return this.mFragments.size();
    }
}
