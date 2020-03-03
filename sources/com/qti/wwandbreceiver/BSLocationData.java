package com.qti.wwandbreceiver;

import android.os.Parcel;
import android.os.Parcelable;

public final class BSLocationData implements Parcelable {
    public static final Parcelable.Creator<BSLocationData> CREATOR = new Parcelable.Creator<BSLocationData>() {
        /* renamed from: a */
        public BSLocationData createFromParcel(Parcel parcel) {
            return new BSLocationData(parcel);
        }

        /* renamed from: a */
        public BSLocationData[] newArray(int i) {
            return new BSLocationData[i];
        }
    };
    public static final int h = 0;
    public static final int i = 1;
    public static final int j = 2;
    public static final int k = 4;
    public static final int l = 8;
    public static final int m = 16;
    public static final int n = 32;
    public static final int o = 64;

    /* renamed from: a  reason: collision with root package name */
    public int f8641a;
    public int b;
    public int c;
    public int d;
    public int e;
    public float f;
    public float g;
    public int p;
    public float q;
    public int r;
    public int s;
    public float t;
    public float u;
    public float v;
    public int w;

    public int describeContents() {
        return 0;
    }

    public BSLocationData() {
        this.p = 0;
    }

    private BSLocationData(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f8641a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeFloat(this.f);
        parcel.writeFloat(this.g);
        parcel.writeInt(this.p);
        parcel.writeFloat(this.q);
        parcel.writeInt(this.r);
        parcel.writeInt(this.s);
        parcel.writeFloat(this.t);
        parcel.writeFloat(this.u);
        parcel.writeFloat(this.v);
        parcel.writeInt(this.w);
    }

    public void a(Parcel parcel) {
        this.f8641a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readFloat();
        this.g = parcel.readFloat();
        this.p = parcel.readInt();
        this.q = parcel.readFloat();
        this.r = parcel.readInt();
        this.s = parcel.readInt();
        this.t = parcel.readFloat();
        this.u = parcel.readFloat();
        this.v = parcel.readFloat();
        this.w = parcel.readInt();
    }
}
