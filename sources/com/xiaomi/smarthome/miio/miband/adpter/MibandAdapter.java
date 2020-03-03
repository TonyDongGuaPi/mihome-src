package com.xiaomi.smarthome.miio.miband.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class MibandAdapter extends FragmentPagerAdapter {

    /* renamed from: a  reason: collision with root package name */
    private List<Fragment> f19443a;

    public MibandAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.f19443a = list;
    }

    public Fragment getItem(int i) {
        if (this.f19443a == null || i < 0 || this.f19443a.size() <= i) {
            return null;
        }
        return this.f19443a.get(i);
    }

    public int getCount() {
        if (this.f19443a == null) {
            return 0;
        }
        return this.f19443a.size();
    }
}
