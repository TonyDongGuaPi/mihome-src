package com.xiaomi.stat.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class h extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ g f23041a;

    h(g gVar) {
        this.f23041a = gVar;
    }

    public void onReceive(Context context, Intent intent) {
        this.f23041a.sendEmptyMessage(3);
    }
}
