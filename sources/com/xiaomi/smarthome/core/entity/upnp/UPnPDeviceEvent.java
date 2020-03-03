package com.xiaomi.smarthome.core.entity.upnp;

import android.os.Parcel;
import android.os.Parcelable;

public class UPnPDeviceEvent implements Parcelable {
    public static final Parcelable.Creator<UPnPDeviceEvent> CREATOR = new Parcelable.Creator<UPnPDeviceEvent>() {
        /* renamed from: a */
        public UPnPDeviceEvent createFromParcel(Parcel parcel) {
            return new UPnPDeviceEvent(parcel);
        }

        /* renamed from: a */
        public UPnPDeviceEvent[] newArray(int i) {
            return new UPnPDeviceEvent[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f13999a;
    public long b;
    public String c;
    public String d;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13999a);
        parcel.writeLong(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }

    public UPnPDeviceEvent() {
    }

    protected UPnPDeviceEvent(Parcel parcel) {
        this.f13999a = parcel.readString();
        this.b = parcel.readLong();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }
}
