package com.unionpay.mobile.android.pro.views;

import android.os.Bundle;
import android.os.Handler;
import com.unionpay.mobile.android.model.b;
import java.util.HashMap;

final class s implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9698a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ k c;

    s(k kVar, String str, HashMap hashMap) {
        this.c = kVar;
        this.f9698a = str;
        this.b = hashMap;
    }

    public final void run() {
        k kVar = this.c;
        String str = this.f9698a;
        b unused = this.c.f9608a;
        Bundle a2 = kVar.a(str, (HashMap<String, String>) this.b);
        Handler w = this.c.z;
        Handler w2 = this.c.z;
        if (a2 == null) {
            a2 = null;
        }
        w.sendMessage(w2.obtainMessage(0, a2));
    }
}
