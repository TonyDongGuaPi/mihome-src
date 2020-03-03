package com.xiaomi.smarthome.shop.utils;

import android.widget.AbsListView;

public class PageScrollListener implements AbsListView.OnScrollListener {

    /* renamed from: a  reason: collision with root package name */
    private Runnable f22201a;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;

    public PageScrollListener(Runnable runnable) {
        this.f22201a = runnable;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        boolean z = false;
        this.b = i3 == i + i2;
        if (i3 == i2) {
            z = true;
        }
        this.c = z;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0 && this.b && this.f22201a != null) {
            if (this.d || !this.c) {
                this.f22201a.run();
            }
        }
    }
}
