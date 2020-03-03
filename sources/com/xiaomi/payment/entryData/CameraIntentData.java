package com.xiaomi.payment.entryData;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class CameraIntentData implements Parcelable {
    public static final Parcelable.Creator<CameraIntentData> CREATOR = new Parcelable.Creator<CameraIntentData>() {
        /* renamed from: a */
        public CameraIntentData createFromParcel(Parcel parcel) {
            return new CameraIntentData(parcel);
        }

        /* renamed from: a */
        public CameraIntentData[] newArray(int i) {
            return new CameraIntentData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public int f12297a;
    public String b;
    public ArrayList<Integer> c;

    public int describeContents() {
        return 0;
    }

    public CameraIntentData(int i, String str, ArrayList<Integer> arrayList) {
        this.f12297a = i;
        this.b = str;
        this.c = arrayList;
    }

    public CameraIntentData(Parcel parcel) {
        this.f12297a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readArrayList(Integer.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f12297a);
        parcel.writeString(this.b);
        parcel.writeList(this.c);
    }
}
