package com.xiaomi.push;

import android.content.SharedPreferences;

class q implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f12842a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ p d;

    q(p pVar, String str, String str2, String str3) {
        this.d = pVar;
        this.f12842a = str;
        this.b = str2;
        this.c = str3;
    }

    public void run() {
        SharedPreferences.Editor edit = this.d.b.getSharedPreferences(this.f12842a, 4).edit();
        edit.putString(this.b, this.c);
        edit.commit();
    }
}
