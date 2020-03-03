package com.reactnativecommunity.netinfo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.facebook.react.bridge.ReactApplicationContext;
import com.reactnativecommunity.netinfo.types.CellularGeneration;
import com.reactnativecommunity.netinfo.types.ConnectionType;

@TargetApi(24)
class NetworkCallbackConnectivityReceiver extends ConnectivityReceiver {

    /* renamed from: a  reason: collision with root package name */
    private final ConnectivityNetworkCallback f8702a = new ConnectivityNetworkCallback();
    /* access modifiers changed from: private */
    public Network b = null;
    /* access modifiers changed from: private */
    public NetworkCapabilities c = null;

    public NetworkCallbackConnectivityReceiver(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"MissingPermission"})
    public void register() {
        try {
            getConnectivityManager().registerDefaultNetworkCallback(this.f8702a);
        } catch (SecurityException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void unregister() {
        try {
            getConnectivityManager().unregisterNetworkCallback(this.f8702a);
        } catch (IllegalArgumentException | SecurityException unused) {
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void a() {
        ConnectionType connectionType = ConnectionType.UNKNOWN;
        boolean z = false;
        CellularGeneration cellularGeneration = null;
        if (this.c != null) {
            if (this.c.hasTransport(2)) {
                connectionType = ConnectionType.BLUETOOTH;
            } else if (this.c.hasTransport(0)) {
                connectionType = ConnectionType.CELLULAR;
            } else if (this.c.hasTransport(3)) {
                connectionType = ConnectionType.ETHERNET;
            } else if (this.c.hasTransport(1)) {
                connectionType = ConnectionType.WIFI;
            } else if (this.c.hasTransport(4)) {
                connectionType = ConnectionType.VPN;
            }
            if (this.c.hasCapability(12) && this.c.hasCapability(16)) {
                z = true;
            }
            if (this.b != null && connectionType == ConnectionType.CELLULAR) {
                cellularGeneration = CellularGeneration.fromNetworkInfo(getConnectivityManager().getNetworkInfo(this.b));
            }
        } else {
            connectionType = ConnectionType.NONE;
        }
        updateConnectivity(connectionType, cellularGeneration, z);
    }

    private class ConnectivityNetworkCallback extends ConnectivityManager.NetworkCallback {
        private ConnectivityNetworkCallback() {
        }

        public void onAvailable(Network network) {
            Network unused = NetworkCallbackConnectivityReceiver.this.b = network;
            NetworkCapabilities unused2 = NetworkCallbackConnectivityReceiver.this.c = NetworkCallbackConnectivityReceiver.this.getConnectivityManager().getNetworkCapabilities(network);
            NetworkCallbackConnectivityReceiver.this.a();
        }

        public void onLosing(Network network, int i) {
            Network unused = NetworkCallbackConnectivityReceiver.this.b = network;
            NetworkCapabilities unused2 = NetworkCallbackConnectivityReceiver.this.c = NetworkCallbackConnectivityReceiver.this.getConnectivityManager().getNetworkCapabilities(network);
            NetworkCallbackConnectivityReceiver.this.a();
        }

        public void onLost(Network network) {
            Network unused = NetworkCallbackConnectivityReceiver.this.b = null;
            NetworkCapabilities unused2 = NetworkCallbackConnectivityReceiver.this.c = null;
            NetworkCallbackConnectivityReceiver.this.a();
        }

        public void onUnavailable() {
            Network unused = NetworkCallbackConnectivityReceiver.this.b = null;
            NetworkCapabilities unused2 = NetworkCallbackConnectivityReceiver.this.c = null;
            NetworkCallbackConnectivityReceiver.this.a();
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            Network unused = NetworkCallbackConnectivityReceiver.this.b = network;
            NetworkCapabilities unused2 = NetworkCallbackConnectivityReceiver.this.c = networkCapabilities;
            NetworkCallbackConnectivityReceiver.this.a();
        }

        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            Network unused = NetworkCallbackConnectivityReceiver.this.b = network;
            NetworkCapabilities unused2 = NetworkCallbackConnectivityReceiver.this.c = NetworkCallbackConnectivityReceiver.this.getConnectivityManager().getNetworkCapabilities(network);
            NetworkCallbackConnectivityReceiver.this.a();
        }
    }
}
