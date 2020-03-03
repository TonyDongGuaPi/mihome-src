package com.ximalaya.ting.android.opensdk.model.statistic;

import android.os.Parcel;
import android.os.Parcelable;

public class RecordModel implements Parcelable {
    public static final Parcelable.Creator<RecordModel> CREATOR = new Parcelable.Creator<RecordModel>() {
        /* renamed from: a */
        public RecordModel createFromParcel(Parcel parcel) {
            RecordModel recordModel = new RecordModel();
            recordModel.a(parcel);
            return recordModel;
        }

        /* renamed from: a */
        public RecordModel[] newArray(int i) {
            return new RecordModel[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f2115a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 9;
    public static final int h = 10;
    int i;
    int j;
    String k;

    public int describeContents() {
        return 0;
    }

    public RecordModel() {
    }

    public RecordModel(int i2, int i3, String str) {
        this.i = i2;
        this.j = i3;
        this.k = str;
    }

    public int a() {
        return this.i;
    }

    public void a(int i2) {
        this.i = i2;
    }

    public int b() {
        return this.j;
    }

    public void b(int i2) {
        this.j = i2;
    }

    public String c() {
        return this.k;
    }

    public void a(String str) {
        this.k = str;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeString(this.k);
    }

    public void a(Parcel parcel) {
        a(parcel.readInt());
        b(parcel.readInt());
        a(parcel.readString());
    }
}
