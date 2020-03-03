package com.reactnativecommunity.netinfo.types;

import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.verificationsdk.internal.Constants;

public enum ConnectionType {
    BLUETOOTH(BleDevice.f14751a),
    CELLULAR("cellular"),
    ETHERNET("ethernet"),
    NONE("none"),
    UNKNOWN("unknown"),
    WIFI("wifi"),
    WIMAX("wimax"),
    VPN(Constants.s);
    
    public final String label;

    private ConnectionType(String str) {
        this.label = str;
    }
}
