package com.xiaomi.smarthome.operation.js_sdk.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.RequiresPermission;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.network.NetworkManager;

public class NetWorkObserver {
    @SuppressLint({"CheckResult"})

    /* renamed from: a  reason: collision with root package name */
    private NetworkManager.NetworkListener f21096a = new NetworkManager.NetworkListener() {
        public void b() {
        }

        public void a() {
            if (NetWorkObserver.this.b != null) {
                NetWorkObserver.this.b.onNetChanged(NetWorkObserver.this.a());
            }
        }
    };
    /* access modifiers changed from: private */
    public OnNetChangedListener b;

    interface OnNetChangedListener {
        void onNetChanged(NetState netState);
    }

    private enum NetworkType {
        NETWORK_WIFI("WiFi"),
        NETWORK_4G("4G"),
        NETWORK_2G("2G"),
        NETWORK_3G("3G"),
        NETWORK_UNKNOWN("UNKNOWN"),
        NETWORK_NO("NOTREACHABLE");
        
        /* access modifiers changed from: private */
        public String desc;

        private NetworkType(String str) {
            this.desc = str;
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static NetworkInfo a(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public NetState a() {
        NetworkType b2 = b(SHApplication.getAppContext());
        NetworkInfo a2 = a(SHApplication.getAppContext());
        boolean z = false;
        if (b2 == NetworkType.NETWORK_NO) {
            return new NetState(false, b2.desc);
        }
        if (a2 != null && a2.isConnected()) {
            z = true;
        }
        return new NetState(z, b2.desc);
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static NetworkType b(Context context) {
        NetworkType networkType;
        NetworkType networkType2 = NetworkType.NETWORK_NO;
        NetworkInfo a2 = a(context);
        if (a2 == null || !a2.isAvailable()) {
            return networkType2;
        }
        if (a2.getType() == 1) {
            return NetworkType.NETWORK_WIFI;
        }
        if (a2.getType() != 0) {
            return NetworkType.NETWORK_UNKNOWN;
        }
        switch (a2.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                networkType = NetworkType.NETWORK_2G;
                break;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                networkType = NetworkType.NETWORK_3G;
                break;
            case 13:
            case 18:
                networkType = NetworkType.NETWORK_4G;
                break;
            default:
                String subtypeName = a2.getSubtypeName();
                if (!subtypeName.equalsIgnoreCase("TD-SCDMA") && !subtypeName.equalsIgnoreCase("WCDMA") && !subtypeName.equalsIgnoreCase("CDMA2000")) {
                    networkType = NetworkType.NETWORK_UNKNOWN;
                    break;
                } else {
                    networkType = NetworkType.NETWORK_3G;
                    break;
                }
                break;
        }
        return networkType;
    }

    public void a(OnNetChangedListener onNetChangedListener) {
        this.b = onNetChangedListener;
        if (this.b != null) {
            NetworkManager.a().a(this.f21096a);
        }
    }

    public void b() {
        NetworkManager.a().b(this.f21096a);
    }
}
