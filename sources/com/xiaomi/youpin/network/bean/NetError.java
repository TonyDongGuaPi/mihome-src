package com.xiaomi.youpin.network.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

public class NetError implements Parcelable {
    public static final Parcelable.Creator<NetError> CREATOR = new Parcelable.Creator<NetError>() {
        /* renamed from: a */
        public NetError createFromParcel(Parcel parcel) {
            return new NetError(parcel);
        }

        /* renamed from: a */
        public NetError[] newArray(int i) {
            return new NetError[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private int f23655a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public NetError(int i, String str) {
        this.f23655a = i;
        this.b = str;
    }

    protected NetError(Parcel parcel) {
        this.f23655a = parcel.readInt();
        this.b = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f23655a);
        parcel.writeString(this.b);
    }

    public final int a() {
        return this.f23655a;
    }

    public final String b() {
        return this.b;
    }

    public String toString() {
        return "NetError{code=" + this.f23655a + ", detail='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
