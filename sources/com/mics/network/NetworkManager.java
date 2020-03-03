package com.mics.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.mics.core.MiCS;

public class NetworkManager {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7769a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private int d;
    private ConnectivityManager e;
    private NetworkListener f;
    private BroadcastReceiver g;

    public interface NetworkListener {
        void a(int i, boolean z);
    }

    private NetworkManager() {
        this.d = 0;
        this.g = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                    NetworkManager.this.d();
                }
            }
        };
        this.e = (ConnectivityManager) MiCS.a().h().getSystemService("connectivity");
        c();
    }

    private static class Holder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final NetworkManager f7771a = new NetworkManager();

        private Holder() {
        }
    }

    public static NetworkManager a() {
        return Holder.f7771a;
    }

    private void c() {
        MiCS.a().h().registerReceiver(this.g, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /* access modifiers changed from: private */
    public void d() {
        NetworkInfo activeNetworkInfo = this.e.getActiveNetworkInfo();
        int i = 1;
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            i = 0;
        } else if (!(1 == activeNetworkInfo.getType() || 9 == activeNetworkInfo.getType())) {
            i = 2;
        }
        if (this.d != i) {
            this.d = i;
            if (this.f != null) {
                this.f.a(i, b());
            }
        }
    }

    public void a(NetworkListener networkListener) {
        this.f = networkListener;
        d();
    }

    public static boolean b() {
        return a().d != 0;
    }
}
