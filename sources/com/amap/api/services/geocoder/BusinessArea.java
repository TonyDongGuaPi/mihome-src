package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

public class BusinessArea implements Parcelable {
    public static final Parcelable.Creator<BusinessArea> CREATOR = new Parcelable.Creator<BusinessArea>() {
        /* renamed from: a */
        public BusinessArea createFromParcel(Parcel parcel) {
            return new BusinessArea(parcel);
        }

        /* renamed from: a */
        public BusinessArea[] newArray(int i) {
            return new BusinessArea[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private LatLonPoint f4457a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public BusinessArea() {
    }

    public LatLonPoint getCenterPoint() {
        return this.f4457a;
    }

    public void setCenterPoint(LatLonPoint latLonPoint) {
        this.f4457a = latLonPoint;
    }

    public String getName() {
        return this.b;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f4457a, i);
        parcel.writeString(this.b);
    }

    public BusinessArea(Parcel parcel) {
        this.f4457a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.b = parcel.readString();
    }
}
