package com.xiaomi.smarthome.newui.amappoi;

import android.os.Parcel;
import android.os.Parcelable;

public class LocationBean implements Parcelable {
    public static final Parcelable.Creator<LocationBean> CREATOR = new Parcelable.Creator<LocationBean>() {
        /* renamed from: a */
        public LocationBean createFromParcel(Parcel parcel) {
            return new LocationBean(parcel);
        }

        /* renamed from: a */
        public LocationBean[] newArray(int i) {
            return new LocationBean[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private double f20451a;
    private double b;
    private String c;
    private String d;

    public int describeContents() {
        return 0;
    }

    public LocationBean(double d2, double d3, String str, String str2) {
        this.f20451a = d2;
        this.b = d3;
        this.c = str;
        this.d = str2;
    }

    public double a() {
        return this.f20451a;
    }

    public double b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.f20451a);
        parcel.writeDouble(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }

    protected LocationBean(Parcel parcel) {
        this.f20451a = parcel.readDouble();
        this.b = parcel.readDouble();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }
}
