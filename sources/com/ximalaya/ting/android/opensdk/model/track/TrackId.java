package com.ximalaya.ting.android.opensdk.model.track;

import com.google.gson.annotations.SerializedName;

public class TrackId {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2126a;
    @SerializedName("is_paid")
    private boolean b;
    @SerializedName("has_bought")
    private boolean c;

    public long a() {
        return this.f2126a;
    }

    public boolean b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }
}
