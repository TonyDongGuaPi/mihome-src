package com.xiaomi.channel.gamesdk;

import android.os.Parcel;
import android.os.Parcelable;

public class Retobj implements Parcelable {
    public static final Parcelable.Creator<Retobj> CREATOR = new Parcelable.Creator<Retobj>() {
        /* renamed from: a */
        public Retobj[] newArray(int i) {
            return new Retobj[i];
        }

        /* renamed from: a */
        public Retobj createFromParcel(Parcel parcel) {
            return new Retobj(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private int f10053a = -1;
    private String b = "";

    public int describeContents() {
        return 0;
    }

    public Retobj() {
    }

    public int a() {
        return this.f10053a;
    }

    public void a(int i) {
        this.f10053a = i;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f10053a);
        parcel.writeString(this.b);
    }

    public Retobj(Parcel parcel) {
        this.f10053a = parcel.readInt();
        this.b = parcel.readString();
    }
}
