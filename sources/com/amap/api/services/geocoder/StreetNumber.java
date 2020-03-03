package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

public final class StreetNumber implements Parcelable {
    public static final Parcelable.Creator<StreetNumber> CREATOR = new Parcelable.Creator<StreetNumber>() {
        /* renamed from: a */
        public StreetNumber[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public StreetNumber createFromParcel(Parcel parcel) {
            return new StreetNumber(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f4466a;
    private String b;
    private LatLonPoint c;
    private String d;
    private float e;

    public int describeContents() {
        return 0;
    }

    public StreetNumber() {
    }

    public String getStreet() {
        return this.f4466a;
    }

    public void setStreet(String str) {
        this.f4466a = str;
    }

    public String getNumber() {
        return this.b;
    }

    public void setNumber(String str) {
        this.b = str;
    }

    public LatLonPoint getLatLonPoint() {
        return this.c;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.c = latLonPoint;
    }

    public String getDirection() {
        return this.d;
    }

    public void setDirection(String str) {
        this.d = str;
    }

    public float getDistance() {
        return this.e;
    }

    public void setDistance(float f) {
        this.e = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4466a);
        parcel.writeString(this.b);
        parcel.writeValue(this.c);
        parcel.writeString(this.d);
        parcel.writeFloat(this.e);
    }

    private StreetNumber(Parcel parcel) {
        this.f4466a = parcel.readString();
        this.b = parcel.readString();
        this.c = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.readFloat();
    }
}
