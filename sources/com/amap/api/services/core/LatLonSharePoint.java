package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;

public class LatLonSharePoint extends LatLonPoint implements Parcelable {
    public static final Parcelable.Creator<LatLonSharePoint> CREATOR = new Parcelable.Creator<LatLonSharePoint>() {
        /* renamed from: a */
        public LatLonSharePoint createFromParcel(Parcel parcel) {
            return new LatLonSharePoint(parcel);
        }

        /* renamed from: a */
        public LatLonSharePoint[] newArray(int i) {
            return new LatLonSharePoint[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f4447a;

    public int describeContents() {
        return 0;
    }

    public LatLonSharePoint(double d, double d2, String str) {
        super(d, d2);
        this.f4447a = str;
    }

    public String getSharePointName() {
        return this.f4447a;
    }

    public void setSharePointName(String str) {
        this.f4447a = str;
    }

    protected LatLonSharePoint(Parcel parcel) {
        super(parcel);
        this.f4447a = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f4447a);
    }

    public int hashCode() {
        int i;
        int hashCode = super.hashCode() * 31;
        if (this.f4447a == null) {
            i = 0;
        } else {
            i = this.f4447a.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        LatLonSharePoint latLonSharePoint = (LatLonSharePoint) obj;
        if (this.f4447a == null) {
            if (latLonSharePoint.f4447a != null) {
                return false;
            }
        } else if (!this.f4447a.equals(latLonSharePoint.f4447a)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return super.toString() + "," + this.f4447a;
    }
}
