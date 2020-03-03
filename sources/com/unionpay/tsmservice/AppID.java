package com.unionpay.tsmservice;

import android.os.Parcel;
import android.os.Parcelable;

public class AppID implements Parcelable {
    public static final Parcelable.Creator<AppID> CREATOR = new Parcelable.Creator<AppID>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new AppID(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new AppID[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    String f9816a = "";
    String b = "";

    public AppID(Parcel parcel) {
        this.f9816a = parcel.readString();
        this.b = parcel.readString();
    }

    public AppID(String str, String str2) {
        this.f9816a = str;
        this.b = str2;
    }

    public int describeContents() {
        return 0;
    }

    public String getAppAid() {
        return this.f9816a;
    }

    public String getAppVersion() {
        return this.b;
    }

    public void setAppAid(String str) {
        this.f9816a = str;
    }

    public void setAppVersion(String str) {
        this.b = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f9816a);
        parcel.writeString(this.b);
    }
}
