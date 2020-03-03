package com.mi.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;

public class UpdaterInfo implements Parcelable {
    public static final Parcelable.Creator<UpdaterInfo> CREATOR = new Parcelable.Creator<UpdaterInfo>() {
        /* renamed from: a */
        public UpdaterInfo[] newArray(int i) {
            return new UpdaterInfo[i];
        }

        /* renamed from: a */
        public UpdaterInfo createFromParcel(Parcel parcel) {
            UpdaterInfo updaterInfo = new UpdaterInfo();
            updaterInfo.b = parcel.readString();
            updaterInfo.c = parcel.readString();
            updaterInfo.d = parcel.readString();
            updaterInfo.e = parcel.readString();
            updaterInfo.f = parcel.readInt();
            updaterInfo.g = parcel.readByte() != 0;
            updaterInfo.f7375a = new ArrayList<>();
            parcel.readList(updaterInfo.f7375a, DescType.class.getClassLoader());
            updaterInfo.i = parcel.readString();
            updaterInfo.h = new ArrayList<>();
            parcel.readList(updaterInfo.h, Feature.class.getClassLoader());
            updaterInfo.j = parcel.readString();
            return updaterInfo;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<DescType> f7375a;
    public String b;
    public String c;
    public String d;
    public String e;
    public int f;
    public boolean g;
    public ArrayList<Feature> h;
    public String i;
    public String j;

    public static class DescType implements Serializable {
        public String mDesc;
        public String mDescType;
    }

    public static class Feature implements Serializable {
        public String mFeatureDescription;
        public String mFeatureImage;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
        parcel.writeByte(this.g ? (byte) 1 : 0);
        parcel.writeList(this.f7375a);
        parcel.writeString(this.i);
        parcel.writeList(this.h);
        parcel.writeString(this.j);
    }
}
