package com.miuipub.internal.app;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import miuipub.util.Versions;
import miuipub.view.PagerAdapter;
import miuipub.view.ViewPager;

class DynamicFragmentPagerAdapter extends PagerAdapter {

    /* renamed from: a  reason: collision with root package name */
    private Context f8238a;
    private FragmentManager b;
    private ArrayList<FragmentInfo> e = new ArrayList<>();
    private FragmentTransaction f = null;
    private Fragment g = null;

    public void a(ViewGroup viewGroup) {
    }

    class FragmentInfo {

        /* renamed from: a  reason: collision with root package name */
        String f8239a;
        Class<? extends Fragment> b;
        Fragment c = null;
        Bundle d;
        ActionBar.Tab e;
        boolean f;

        FragmentInfo(String str, Class<? extends Fragment> cls, Bundle bundle, ActionBar.Tab tab, boolean z) {
            this.f8239a = str;
            this.b = cls;
            this.d = bundle;
            this.e = tab;
            this.f = z;
        }
    }

    public DynamicFragmentPagerAdapter(Context context, FragmentManager fragmentManager, ViewPager viewPager) {
        this.f8238a = context;
        this.b = fragmentManager;
        viewPager.setAdapter(this);
    }

    public void a(ViewGroup viewGroup, int i, Object obj) {
        if (this.f == null) {
            this.f = this.b.beginTransaction();
        }
        this.f.detach((Fragment) obj);
    }

    public void b(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.g) {
            if (this.g != null) {
                this.g.setMenuVisibility(false);
                if (Versions.f()) {
                    this.g.setUserVisibleHint(false);
                }
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                if (Versions.f()) {
                    fragment.setUserVisibleHint(true);
                }
            }
            this.g = fragment;
        }
    }

    public void b(ViewGroup viewGroup) {
        if (this.f != null) {
            this.f.commitAllowingStateLoss();
            this.f = null;
            this.b.executePendingTransactions();
        }
    }

    public boolean a(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    public Object a(ViewGroup viewGroup, int i) {
        if (this.f == null) {
            this.f = this.b.beginTransaction();
        }
        Fragment a2 = a(i, true);
        if (a2.getFragmentManager() != null) {
            this.f.attach(a2);
        } else {
            this.f.add(viewGroup.getId(), a2, this.e.get(i).f8239a);
        }
        if (a2 != this.g) {
            a2.setMenuVisibility(false);
            if (Versions.f()) {
                a2.setUserVisibleHint(false);
            }
        }
        return a2;
    }

    public int a() {
        return this.e.size();
    }

    public boolean a(int i) {
        return this.e.get(i).f;
    }

    public int a(Object obj) {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            if (obj == this.e.get(i).c) {
                return i;
            }
        }
        return -2;
    }

    /* access modifiers changed from: package-private */
    public ActionBar.Tab b(int i) {
        return this.e.get(i).e;
    }

    /* access modifiers changed from: package-private */
    public Fragment a(int i, boolean z) {
        FragmentInfo fragmentInfo = this.e.get(i);
        if (fragmentInfo.c == null) {
            fragmentInfo.c = this.b.findFragmentByTag(fragmentInfo.f8239a);
            if (fragmentInfo.c == null && z) {
                fragmentInfo.c = Fragment.instantiate(this.f8238a, fragmentInfo.b.getName(), fragmentInfo.d);
                fragmentInfo.b = null;
                fragmentInfo.d = null;
            }
        }
        return fragmentInfo.c;
    }

    /* access modifiers changed from: package-private */
    public int a(String str, Class<? extends Fragment> cls, Bundle bundle, ActionBar.Tab tab, boolean z) {
        this.e.add(new FragmentInfo(str, cls, bundle, tab, z));
        d();
        return this.e.size() - 1;
    }

    /* access modifiers changed from: package-private */
    public int a(String str, int i, Class<? extends Fragment> cls, Bundle bundle, ActionBar.Tab tab, boolean z) {
        this.e.add(i, new FragmentInfo(str, cls, bundle, tab, z));
        d();
        return i;
    }

    /* access modifiers changed from: package-private */
    public void c(int i) {
        b(a(i, false));
        this.e.remove(i);
        d();
    }

    /* access modifiers changed from: package-private */
    public int a(String str) {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            if (this.e.get(i).f8239a.equals(str)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int a(ActionBar.Tab tab) {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            FragmentInfo fragmentInfo = this.e.get(i);
            if (fragmentInfo.e == tab) {
                b(fragmentInfo.c);
                this.e.remove(i);
                d();
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int a(Fragment fragment) {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            if (a(i, false) == fragment) {
                b(fragment);
                this.e.remove(i);
                d();
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        e();
        this.e.clear();
        d();
    }

    /* access modifiers changed from: package-private */
    public void b(int i, boolean z) {
        FragmentInfo fragmentInfo = this.e.get(i);
        if (fragmentInfo.f != z) {
            fragmentInfo.f = z;
            d();
        }
    }

    private void e() {
        FragmentTransaction beginTransaction = this.b.beginTransaction();
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            beginTransaction.remove(a(i, false));
        }
        beginTransaction.commitAllowingStateLoss();
        this.b.executePendingTransactions();
    }

    private void b(Fragment fragment) {
        FragmentManager fragmentManager;
        if (fragment != null && (fragmentManager = fragment.getFragmentManager()) != null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.remove(fragment);
            beginTransaction.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }
}
