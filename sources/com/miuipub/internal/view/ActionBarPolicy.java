package com.miuipub.internal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import miuipub.util.AttributeResolver;
import miuipub.v6.R;

public class ActionBarPolicy {

    /* renamed from: a  reason: collision with root package name */
    private Context f8287a;

    public static ActionBarPolicy a(Context context) {
        return new ActionBarPolicy(context);
    }

    private ActionBarPolicy(Context context) {
        this.f8287a = context;
    }

    public int a() {
        return this.f8287a.getResources().getInteger(R.integer.v6_abc_max_action_buttons);
    }

    public boolean b() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public int c() {
        return this.f8287a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public boolean d() {
        return AttributeResolver.a(this.f8287a, R.attr.v6_actionBarEmbedTabs, false);
    }

    public boolean e() {
        return AttributeResolver.a(this.f8287a, R.attr.v6_actionBarTightTitle, false);
    }

    public int f() {
        TypedArray obtainStyledAttributes = this.f8287a.obtainStyledAttributes((AttributeSet) null, R.styleable.V6_ActionBar, 16843508, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.V6_ActionBar_android_height, 0);
        obtainStyledAttributes.recycle();
        if (layoutDimension > 0) {
            return layoutDimension;
        }
        TypedArray obtainStyledAttributes2 = this.f8287a.obtainStyledAttributes((AttributeSet) null, R.styleable.V6_ActionBar, 16843470, 0);
        int layoutDimension2 = obtainStyledAttributes2.getLayoutDimension(R.styleable.V6_ActionBar_android_height, 0);
        obtainStyledAttributes2.recycle();
        return layoutDimension2;
    }

    public boolean g() {
        return this.f8287a.getApplicationInfo().targetSdkVersion < 14;
    }

    public int h() {
        return this.f8287a.getResources().getDimensionPixelSize(R.dimen.v6_action_bar_stacked_tab_max_width);
    }
}
