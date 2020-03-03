package com.mijia.model.sdcard;

import android.os.Parcel;
import android.os.Parcelable;
import com.mijia.model.CameraImageLoader;
import com.tutk.IOTC.Packet;

public class TimeItem implements Parcelable, Comparable<TimeItem> {
    public static final Parcelable.Creator<TimeItem> CREATOR = new Parcelable.Creator<TimeItem>() {
        /* renamed from: a */
        public TimeItem createFromParcel(Parcel parcel) {
            return new TimeItem(parcel);
        }

        /* renamed from: a */
        public TimeItem[] newArray(int i) {
            return new TimeItem[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public long f8098a;
    public long b;
    public long c;
    public int d;
    public boolean e = false;

    public static int a() {
        return 8;
    }

    public int describeContents() {
        return 0;
    }

    public TimeItem(long j, long j2, int i) {
        this.f8098a = j;
        this.b = j2;
        this.c = j + j2;
        this.d = i;
    }

    public TimeItem(long j, long j2, int i, boolean z) {
        this.f8098a = j;
        this.b = j2;
        this.c = j + j2;
        this.d = i;
        this.e = z;
    }

    protected TimeItem(Parcel parcel) {
        this.f8098a = parcel.readLong();
        this.b = parcel.readLong();
        this.c = parcel.readLong();
        this.d = parcel.readInt();
    }

    public String a(String str) {
        return CameraImageLoader.d + str + CameraImageLoader.e + String.valueOf(this.f8098a);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f8098a);
        parcel.writeLong(this.b);
        parcel.writeLong(this.c);
        parcel.writeInt(this.d);
    }

    public static TimeItem a(byte[] bArr, int i, boolean z) {
        long byteArrayToInt = ((long) Packet.byteArrayToInt(bArr, i, z)) * 1000;
        long j = ((long) (bArr[i + 4] & 255)) * 1000;
        byte b2 = bArr[i + 5] & 255;
        byte b3 = bArr[i + 6] & 255;
        if ((bArr[i + 7] & 255) != 0) {
            byteArrayToInt = 0;
        }
        return new TimeItem(byteArrayToInt, j, b3, b2 == 1);
    }

    public long b() {
        return this.c;
    }

    public boolean a(long j) {
        return j >= this.f8098a && j < this.c;
    }

    /* renamed from: a */
    public int compareTo(TimeItem timeItem) {
        return (int) (this.f8098a - timeItem.f8098a);
    }

    public int hashCode() {
        return (int) (this.f8098a / 1000);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof TimeItem) || this.f8098a != ((TimeItem) obj).f8098a) {
            return false;
        }
        return true;
    }
}
