package com.xiaomi.smarthome.core.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

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
    public static final int f13963a = -2;
    public static final int b = -3;
    public static final int c = -4;
    public static final int d = -5;
    public static final int e = -97;
    public static final int f = -666;
    private int g;
    private String h;
    private String i;

    public int describeContents() {
        return 0;
    }

    public Error(int i2, String str, String str2) {
        this.g = i2;
        this.h = str;
        this.i = str2;
    }

    public Error(int i2, String str) {
        this.g = i2;
        this.h = str;
    }

    public final int a() {
        return this.g;
    }

    public final String b() {
        return this.h;
    }

    public String c() {
        return this.i;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
    }

    protected Error(Parcel parcel) {
        this.g = parcel.readInt();
        this.h = parcel.readString();
        this.i = parcel.readString();
    }

    public String toString() {
        return "Error{mCode=" + this.g + ", mDetail='" + this.h + Operators.SINGLE_QUOTE + ", mExtraMsg='" + this.i + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
