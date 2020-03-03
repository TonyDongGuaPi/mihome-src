package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;

public class LatLonPoint implements Parcelable {
    public static final Parcelable.Creator<LatLonPoint> CREATOR = new Parcelable.Creator<LatLonPoint>() {
        /* renamed from: a */
        public LatLonPoint createFromParcel(Parcel parcel) {
            return new LatLonPoint(parcel);
        }

        /* renamed from: a */
        public LatLonPoint[] newArray(int i) {
            return new LatLonPoint[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private double f4446a;
    private double b;

    public int describeContents() {
        return 0;
    }

    public LatLonPoint(double d, double d2) {
        this.f4446a = d;
        this.b = d2;
    }

    public double getLongitude() {
        return this.b;
    }

    public void setLongitude(double d) {
        this.b = d;
    }

    public double getLatitude() {
        return this.f4446a;
    }

    public void setLatitude(double d) {
        this.f4446a = d;
    }

    public LatLonPoint copy() {
        return new LatLonPoint(this.f4446a, this.b);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f4446a);
        long doubleToLongBits2 = Double.doubleToLongBits(this.b);
        return ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LatLonPoint latLonPoint = (LatLonPoint) obj;
        return Double.doubleToLongBits(this.f4446a) == Double.doubleToLongBits(latLonPoint.f4446a) && Double.doubleToLongBits(this.b) == Double.doubleToLongBits(latLonPoint.b);
    }

    public String toString() {
        return "" + this.f4446a + "," + this.b;
    }

    protected LatLonPoint(Parcel parcel) {
        this.f4446a = parcel.readDouble();
        this.b = parcel.readDouble();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.f4446a);
        parcel.writeDouble(this.b);
    }
}
