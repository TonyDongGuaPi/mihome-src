package com.xiaomi.youpin.common.base;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

public class YouPinError implements Parcelable {
    public static final Parcelable.Creator<YouPinError> CREATOR = new Parcelable.Creator<YouPinError>() {
        /* renamed from: a */
        public YouPinError createFromParcel(Parcel parcel) {
            return new YouPinError(parcel);
        }

        /* renamed from: a */
        public YouPinError[] newArray(int i) {
            return new YouPinError[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private int f23226a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public YouPinError(int i, String str) {
        this.f23226a = i;
        this.b = str;
    }

    protected YouPinError(Parcel parcel) {
        this.f23226a = parcel.readInt();
        this.b = parcel.readString();
    }

    public final int b() {
        return this.f23226a;
    }

    public final String c() {
        return this.b;
    }

    public String toString() {
        return "YouPinError{code=" + this.f23226a + ", detail='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f23226a);
        parcel.writeString(this.b);
    }
}
