package com.xiaomi.smarthome.core.server.internal.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import com.xiaomi.smarthome.core.server.CoreService;
import java.util.ArrayList;
import java.util.List;

public class NetworkManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14584a = -100;
    private static NetworkManager e;
    private static Object f = new Object();
    Context b;
    List<NetworkListener> c = new ArrayList();
    private NetworkBroadcastReceiver d;

    public interface NetworkListener {
        void d();
    }

    public static NetworkManager a() {
        synchronized (f) {
            if (e == null) {
                e = new NetworkManager(CoreService.getAppContext());
            }
        }
        return e;
    }

    public static NetworkManager a(Context context) {
        synchronized (f) {
            if (e == null) {
                e = new NetworkManager(context);
            }
        }
        return e;
    }

    private NetworkManager(Context context) {
        this.b = context;
        b();
    }

    public void b(Context context) {
        this.b = context;
    }

    public void b() {
        this.d = new NetworkBroadcastReceiver(this.b);
        this.b.registerReceiver(this.d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public int c() {
        if (this.d != null) {
            return this.d.b();
        }
        return -100;
    }

    public int d() {
        if (this.d != null) {
            return this.d.a();
        }
        return -100;
    }

    class NetworkBroadcastReceiver extends BroadcastReceiver {
        private int b = -100;
        private int c = -100;
        private String d;

        /* access modifiers changed from: private */
        public int a() {
            return this.c;
        }

        /* access modifiers changed from: private */
        public int b() {
            return this.b;
        }

        public NetworkBroadcastReceiver(Context context) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                this.b = -100;
                return;
            }
            this.b = activeNetworkInfo.getType();
            if (activeNetworkInfo.getType() == 1) {
                this.d = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getSSID();
            }
        }

        public void onReceive(Context context, Intent intent) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    this.c = activeNetworkInfo.getType();
                    if (this.c != this.b) {
                        if (activeNetworkInfo.getType() == 1) {
                            this.d = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getSSID();
                        } else {
                            this.d = null;
                        }
                        if (this.c == 1 || this.b == 1 || this.b == -100) {
                            NetworkManager.this.g();
                        }
                    } else if (activeNetworkInfo.getType() == 1) {
                        String ssid = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getSSID();
                        if (this.d == null || ssid == null || !ssid.equals(this.d)) {
                            NetworkManager.this.g();
                        }
                        this.d = ssid;
                    } else {
                        this.d = null;
                    }
                    this.b = this.c;
                    return;
                }
                this.c = -100;
                if (this.b != -100) {
                    NetworkManager.this.g();
                }
                this.b = -100;
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        int i = 0;
        while (i < this.c.size()) {
            try {
                this.c.get(i).d();
            } catch (Exception unused) {
                this.c.remove(i);
                i--;
            }
            i++;
        }
    }

    public void a(NetworkListener networkListener) {
        if (networkListener != null) {
            for (NetworkListener next : this.c) {
                if (next != null && next.equals(networkListener)) {
                    return;
                }
            }
            this.c.add(networkListener);
        }
    }

    public void b(NetworkListener networkListener) {
        if (networkListener != null) {
            this.c.remove(networkListener);
        }
    }

    public boolean e() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.b.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public boolean f() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.b.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
