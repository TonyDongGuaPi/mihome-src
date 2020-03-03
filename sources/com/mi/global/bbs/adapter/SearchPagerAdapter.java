package com.mi.global.bbs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.mi.global.bbs.ui.SearchFragment;
import java.util.List;

public class SearchPagerAdapter extends FragmentPagerAdapter {
    private List<SearchFragment> fragmentList;
    private CharSequence[] title;

    public SearchPagerAdapter(FragmentManager fragmentManager, CharSequence[] charSequenceArr, List<SearchFragment> list) {
        super(fragmentManager);
        this.title = charSequenceArr;
        this.fragmentList = list;
    }

    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    public int getCount() {
        if (this.fragmentList == null) {
            return 0;
        }
        return this.fragmentList.size();
    }

    public CharSequence getPageTitle(int i) {
        return this.title[i];
    }
}
