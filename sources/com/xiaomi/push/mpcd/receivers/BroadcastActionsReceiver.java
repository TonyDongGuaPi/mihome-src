package com.xiaomi.push.mpcd.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.push.dy;

public class BroadcastActionsReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private dy f12837a;

    public BroadcastActionsReceiver(dy dyVar) {
        this.f12837a = dyVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.f12837a != null) {
            this.f12837a.a(context, intent);
        }
    }
}
