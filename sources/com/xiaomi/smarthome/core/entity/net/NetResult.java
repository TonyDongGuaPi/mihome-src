package com.xiaomi.smarthome.core.entity.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class NetResult implements Parcelable {
    public static final Parcelable.Creator<NetResult> CREATOR = new Parcelable.Creator<NetResult>() {
        /* renamed from: a */
        public NetResult createFromParcel(Parcel parcel) {
            return new NetResult(parcel);
        }

        /* renamed from: a */
        public NetResult[] newArray(int i) {
            return new NetResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public int f13981a;
    public boolean b = false;
    public String c;

    public int describeContents() {
        return 0;
    }

    public NetResult() {
    }

    protected NetResult(Parcel parcel) {
        boolean z = false;
        this.f13981a = parcel.readInt();
        this.b = parcel.readByte() != 0 ? true : z;
        this.c = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f13981a);
        parcel.writeByte(this.b ? (byte) 1 : 0);
        parcel.writeString(this.c);
    }

    @NonNull
    public String toString() {
        return String.format("net Result: isCache = %s,code = %d, response = %s", new Object[]{String.valueOf(this.b), Integer.valueOf(this.f13981a), this.c});
    }
}
