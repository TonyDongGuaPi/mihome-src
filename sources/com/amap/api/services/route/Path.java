package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

public class Path implements Parcelable {
    public static final Parcelable.Creator<Path> CREATOR = new Parcelable.Creator<Path>() {
        /* renamed from: a */
        public Path[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public Path createFromParcel(Parcel parcel) {
            return new Path(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private float f4502a;
    private long b;

    public int describeContents() {
        return 0;
    }

    public float getDistance() {
        return this.f4502a;
    }

    public void setDistance(float f) {
        this.f4502a = f;
    }

    public long getDuration() {
        return this.b;
    }

    public void setDuration(long j) {
        this.b = j;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f4502a);
        parcel.writeLong(this.b);
    }

    public Path(Parcel parcel) {
        this.f4502a = parcel.readFloat();
        this.b = parcel.readLong();
    }

    public Path() {
    }
}
