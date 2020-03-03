package com.mibi.common.base;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

class ResultInfo implements Parcelable {
    public static final Parcelable.Creator<ResultInfo> CREATOR = new Parcelable.Creator<ResultInfo>() {
        /* renamed from: a */
        public ResultInfo createFromParcel(Parcel parcel) {
            return new ResultInfo(parcel);
        }

        /* renamed from: a */
        public ResultInfo[] newArray(int i) {
            return new ResultInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    String f7467a;
    int b;
    int c;
    Bundle d;

    public int describeContents() {
        return 0;
    }

    public ResultInfo(String str, int i, int i2, Bundle bundle) {
        this.f7467a = str;
        this.b = i;
        this.c = i2;
        this.d = bundle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f7467a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        if (this.d != null) {
            parcel.writeInt(1);
            this.d.writeToParcel(parcel, 0);
            return;
        }
        parcel.writeInt(0);
    }

    private ResultInfo(Parcel parcel) {
        this.f7467a = parcel.readString();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.d = parcel.readBundle();
        } else {
            this.d = null;
        }
    }
}
