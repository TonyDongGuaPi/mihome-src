package com.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class NetworkMonitor extends BroadcastReceiver {
    public static final int INVALID_TYPE = -1;
    static final String TAG = "NetworkMonitor";
    private static final int b = -1;
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;

    /* renamed from: a  reason: collision with root package name */
    private int f668a = -1;
    private int f = -1;
    private CopyOnWriteArraySet<OnNetworkChange> g = new CopyOnWriteArraySet<>();
    private WeakReference<Context> h;

    public interface OnNetworkChange {
        void onChange(int i);

        void onConnected(int i);

        void onConnecting(int i);

        void onDisconnected(int i);
    }

    public void addListener(OnNetworkChange onNetworkChange) {
        if (onNetworkChange != null) {
            this.g.add(onNetworkChange);
        }
    }

    public void removeListener(OnNetworkChange onNetworkChange) {
        this.g.remove(onNetworkChange);
    }

    public void register(Context context) {
        Context applicationContext = context.getApplicationContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        applicationContext.registerReceiver(this, intentFilter);
        this.h = new WeakReference<>(applicationContext);
    }

    public void unregister() {
        Context context;
        WeakReference<Context> weakReference = this.h;
        if (weakReference != null && (context = (Context) weakReference.get()) != null) {
            try {
                context.unregisterReceiver(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (this.g.isEmpty()) {
            Log.d(TAG, "NetworkMonitor.onReceive, mListener is empty");
        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equalsIgnoreCase(intent.getAction())) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                Log.d(TAG, "NetworkMonitor.onReceive, disconnected");
                Iterator<OnNetworkChange> it = this.g.iterator();
                while (it.hasNext()) {
                    it.next().onDisconnected(-1);
                }
                this.f668a = -1;
                this.f = 1;
                return;
            }
            int type = activeNetworkInfo.getType();
            if (type != this.f668a) {
                Log.d(TAG, "NetworkMonitor.onReceive, connected, type = " + type);
                Iterator<OnNetworkChange> it2 = this.g.iterator();
                while (it2.hasNext()) {
                    it2.next().onChange(type);
                }
            }
            if (activeNetworkInfo.isConnected()) {
                if (this.f668a != type || this.f != 0) {
                    Log.d(TAG, "NetworkMonitor.onReceive, connected, type = " + type);
                    Iterator<OnNetworkChange> it3 = this.g.iterator();
                    while (it3.hasNext()) {
                        it3.next().onConnected(type);
                    }
                    this.f = 0;
                } else {
                    return;
                }
            } else if (activeNetworkInfo.isConnectedOrConnecting()) {
                if (this.f668a != type || this.f != 2) {
                    Log.d(TAG, "NetworkMonitor.onReceive, connecting, type = " + type);
                    Iterator<OnNetworkChange> it4 = this.g.iterator();
                    while (it4.hasNext()) {
                        it4.next().onConnecting(type);
                    }
                    this.f = 2;
                } else {
                    return;
                }
            } else if (this.f668a != type || this.f != 1) {
                Log.d(TAG, "NetworkMonitor.onReceive, disconnected, type = " + type);
                Iterator<OnNetworkChange> it5 = this.g.iterator();
                while (it5.hasNext()) {
                    it5.next().onDisconnected(type);
                }
                this.f = 1;
            } else {
                return;
            }
            this.f668a = type;
        }
    }
}
