package com.ximalaya.ting.android.opensdk.model.token;

import android.os.Parcel;
import android.os.Parcelable;

public class AccessToken implements Parcelable {
    public static final Parcelable.Creator<AccessToken> CREATOR = new Parcelable.Creator<AccessToken>() {
        /* renamed from: a */
        public AccessToken createFromParcel(Parcel parcel) {
            AccessToken accessToken = new AccessToken();
            accessToken.a(parcel);
            return accessToken;
        }

        /* renamed from: a */
        public AccessToken[] newArray(int i) {
            return new AccessToken[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f2118a;
    private long b;
    private String c;
    private long d;
    private String e;
    private String f;
    private String g;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public String b() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public String c() {
        return this.f2118a;
    }

    public void c(String str) {
        this.f2118a = str;
    }

    public long d() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    public String e() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public long f() {
        return this.d;
    }

    public void b(long j) {
        this.d = j;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2118a);
        parcel.writeLong(this.b);
        parcel.writeString(this.c);
        parcel.writeLong(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }

    public void a(Parcel parcel) {
        this.f2118a = parcel.readString();
        this.b = parcel.readLong();
        this.c = parcel.readString();
        this.d = parcel.readLong();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
    }

    public String g() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }
}
