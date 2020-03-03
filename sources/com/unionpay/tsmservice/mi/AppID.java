package com.unionpay.tsmservice.mi;

import android.os.Parcel;
import android.os.Parcelable;

public class AppID implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new AppID(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new AppID[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    String f9829a = "";
    String b = "";

    public AppID(Parcel parcel) {
        this.f9829a = parcel.readString();
        this.b = parcel.readString();
    }

    public AppID(String str, String str2) {
        this.f9829a = str;
        this.b = str2;
    }

    public int describeContents() {
        return 0;
    }

    public String getAppAid() {
        return this.f9829a;
    }

    public String getAppVersion() {
        return this.b;
    }

    public void setAppAid(String str) {
        this.f9829a = str;
    }

    public void setAppVersion(String str) {
        this.b = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f9829a);
        parcel.writeString(this.b);
    }
}
