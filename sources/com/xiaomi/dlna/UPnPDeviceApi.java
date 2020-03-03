package com.xiaomi.dlna;

import java.util.List;
import org.cybergarage.upnp.Device;

public abstract class UPnPDeviceApi {
    protected static UPnPDeviceApi sUPnPDeviceApi;

    public abstract Device getDeviceByUUID(String str);

    public abstract List<Device> upnpDevices();

    public static UPnPDeviceApi instance() {
        return sUPnPDeviceApi;
    }
}
