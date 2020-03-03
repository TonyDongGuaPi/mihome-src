package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.util.Preconditions;

final class DefaultConnectivityMonitor implements ConnectivityMonitor {
    private static final String c = "ConnectivityMonitor";

    /* renamed from: a  reason: collision with root package name */
    final ConnectivityMonitor.ConnectivityListener f5037a;
    boolean b;
    private final Context d;
    private boolean e;
    private final BroadcastReceiver f = new BroadcastReceiver() {
        public void onReceive(@NonNull Context context, Intent intent) {
            boolean z = DefaultConnectivityMonitor.this.b;
            DefaultConnectivityMonitor.this.b = DefaultConnectivityMonitor.this.a(context);
            if (z != DefaultConnectivityMonitor.this.b) {
                if (Log.isLoggable(DefaultConnectivityMonitor.c, 3)) {
                    Log.d(DefaultConnectivityMonitor.c, "connectivity changed, isConnected: " + DefaultConnectivityMonitor.this.b);
                }
                DefaultConnectivityMonitor.this.f5037a.a(DefaultConnectivityMonitor.this.b);
            }
        }
    };

    public void i() {
    }

    DefaultConnectivityMonitor(@NonNull Context context, @NonNull ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.d = context.getApplicationContext();
        this.f5037a = connectivityListener;
    }

    private void a() {
        if (!this.e) {
            this.b = a(this.d);
            try {
                this.d.registerReceiver(this.f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                this.e = true;
            } catch (SecurityException e2) {
                if (Log.isLoggable(c, 5)) {
                    Log.w(c, "Failed to register", e2);
                }
            }
        }
    }

    private void b() {
        if (this.e) {
            this.d.unregisterReceiver(this.f);
            this.e = false;
        }
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"MissingPermission"})
    public boolean a(@NonNull Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) Preconditions.a((ConnectivityManager) context.getSystemService("connectivity"))).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (RuntimeException e2) {
            if (Log.isLoggable(c, 5)) {
                Log.w(c, "Failed to determine connectivity status when connectivity changed", e2);
            }
            return true;
        }
    }

    public void g() {
        a();
    }

    public void h() {
        b();
    }
}
