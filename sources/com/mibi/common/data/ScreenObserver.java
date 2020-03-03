package com.mibi.common.data;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.mibi.common.base.BaseBroadcastReceiver;

public class ScreenObserver {

    /* renamed from: a  reason: collision with root package name */
    private Context f7540a;
    private ScreenBroadcastReceiver b;
    /* access modifiers changed from: private */
    public ScreenStateListener c;

    public interface ScreenStateListener {
        void a();

        void b();
    }

    private class ScreenBroadcastReceiver extends BaseBroadcastReceiver {
        private String b;

        private ScreenBroadcastReceiver() {
            this.b = null;
        }

        public void onReceive(Context context, Intent intent) {
            this.b = intent.getAction();
            if (ScreenObserver.this.c == null) {
                return;
            }
            if ("android.intent.action.SCREEN_ON".equals(this.b)) {
                ScreenObserver.this.c.a();
            } else if ("android.intent.action.SCREEN_OFF".equals(this.b)) {
                ScreenObserver.this.c.b();
            }
        }
    }

    public void a(Context context, ScreenStateListener screenStateListener) {
        this.f7540a = context;
        this.b = new ScreenBroadcastReceiver();
        this.c = screenStateListener;
        b();
    }

    public void a() {
        this.f7540a.unregisterReceiver(this.b);
        this.f7540a = null;
        this.b = null;
        this.c = null;
    }

    private void b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.f7540a.registerReceiver(this.b, intentFilter);
    }
}
