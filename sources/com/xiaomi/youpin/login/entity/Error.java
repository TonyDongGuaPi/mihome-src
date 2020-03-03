package com.xiaomi.youpin.login.entity;

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
    private int f23510a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public Error(int i, String str) {
        this.f23510a = i;
        this.b = str;
    }

    protected Error(Parcel parcel) {
        this.f23510a = parcel.readInt();
        this.b = parcel.readString();
    }

    public final int a() {
        return this.f23510a;
    }

    public final String b() {
        return this.b;
    }

    public String toString() {
        return "Error{mCode=" + this.f23510a + ", mDetail='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f23510a);
        parcel.writeString(this.b);
    }
}
