package com.xiaomi.jr.http;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import com.xiaomi.jr.common.utils.Algorithms;
import com.xiaomi.jr.common.utils.NetworkUtils;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class NetworkStatusReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1429a = "MifiNetworkStatusReceiver";
    private static final CopyOnWriteArrayList<WeakReference<NetworkStatusListener>> b = new CopyOnWriteArrayList<>();

    public interface NetworkStatusListener {
        void a(Context context, NetworkInfo networkInfo);
    }

    public void onReceive(Context context, Intent intent) {
        a(context, NetworkUtils.a(context));
    }

    public static void addNetworkStatusListener(Context context, NetworkStatusListener networkStatusListener, boolean z) {
        Algorithms.a(b, networkStatusListener);
        if (z) {
            networkStatusListener.a(context, NetworkUtils.a(context));
        }
    }

    public static void removeNetworkStatusListener(NetworkStatusListener networkStatusListener) {
        Algorithms.b(b, networkStatusListener);
    }

    private void a(Context context, NetworkInfo networkInfo) {
        Iterator<WeakReference<NetworkStatusListener>> it = b.iterator();
        while (it.hasNext()) {
            NetworkStatusListener networkStatusListener = (NetworkStatusListener) it.next().get();
            if (networkStatusListener != null) {
                networkStatusListener.a(context, networkInfo);
            }
        }
    }
}
