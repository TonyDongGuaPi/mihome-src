package com.mi.global.bbs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.mi.global.bbs.ui.WatchImageFragment;

public class ImgFragmentPageAdapter extends FragmentStatePagerAdapter {
    private String[] imgUrls;

    public ImgFragmentPageAdapter(FragmentManager fragmentManager, String[] strArr) {
        super(fragmentManager);
        this.imgUrls = strArr;
    }

    public Fragment getItem(int i) {
        return WatchImageFragment.newInstance(this.imgUrls[i]);
    }

    public int getCount() {
        if (this.imgUrls == null) {
            return 0;
        }
        return this.imgUrls.length;
    }
}
