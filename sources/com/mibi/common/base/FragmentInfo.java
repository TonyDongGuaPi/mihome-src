package com.mibi.common.base;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashSet;

class FragmentInfo implements Parcelable {
    public static final Parcelable.Creator<FragmentInfo> CREATOR = new Parcelable.Creator<FragmentInfo>() {
        /* renamed from: a */
        public FragmentInfo[] newArray(int i) {
            return new FragmentInfo[i];
        }

        /* renamed from: a */
        public FragmentInfo createFromParcel(Parcel parcel) {
            FragmentInfo fragmentInfo = new FragmentInfo();
            fragmentInfo.f7458a = parcel.readString();
            fragmentInfo.b = (HashSet) parcel.readSerializable();
            boolean z = false;
            fragmentInfo.c = parcel.readByte() != 0;
            if (parcel.readByte() != 0) {
                z = true;
            }
            fragmentInfo.d = z;
            fragmentInfo.e = parcel.readString();
            fragmentInfo.f = parcel.readInt();
            return fragmentInfo;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    String f7458a;
    HashSet<String> b = new HashSet<>();
    boolean c;
    boolean d;
    String e;
    int f;

    public int describeContents() {
        return 0;
    }

    FragmentInfo() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f7458a);
        parcel.writeSerializable(this.b);
        parcel.writeByte(this.c ? (byte) 1 : 0);
        parcel.writeByte(this.d ? (byte) 1 : 0);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
    }
}
