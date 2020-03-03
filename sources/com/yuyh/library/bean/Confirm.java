package com.yuyh.library.bean;

import android.view.View;

public class Confirm {

    /* renamed from: a  reason: collision with root package name */
    public String f2562a;
    public int b = -1;
    public View.OnClickListener c;

    public Confirm(String str) {
        this.f2562a = str;
    }

    public Confirm(String str, int i) {
        this.f2562a = str;
        this.b = i;
    }

    public Confirm(String str, int i, View.OnClickListener onClickListener) {
        this.f2562a = str;
        this.b = i;
        this.c = onClickListener;
    }
}
