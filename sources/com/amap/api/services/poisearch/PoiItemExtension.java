package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;

public class PoiItemExtension implements Parcelable {
    public static final Parcelable.Creator<PoiItemExtension> CREATOR = new Parcelable.Creator<PoiItemExtension>() {
        /* renamed from: a */
        public PoiItemExtension createFromParcel(Parcel parcel) {
            return new PoiItemExtension(parcel);
        }

        /* renamed from: a */
        public PoiItemExtension[] newArray(int i) {
            return new PoiItemExtension[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f4479a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public PoiItemExtension(String str, String str2) {
        this.f4479a = str;
        this.b = str2;
    }

    public String getOpentime() {
        return this.f4479a;
    }

    public String getmRating() {
        return this.b;
    }

    protected PoiItemExtension(Parcel parcel) {
        this.f4479a = parcel.readString();
        this.b = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4479a);
        parcel.writeString(this.b);
    }
}
