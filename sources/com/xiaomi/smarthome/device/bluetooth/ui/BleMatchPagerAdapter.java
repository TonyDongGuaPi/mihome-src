package com.xiaomi.smarthome.device.bluetooth.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class BleMatchPagerAdapter extends FragmentPagerAdapter {

    /* renamed from: a  reason: collision with root package name */
    private List<BleMatchFragment> f15249a;

    public BleMatchPagerAdapter(FragmentManager fragmentManager, List<BleMatchFragment> list) {
        super(fragmentManager);
        a(list);
    }

    private void a(List<BleMatchFragment> list) {
        this.f15249a = list;
        if (list == null) {
            this.f15249a = new ArrayList();
        }
    }

    public Fragment getItem(int i) {
        if (i < 0 || i >= this.f15249a.size()) {
            return null;
        }
        return this.f15249a.get(i);
    }

    public int getCount() {
        if (this.f15249a != null) {
            return this.f15249a.size();
        }
        return 0;
    }
}
