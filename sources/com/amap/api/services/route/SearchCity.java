package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchCity implements Parcelable {
    public static final Parcelable.Creator<SearchCity> CREATOR = new Parcelable.Creator<SearchCity>() {
        /* renamed from: a */
        public SearchCity[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public SearchCity createFromParcel(Parcel parcel) {
            return new SearchCity(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f4523a;
    private String b;
    private String c;

    public int describeContents() {
        return 0;
    }

    public String getSearchCityName() {
        return this.f4523a;
    }

    public void setSearchCityName(String str) {
        this.f4523a = str;
    }

    public String getSearchCitycode() {
        return this.b;
    }

    public void setSearchCitycode(String str) {
        this.b = str;
    }

    public String getSearchCityAdCode() {
        return this.c;
    }

    public void setSearchCityhAdCode(String str) {
        this.c = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4523a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    public SearchCity(Parcel parcel) {
        this.f4523a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public SearchCity() {
    }
}
