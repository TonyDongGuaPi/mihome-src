package com.xiaomi.router.miio.miioplugin;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.api.DeviceTag;

public class DeviceTagInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceTagInfo> CREATOR = new Parcelable.Creator<DeviceTagInfo>() {
        /* renamed from: a */
        public DeviceTagInfo createFromParcel(Parcel parcel) {
            return new DeviceTagInfo(parcel);
        }

        /* renamed from: a */
        public DeviceTagInfo[] newArray(int i) {
            return new DeviceTagInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13051a = 0;
    public static final int b = 2;
    public static final int c = 4;
    public String d;

    public int describeContents() {
        return 0;
    }

    public DeviceTagInfo() {
    }

    private DeviceTagInfo(Parcel parcel) {
        a(parcel);
    }

    /* access modifiers changed from: package-private */
    public void a(Parcel parcel) {
        this.d = parcel.readString();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Parcel parcel) {
        if (str == null) {
            parcel.writeString("");
        } else {
            parcel.writeString(str);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        a(this.d, parcel);
    }

    public DeviceTag a() {
        DeviceTag deviceTag = new DeviceTag();
        deviceTag.infoJson = this.d;
        return deviceTag;
    }
}
