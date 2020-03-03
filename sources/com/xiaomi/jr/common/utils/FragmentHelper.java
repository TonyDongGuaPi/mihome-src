package com.xiaomi.jr.common.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentHelper {
    public static Fragment a(FragmentManager fragmentManager, int i, Class<? extends Fragment> cls, Bundle bundle, String str) {
        try {
            Fragment fragment = (Fragment) cls.newInstance();
            fragment.setArguments(bundle);
            if (b(fragmentManager, i, fragment, str)) {
                return fragment;
            }
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static boolean a(FragmentManager fragmentManager, int i, Fragment fragment, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        try {
            beginTransaction.add(i, fragment, str);
            beginTransaction.commitAllowingStateLoss();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(FragmentManager fragmentManager, int i, Fragment fragment, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        try {
            beginTransaction.replace(i, fragment, str);
            beginTransaction.commitAllowingStateLoss();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        try {
            beginTransaction.remove(fragment);
            beginTransaction.commitAllowingStateLoss();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
