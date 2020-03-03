package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public class WatchBandDevice extends Device {
    public static final Parcelable.Creator<WatchBandDevice> CREATOR = new Parcelable.Creator<WatchBandDevice>() {
        /* renamed from: a */
        public WatchBandDevice createFromParcel(Parcel parcel) {
            return new WatchBandDevice(parcel);
        }

        /* renamed from: a */
        public WatchBandDevice[] newArray(int i) {
            return new WatchBandDevice[i];
        }
    };

    protected WatchBandDevice(Parcel parcel) {
        super(parcel);
    }

    public WatchBandDevice() {
    }

    public int describeContents() {
        return super.describeContents();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        a(true);
        b(true);
        c(0);
        d(true);
    }
}
