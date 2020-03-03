package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

public class Doorway implements Parcelable {
    public static final Parcelable.Creator<Doorway> CREATOR = new Parcelable.Creator<Doorway>() {
        /* renamed from: a */
        public Doorway[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public Doorway createFromParcel(Parcel parcel) {
            return new Doorway(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f4495a;
    private LatLonPoint b;

    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.f4495a;
    }

    public void setName(String str) {
        this.f4495a = str;
    }

    public LatLonPoint getLatLonPoint() {
        return this.b;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.b = latLonPoint;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4495a);
        parcel.writeParcelable(this.b, i);
    }

    public Doorway(Parcel parcel) {
        this.f4495a = parcel.readString();
        this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public Doorway() {
    }
}
