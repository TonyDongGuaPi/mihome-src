package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;

public class XmTrackStatue {

    /* renamed from: a  reason: collision with root package name */
    private int f2035a;
    @SerializedName("is_paid")
    private boolean b;
    @SerializedName("has_bought")
    private boolean c;

    public int a() {
        return this.f2035a;
    }

    public void a(int i) {
        this.f2035a = i;
    }

    public boolean b() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public boolean c() {
        return this.c;
    }

    public void b(boolean z) {
        this.c = z;
    }
}
