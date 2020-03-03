package com.xiaomi.youpin.core.net;

import android.os.Parcel;
import android.os.Parcelable;

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
    public int f23344a;
    public boolean b = false;
    public boolean c = true;
    public String d;

    public int describeContents() {
        return 0;
    }

    public NetResult() {
    }

    protected NetResult(Parcel parcel) {
        boolean z = false;
        this.f23344a = parcel.readInt();
        this.b = parcel.readByte() != 0;
        this.c = parcel.readByte() != 0 ? true : z;
        this.d = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f23344a);
        parcel.writeByte(this.b ? (byte) 1 : 0);
        parcel.writeByte(this.c ? (byte) 1 : 0);
        parcel.writeString(this.d);
    }
}
