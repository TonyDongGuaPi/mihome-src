package com.miuipub.internal.app;

import android.app.Fragment;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import miuipub.app.ActionBar;
import miuipub.util.ViewUtils;
import miuipub.view.ViewPager;

class ViewPagerScrollEffect implements ActionBar.FragmentViewPagerChangeListener {

    /* renamed from: a  reason: collision with root package name */
    Rect f8242a = new Rect();
    ArrayList<View> b = new ArrayList<>();
    int c = -1;
    boolean d = true;
    int e = -1;
    int f = -1;
    ViewGroup g = null;
    ViewPager h;
    DynamicFragmentPagerAdapter i;

    public void onPageSelected(int i2) {
    }

    public ViewPagerScrollEffect(ViewPager viewPager, DynamicFragmentPagerAdapter dynamicFragmentPagerAdapter) {
        this.h = viewPager;
        this.i = dynamicFragmentPagerAdapter;
    }

    public void onPageScrolled(int i2, float f2, boolean z, boolean z2) {
        if (f2 == 0.0f) {
            this.c = i2;
            this.d = true;
            if (this.g != null) {
                a(this.g);
            }
        }
        if (this.e != i2) {
            if (this.c < i2) {
                this.c = i2;
            } else {
                int i3 = i2 + 1;
                if (this.c > i3) {
                    this.c = i3;
                }
            }
            this.e = i2;
            this.d = true;
            if (this.g != null) {
                a(this.g);
            }
        }
        if (f2 > 0.0f) {
            if (this.d) {
                this.d = false;
                if (this.c == i2) {
                    this.f = i2 + 1;
                } else {
                    this.f = i2;
                }
                Fragment a2 = this.i.a(this.f, false);
                this.g = null;
                if (!(a2 == null || a2.getView() == null)) {
                    View findViewById = a2.getView().findViewById(16908298);
                    if (findViewById instanceof ViewGroup) {
                        this.g = (ViewGroup) findViewById;
                    }
                }
            }
            if (this.f == i2) {
                f2 = 1.0f - f2;
            }
            float f3 = f2;
            if (this.g != null) {
                a(this.g, this.g.getWidth(), this.g.getHeight(), f3, this.f != i2);
            }
        }
    }

    public void onPageScrollStateChanged(int i2) {
        if (i2 == 0) {
            this.c = this.h.getCurrentItem();
            this.d = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup, ArrayList<View> arrayList) {
        a(arrayList, viewGroup);
        arrayList.clear();
        ViewUtils.a(viewGroup, this.f8242a);
        if (!this.f8242a.isEmpty()) {
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt.getVisibility() != 8 || childAt.getHeight() > 0) {
                    arrayList.add(childAt);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ArrayList<View> arrayList, ViewGroup viewGroup) {
        Iterator<View> it = arrayList.iterator();
        while (it.hasNext()) {
            View next = it.next();
            if (viewGroup.indexOfChild(next) == -1 && next.getTranslationX() != 0.0f) {
                next.setTranslationX(0.0f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup) {
        a(viewGroup, this.b);
        if (!this.b.isEmpty()) {
            Iterator<View> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().setTranslationX(0.0f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup, int i2, int i3, float f2, boolean z) {
        a(viewGroup, this.b);
        if (!this.b.isEmpty()) {
            int i4 = 0;
            int top = this.b.get(0).getTop();
            int i5 = Integer.MAX_VALUE;
            Iterator<View> it = this.b.iterator();
            while (it.hasNext()) {
                View next = it.next();
                if (i5 != next.getTop()) {
                    int top2 = next.getTop();
                    int a2 = a(top2 - top, i2, i3, f2);
                    if (!z) {
                        a2 = -a2;
                    }
                    int i6 = a2;
                    i5 = top2;
                    i4 = i6;
                }
                next.setTranslationX((float) i4);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int a(int i2, int i3, int i4, float f2) {
        float f3 = ((float) (i2 < i4 ? (i2 * i3) / i4 : i3)) + ((0.1f - ((f2 * f2) / 0.9f)) * ((float) i3));
        if (f3 > 0.0f) {
            return (int) f3;
        }
        return 0;
    }
}
