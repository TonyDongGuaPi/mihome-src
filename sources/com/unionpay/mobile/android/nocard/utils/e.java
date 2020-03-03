package com.unionpay.mobile.android.nocard.utils;

import android.content.Context;
import com.unionpay.mobile.android.net.c;
import com.unionpay.mobile.android.net.d;

final class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9585a;
    final /* synthetic */ String b;
    final /* synthetic */ Context c;

    e(String str, String str2, Context context) {
        this.f9585a = str;
        this.b = str2;
        this.c = context;
    }

    public final void run() {
        new c(new d(1, this.f9585a, this.b.getBytes()), this.c).a();
    }
}
