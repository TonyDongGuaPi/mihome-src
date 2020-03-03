package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;
import org.cybergarage.upnp.Device;

public class UPnPDevice extends Device {
    public static final Parcelable.Creator<UPnPDevice> CREATOR = new Parcelable.Creator<UPnPDevice>() {
        /* renamed from: a */
        public UPnPDevice createFromParcel(Parcel parcel) {
            return new UPnPDevice(parcel);
        }

        /* renamed from: a */
        public UPnPDevice[] newArray(int i) {
            return new UPnPDevice[0];
        }
    };

    public UPnPDevice(Device device) {
        a(device.getUDN());
        b("chuangmi.wifi.v1");
        a(true);
        d(true);
        b(true);
        c(false);
    }

    protected UPnPDevice(Parcel parcel) {
        super(parcel);
    }

    public String a() {
        return k();
    }
}
