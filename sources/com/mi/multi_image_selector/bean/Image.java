package com.mi.multi_image_selector.bean;

import android.text.TextUtils;

public class Image {

    /* renamed from: a  reason: collision with root package name */
    public String f7396a;
    public String b;
    public long c;

    public Image(String str, String str2, long j) {
        this.f7396a = str;
        this.b = str2;
        this.c = j;
    }

    public boolean equals(Object obj) {
        try {
            return TextUtils.equals(this.f7396a, ((Image) obj).f7396a);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return super.equals(obj);
        }
    }
}
