package com.ximalaya.ting.android.opensdk.model.xdcs;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

public class CdnConfigModel implements Parcelable {
    public static final Parcelable.Creator<CdnConfigModel> CREATOR = new Parcelable.Creator<CdnConfigModel>() {
        /* renamed from: a */
        public CdnConfigModel[] newArray(int i) {
            return new CdnConfigModel[i];
        }

        /* renamed from: a */
        public CdnConfigModel createFromParcel(Parcel parcel) {
            return new CdnConfigModel(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public int f2138a;
    public int b;
    public int c;
    public int d;
    public int e;
    public String f;
    public String g;
    public String h;
    public String i;
    public HashMap<String, String> j;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.h;
    }

    public void a(String str) {
        this.h = str;
    }

    public String b() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public HashMap<String, String> c() {
        return this.j;
    }

    public void a(HashMap<String, String> hashMap) {
        this.j = hashMap;
    }

    public String d() {
        return this.g;
    }

    public void c(String str) {
        this.g = str;
    }

    public String e() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public int f() {
        return this.e;
    }

    public void a(int i2) {
        this.e = i2;
    }

    public int g() {
        return this.f2138a;
    }

    public void b(int i2) {
        this.f2138a = i2;
    }

    public int h() {
        return this.b;
    }

    public void c(int i2) {
        this.b = i2;
    }

    public int i() {
        return this.c;
    }

    public void d(int i2) {
        this.c = i2;
    }

    public int j() {
        return this.d;
    }

    public void e(int i2) {
        this.d = i2;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f2138a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeMap(this.j);
    }

    public CdnConfigModel() {
    }

    public CdnConfigModel(Parcel parcel) {
        this.f2138a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.b = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readHashMap(HashMap.class.getClassLoader());
    }
}
