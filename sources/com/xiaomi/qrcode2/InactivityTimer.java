package com.xiaomi.qrcode2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;

final class InactivityTimer {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f13007a = "InactivityTimer";
    private static final long b = 300000;
    /* access modifiers changed from: private */
    public final Activity c;
    private final BroadcastReceiver d = new PowerStatusReceiver();
    private boolean e = false;
    private AsyncTask<Object, Object, Object> f;

    InactivityTimer(Activity activity) {
        this.c = activity;
        a();
    }

    /* access modifiers changed from: package-private */
    public synchronized void a() {
        f();
        this.f = new InactivityAsyncTask();
        this.f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
    }

    public synchronized void b() {
        f();
        if (this.e) {
            this.c.unregisterReceiver(this.d);
            this.e = false;
        } else {
            Log.w(f13007a, "PowerStatusReceiver was never registered?");
        }
    }

    public synchronized void c() {
        if (this.e) {
            Log.w(f13007a, "PowerStatusReceiver was already registered?");
        } else {
            this.c.registerReceiver(this.d, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            this.e = true;
        }
        a();
    }

    /* access modifiers changed from: private */
    public synchronized void f() {
        AsyncTask<Object, Object, Object> asyncTask = this.f;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.f = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        f();
    }

    private final class PowerStatusReceiver extends BroadcastReceiver {
        private PowerStatusReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                if (intent.getIntExtra("plugged", -1) <= 0) {
                    InactivityTimer.this.a();
                } else {
                    InactivityTimer.this.f();
                }
            }
        }
    }

    private final class InactivityAsyncTask extends AsyncTask<Object, Object, Object> {
        private InactivityAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(300000);
                Log.i(InactivityTimer.f13007a, "Finishing activity due to inactivity");
                InactivityTimer.this.c.finish();
                return null;
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }
}
