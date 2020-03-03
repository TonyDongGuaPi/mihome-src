package com.mibi.common.base;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

class JumpBackResultInfo implements Parcelable {
    public static final Parcelable.Creator<JumpBackResultInfo> CREATOR = new Parcelable.Creator<JumpBackResultInfo>() {
        /* renamed from: a */
        public JumpBackResultInfo createFromParcel(Parcel parcel) {
            return new JumpBackResultInfo(parcel);
        }

        /* renamed from: a */
        public JumpBackResultInfo[] newArray(int i) {
            return new JumpBackResultInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    int f7460a;
    Bundle b;
    String c;
    boolean d;

    public int describeContents() {
        return 0;
    }

    public JumpBackResultInfo(int i, Bundle bundle, String str, boolean z) {
        this.f7460a = i;
        this.b = bundle;
        this.c = str;
        this.d = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f7460a);
        if (this.b != null) {
            parcel.writeInt(1);
            this.b.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.c);
        parcel.writeByte(this.d ? (byte) 1 : 0);
    }

    private JumpBackResultInfo(Parcel parcel) {
        this.f7460a = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.b = parcel.readBundle();
        } else {
            this.b = null;
        }
        this.c = parcel.readString();
        this.d = parcel.readByte() != 0;
    }
}
