package com.reactnativecommunity.netinfo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.reactnativecommunity.netinfo.types.CellularGeneration;
import com.reactnativecommunity.netinfo.types.ConnectionType;

public class BroadcastReceiverConnectivityReceiver extends ConnectivityReceiver {
    private final ConnectivityBroadcastReceiver mConnectivityBroadcastReceiver = new ConnectivityBroadcastReceiver();

    public /* bridge */ /* synthetic */ void getCurrentState(Promise promise) {
        super.getCurrentState(promise);
    }

    public BroadcastReceiverConnectivityReceiver(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getReactContext().registerReceiver(this.mConnectivityBroadcastReceiver, intentFilter);
        this.mConnectivityBroadcastReceiver.a(true);
        updateAndSendConnectionType();
    }

    public void unregister() {
        if (this.mConnectivityBroadcastReceiver.a()) {
            getReactContext().unregisterReceiver(this.mConnectivityBroadcastReceiver);
            this.mConnectivityBroadcastReceiver.a(false);
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void updateAndSendConnectionType() {
        boolean z;
        ConnectionType connectionType = ConnectionType.UNKNOWN;
        CellularGeneration cellularGeneration = null;
        try {
            NetworkInfo activeNetworkInfo = getConnectivityManager().getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    z = activeNetworkInfo.isConnected();
                    try {
                        switch (activeNetworkInfo.getType()) {
                            case 0:
                            case 4:
                                connectionType = ConnectionType.CELLULAR;
                                cellularGeneration = CellularGeneration.fromNetworkInfo(activeNetworkInfo);
                                break;
                            case 1:
                                connectionType = ConnectionType.WIFI;
                                break;
                            case 6:
                                connectionType = ConnectionType.WIMAX;
                                break;
                            case 7:
                                connectionType = ConnectionType.BLUETOOTH;
                                break;
                            case 9:
                                connectionType = ConnectionType.ETHERNET;
                                break;
                            case 17:
                                connectionType = ConnectionType.VPN;
                                break;
                        }
                    } catch (SecurityException unused) {
                        connectionType = ConnectionType.UNKNOWN;
                        updateConnectivity(connectionType, cellularGeneration, z);
                    }
                    updateConnectivity(connectionType, cellularGeneration, z);
                }
            }
            connectionType = ConnectionType.NONE;
            z = false;
        } catch (SecurityException unused2) {
            z = false;
            connectionType = ConnectionType.UNKNOWN;
            updateConnectivity(connectionType, cellularGeneration, z);
        }
        updateConnectivity(connectionType, cellularGeneration, z);
    }

    private class ConnectivityBroadcastReceiver extends BroadcastReceiver {
        private boolean b;

        private ConnectivityBroadcastReceiver() {
            this.b = false;
        }

        public void a(boolean z) {
            this.b = z;
        }

        public boolean a() {
            return this.b;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                BroadcastReceiverConnectivityReceiver.this.updateAndSendConnectionType();
            }
        }
    }
}
