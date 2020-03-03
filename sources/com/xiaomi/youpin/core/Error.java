package com.xiaomi.youpin.core;

import android.os.Parcel;
import android.os.Parcelable;

public class Error implements Parcelable {
    public static final Parcelable.Creator<Error> CREATOR = new Parcelable.Creator<Error>() {
        /* renamed from: a */
        public Error createFromParcel(Parcel parcel) {
            return new Error(parcel);
        }

        /* renamed from: a */
        public Error[] newArray(int i) {
            return new Error[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f23315a = -2;
    public static final int b = -3;
    private int c;
    private String d;

    public int describeContents() {
        return 0;
    }

    public Error(int i, String str) {
        this.c = i;
        this.d = str;
    }

    protected Error(Parcel parcel) {
        this.c = parcel.readInt();
        this.d = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
    }

    public final int a() {
        return this.c;
    }

    public final String b() {
        return this.d;
    }
}
