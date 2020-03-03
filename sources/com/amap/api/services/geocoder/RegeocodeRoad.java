package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

public final class RegeocodeRoad implements Parcelable {
    public static final Parcelable.Creator<RegeocodeRoad> CREATOR = new Parcelable.Creator<RegeocodeRoad>() {
        /* renamed from: a */
        public RegeocodeRoad[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public RegeocodeRoad createFromParcel(Parcel parcel) {
            return new RegeocodeRoad(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f4465a;
    private String b;
    private float c;
    private String d;
    private LatLonPoint e;

    public int describeContents() {
        return 0;
    }

    public RegeocodeRoad() {
    }

    public String getId() {
        return this.f4465a;
    }

    public void setId(String str) {
        this.f4465a = str;
    }

    public String getName() {
        return this.b;
    }

    public void setName(String str) {
        this.b = str;
    }

    public float getDistance() {
        return this.c;
    }

    public void setDistance(float f) {
        this.c = f;
    }

    public String getDirection() {
        return this.d;
    }

    public void setDirection(String str) {
        this.d = str;
    }

    public LatLonPoint getLatLngPoint() {
        return this.e;
    }

    public void setLatLngPoint(LatLonPoint latLonPoint) {
        this.e = latLonPoint;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4465a);
        parcel.writeString(this.b);
        parcel.writeFloat(this.c);
        parcel.writeString(this.d);
        parcel.writeValue(this.e);
    }

    private RegeocodeRoad(Parcel parcel) {
        this.f4465a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readFloat();
        this.d = parcel.readString();
        this.e = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
    }
}
