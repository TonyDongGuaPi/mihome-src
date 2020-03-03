package com.tencent.wxop.stat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class b extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9307a;

    b(a aVar) {
        this.f9307a = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.f9307a.e != null) {
            this.f9307a.e.a(new c(this));
        }
    }
}
