package com.unionpay.mobile.android.pro.views;

import android.os.Bundle;
import android.os.Handler;
import com.unionpay.mobile.android.model.b;
import java.util.HashMap;

final class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9689a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ a c;

    g(a aVar, String str, HashMap hashMap) {
        this.c = aVar;
        this.f9689a = str;
        this.b = hashMap;
    }

    public final void run() {
        a aVar = this.c;
        String str = this.f9689a;
        b unused = this.c.f9608a;
        Bundle a2 = aVar.a(str, (HashMap<String, String>) this.b);
        Handler x = this.c.z;
        Handler x2 = this.c.z;
        if (a2 == null) {
            a2 = null;
        }
        x.sendMessage(x2.obtainMessage(0, a2));
    }
}
