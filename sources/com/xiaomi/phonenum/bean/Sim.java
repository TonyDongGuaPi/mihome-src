package com.xiaomi.phonenum.bean;

import android.os.Bundle;
import android.support.annotation.NonNull;

public class Sim {

    /* renamed from: a  reason: collision with root package name */
    public final String f12555a;
    public final String b;
    public final String c;
    public final String d;

    public Sim(@NonNull String str, String str2, String str3, String str4) {
        this.f12555a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("iccid", this.f12555a);
        bundle.putString("imsi", this.b);
        bundle.putString("mccmnc", this.c);
        bundle.putString("line1Number", this.d);
        return bundle;
    }

    public String toString() {
        return a().toString();
    }
}
