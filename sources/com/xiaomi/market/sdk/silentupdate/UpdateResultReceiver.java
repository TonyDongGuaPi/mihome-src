package com.xiaomi.market.sdk.silentupdate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.xiaomi.market.sdk.AppGlobal;

public class UpdateResultReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final UpdateResultReceiver f11127a = new UpdateResultReceiver();
    private volatile Callback b;
    private volatile boolean c;

    public interface Callback {
        void a(@NonNull Bundle bundle);
    }

    public static UpdateResultReceiver get() {
        return f11127a;
    }

    public void register(Callback callback) {
        this.b = callback;
        if (!this.c) {
            AppGlobal.a().registerReceiver(this, new IntentFilter("com.xiaomi.market.DOWNLOAD_INSTALL_RESULT"));
            this.c = true;
        }
    }

    public void unregister() {
        this.b = null;
        AppGlobal.a().unregisterReceiver(this);
        this.c = false;
    }

    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(context.getPackageName(), intent.getStringExtra("packageName")) && this.b != null && intent.getExtras() != null) {
            this.b.a(intent.getExtras());
        }
    }
}
