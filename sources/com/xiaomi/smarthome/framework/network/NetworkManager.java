package com.xiaomi.smarthome.framework.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import com.xiaomi.smarthome.application.ApplicationLifeCycle;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.framework.api.RouterLocalApi;
import java.util.ArrayList;
import java.util.List;

public class NetworkManager extends ApplicationLifeCycle {

    /* renamed from: a  reason: collision with root package name */
    public static final int f16680a = -100;
    private static NetworkManager d;
    Context b = CommonApplication.getAppContext();
    List<NetworkListener> c = new ArrayList();
    private NetworkBroadcastReceiver e = new NetworkBroadcastReceiver();

    public interface NetworkListener {
        void a();

        void b();
    }

    private NetworkManager() {
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.b.registerReceiver(this.e, new IntentFilter(intentFilter));
        this.b.registerReceiver(this.e, new IntentFilter("android.net.wifi.STATE_CHANGE"));
    }

    public static NetworkManager a() {
        if (d == null) {
            d = new NetworkManager();
        }
        return d;
    }

    public int c() {
        if (this.e != null) {
            return this.e.b();
        }
        return -100;
    }

    public int d() {
        if (this.e != null) {
            return this.e.a();
        }
        return -100;
    }

    /* access modifiers changed from: private */
    public void f() {
        RouterLocalApi.a().c();
        int i = 0;
        while (i < this.c.size()) {
            try {
                this.c.get(i).a();
            } catch (Exception unused) {
                this.c.remove(i);
                i--;
            }
            i++;
        }
    }

    public void a(NetworkListener networkListener) {
        if (networkListener != null) {
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

    class NetworkBroadcastReceiver extends BroadcastReceiver {
        /* access modifiers changed from: private */
        public volatile int b = -100;
        /* access modifiers changed from: private */
        public volatile int c = -100;
        /* access modifiers changed from: private */
        public volatile String d;

        public NetworkBroadcastReceiver() {
            CommonApplication.getGlobalWorkerHandler().post(new Runnable(NetworkManager.this) {
                public void run() {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) CommonApplication.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo == null) {
                        int unused = NetworkBroadcastReceiver.this.c = NetworkBroadcastReceiver.this.b = -100;
                        return;
                    }
                    int unused2 = NetworkBroadcastReceiver.this.c = NetworkBroadcastReceiver.this.b = activeNetworkInfo.getType();
                    if (activeNetworkInfo.getType() == 1) {
                        try {
                            String unused3 = NetworkBroadcastReceiver.this.d = ((WifiManager) CommonApplication.getAppContext().getApplicationContext().getSystemService("wifi")).getConnectionInfo().getSSID();
                        } catch (Exception unused4) {
                        }
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        public int a() {
            return this.c;
        }

        /* access modifiers changed from: private */
        public int b() {
            return this.b;
        }

        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0025 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(final android.content.Context r3, final android.content.Intent r4) {
            /*
                r2 = this;
                java.lang.String r0 = r4.getAction()     // Catch:{ Exception -> 0x003d }
                java.lang.String r1 = "android.intent.action.SCREEN_OFF"
                boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x003d }
                if (r0 == 0) goto L_0x0031
                r3 = 0
            L_0x000d:
                com.xiaomi.smarthome.framework.network.NetworkManager r4 = com.xiaomi.smarthome.framework.network.NetworkManager.this     // Catch:{ Exception -> 0x003d }
                java.util.List<com.xiaomi.smarthome.framework.network.NetworkManager$NetworkListener> r4 = r4.c     // Catch:{ Exception -> 0x003d }
                int r4 = r4.size()     // Catch:{ Exception -> 0x003d }
                if (r3 >= r4) goto L_0x003d
                com.xiaomi.smarthome.framework.network.NetworkManager r4 = com.xiaomi.smarthome.framework.network.NetworkManager.this     // Catch:{ Exception -> 0x003d }
                java.util.List<com.xiaomi.smarthome.framework.network.NetworkManager$NetworkListener> r4 = r4.c     // Catch:{ Exception -> 0x003d }
                java.lang.Object r4 = r4.get(r3)     // Catch:{ Exception -> 0x003d }
                com.xiaomi.smarthome.framework.network.NetworkManager$NetworkListener r4 = (com.xiaomi.smarthome.framework.network.NetworkManager.NetworkListener) r4     // Catch:{ Exception -> 0x003d }
                r4.b()     // Catch:{ Exception -> 0x0025 }
                goto L_0x002e
            L_0x0025:
                com.xiaomi.smarthome.framework.network.NetworkManager r4 = com.xiaomi.smarthome.framework.network.NetworkManager.this     // Catch:{ Exception -> 0x003d }
                java.util.List<com.xiaomi.smarthome.framework.network.NetworkManager$NetworkListener> r4 = r4.c     // Catch:{ Exception -> 0x003d }
                r4.remove(r3)     // Catch:{ Exception -> 0x003d }
                int r3 = r3 + -1
            L_0x002e:
                int r3 = r3 + 1
                goto L_0x000d
            L_0x0031:
                android.os.Handler r0 = com.xiaomi.smarthome.application.CommonApplication.getGlobalWorkerHandler()     // Catch:{ Exception -> 0x003d }
                com.xiaomi.smarthome.framework.network.NetworkManager$NetworkBroadcastReceiver$2 r1 = new com.xiaomi.smarthome.framework.network.NetworkManager$NetworkBroadcastReceiver$2     // Catch:{ Exception -> 0x003d }
                r1.<init>(r3, r4)     // Catch:{ Exception -> 0x003d }
                r0.post(r1)     // Catch:{ Exception -> 0x003d }
            L_0x003d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.network.NetworkManager.NetworkBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }
}
