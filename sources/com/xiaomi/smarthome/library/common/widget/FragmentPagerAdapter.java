package com.xiaomi.smarthome.library.common.widget;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public abstract class FragmentPagerAdapter extends PagerAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18842a = "FragmentPagerAdapter";
    private static final boolean d = false;
    private final FragmentManager e;
    private FragmentTransaction f = null;
    private Fragment g = null;

    public abstract Fragment a(int i);

    public void a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public void a(View view) {
    }

    public Parcelable b() {
        return null;
    }

    public FragmentPagerAdapter(FragmentManager fragmentManager) {
        this.e = fragmentManager;
    }

    public Object a(View view, int i) {
        if (this.f == null) {
            this.f = this.e.beginTransaction();
        }
        Fragment findFragmentByTag = this.e.findFragmentByTag(a(view.getId(), i));
        if (findFragmentByTag != null) {
            this.f.attach(findFragmentByTag);
        } else {
            findFragmentByTag = a(i);
            this.f.add(view.getId(), findFragmentByTag, a(view.getId(), i));
        }
        if (findFragmentByTag != this.g) {
            findFragmentByTag.setMenuVisibility(false);
        }
        return findFragmentByTag;
    }

    public void a(View view, int i, Object obj) {
        if (this.f == null) {
            this.f = this.e.beginTransaction();
        }
        this.f.detach((Fragment) obj);
    }

    public void b(View view, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.g) {
            if (this.g != null) {
                this.g.setMenuVisibility(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
            }
            this.g = fragment;
        }
    }

    public void b(View view) {
        if (this.f != null) {
            this.f.commitAllowingStateLoss();
            this.f = null;
            this.e.executePendingTransactions();
        }
    }

    public boolean a(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    private static String a(int i, int i2) {
        return "android:switcher:" + i + ":" + i2;
    }

    public FragmentManager c() {
        return this.e;
    }
}
