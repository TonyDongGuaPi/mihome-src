package com.xiaomi.smarthome.library.common.widget.pulldown;

import android.view.View;
import android.view.animation.Animation;

public interface IPullDownElastic {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19061a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;

    View a();

    void a(int i);

    void a(int i, boolean z);

    void a(Animation animation);

    void a(String str);

    int b();

    void b(int i);

    void b(String str);

    void c();

    void c(int i);
}
