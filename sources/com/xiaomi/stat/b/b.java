package com.xiaomi.stat.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.stat.d.k;

class b extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f23024a;

    b(a aVar) {
        this.f23024a = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        try {
            if (this.f23024a.u != 1) {
                context.unregisterReceiver(this.f23024a.x);
            } else {
                e.a().execute(new c(this));
            }
        } catch (Exception e) {
            k.d("ConfigManager", "mNetReceiver exception", e);
        }
    }
}