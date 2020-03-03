package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public class VirtualDevice extends Device {
    public static final Parcelable.Creator<VirtualDevice> CREATOR = new Parcelable.Creator<VirtualDevice>() {
        /* renamed from: a */
        public VirtualDevice createFromParcel(Parcel parcel) {
            return new VirtualDevice(parcel);
        }

        /* renamed from: a */
        public VirtualDevice[] newArray(int i) {
            return new VirtualDevice[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private int f13976a;
    private String b;

    protected VirtualDevice(Parcel parcel) {
        super(parcel);
        this.f13976a = parcel.readInt();
        this.b = parcel.readString();
    }

    public static VirtualDevice b(JSONObject jSONObject) {
        VirtualDevice virtualDevice = new VirtualDevice();
        virtualDevice.a(jSONObject);
        return virtualDevice;
    }

    private VirtualDevice() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f13976a);
        parcel.writeString(this.b);
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            super.a(jSONObject);
            this.f13976a = jSONObject.optInt("state");
            this.b = jSONObject.optString("url");
            b(true);
            a(true);
        }
    }

    public synchronized int a() {
        return this.f13976a;
    }

    public synchronized String b() {
        return this.b;
    }
}
