package com.mobikwik.sdk.ui.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.ui.util.d;

class e extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d.b f8422a;

    e(d.b bVar) {
        this.f8422a = bVar;
    }

    public void onReceive(Context context, Intent intent) {
        System.currentTimeMillis();
        long unused = this.f8422a.h;
        this.f8422a.f();
        this.f8422a.b();
        String stringExtra = intent.getStringExtra("otpValue");
        Utils.print("BroadcastReceiver onReceive " + stringExtra);
        if (this.f8422a.f != null) {
            this.f8422a.f.a(stringExtra);
        }
    }
}
