package com.mi.global.shop.base.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import java.util.ArrayList;
import java.util.List;

public class FragmentUtil {
    public static List<Fragment> a(FragmentManager fragmentManager) {
        new ArrayList();
        ArrayList arrayList = new ArrayList();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null) {
            return arrayList;
        }
        for (Fragment next : fragments) {
            if (next != null && next.isVisible()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
