package com.qti.wifidbreceiver;

import android.os.Parcel;
import android.os.Parcelable;

public final class APSpecialInfo implements Parcelable {
    public static final Parcelable.Creator<APSpecialInfo> CREATOR = new Parcelable.Creator<APSpecialInfo>() {
        /* renamed from: a */
        public APSpecialInfo createFromParcel(Parcel parcel) {
            return new APSpecialInfo(parcel);
        }

        /* renamed from: a */
        public APSpecialInfo[] newArray(int i) {
            return new APSpecialInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f8638a;
    public int b;

    public int describeContents() {
        return 0;
    }

    public APSpecialInfo() {
    }

    private APSpecialInfo(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f8638a);
        parcel.writeInt(this.b);
    }

    public void a(Parcel parcel) {
        this.f8638a = parcel.readString();
        this.b = parcel.readInt();
    }
}
