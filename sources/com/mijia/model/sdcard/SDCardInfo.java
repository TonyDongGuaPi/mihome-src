package com.mijia.model.sdcard;

import android.os.Parcel;
import android.os.Parcelable;

public class SDCardInfo implements Parcelable {
    public static final Parcelable.Creator<SDCardInfo> CREATOR = new Parcelable.Creator<SDCardInfo>() {
        /* renamed from: a */
        public SDCardInfo createFromParcel(Parcel parcel) {
            return new SDCardInfo(parcel);
        }

        /* renamed from: a */
        public SDCardInfo[] newArray(int i) {
            return new SDCardInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public long f8074a;
    public long b;
    public long c;
    public long d;
    public int e;

    public int describeContents() {
        return 0;
    }

    private SDCardInfo(Parcel parcel) {
        this.f8074a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f8074a = parcel.readLong();
        this.b = parcel.readLong();
        this.c = parcel.readLong();
        this.e = parcel.readInt();
        this.d = (this.f8074a - this.b) - this.c;
    }

    public SDCardInfo(long j, long j2, long j3, int i) {
        this.f8074a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f8074a = j;
        this.c = j2;
        this.b = j3;
        this.e = i;
        this.d = (this.f8074a - this.b) - this.c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f8074a);
        parcel.writeLong(this.b);
        parcel.writeLong(this.c);
        parcel.writeInt(this.e);
    }
}
