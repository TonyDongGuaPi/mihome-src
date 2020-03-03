package com.xiaomi.smarthome.core.entity.account;

import android.os.Parcel;
import android.os.Parcelable;

public class RefreshServiceTokenResult implements Parcelable {
    public static final Parcelable.Creator<RefreshServiceTokenResult> CREATOR = new Parcelable.Creator<RefreshServiceTokenResult>() {
        /* renamed from: a */
        public RefreshServiceTokenResult createFromParcel(Parcel parcel) {
            return new RefreshServiceTokenResult(parcel);
        }

        /* renamed from: a */
        public RefreshServiceTokenResult[] newArray(int i) {
            return new RefreshServiceTokenResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f13965a;
    public String b;
    public String c;
    public String d;
    public long e;

    public int describeContents() {
        return 0;
    }

    public RefreshServiceTokenResult() {
    }

    protected RefreshServiceTokenResult(Parcel parcel) {
        this.f13965a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13965a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeLong(this.e);
    }
}
