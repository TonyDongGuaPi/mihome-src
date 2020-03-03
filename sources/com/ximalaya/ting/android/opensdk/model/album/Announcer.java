package com.ximalaya.ting.android.opensdk.model.album;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.util.XiMaDataSupport;

public class Announcer extends XiMaDataSupport implements Parcelable {
    public static final Parcelable.Creator<Announcer> CREATOR = new Parcelable.Creator<Announcer>() {
        /* renamed from: a */
        public Announcer[] newArray(int i) {
            return new Announcer[i];
        }

        /* renamed from: a */
        public Announcer createFromParcel(Parcel parcel) {
            Announcer announcer = new Announcer();
            announcer.a(parcel);
            return announcer;
        }
    };
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2013a;
    private String b;
    @SerializedName("avatar_url")
    private String c;
    @SerializedName("is_verified")
    private boolean d;
    private String e;
    @SerializedName("vcategory_id")
    private long f;
    private String g;
    private String h;
    @SerializedName("announcer_position")
    private String i;
    @SerializedName("follower_count")
    private long j;
    @SerializedName("following_count")
    private long k;
    @SerializedName("released_album_count")
    private long l;
    @SerializedName("released_track_count")
    private long m;
    private int n;
    private int o;

    public int describeContents() {
        return 0;
    }

    public long a() {
        return this.f2013a;
    }

    public void a(long j2) {
        this.f2013a = j2;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public boolean d() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public String e() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public long f() {
        return this.f;
    }

    public void b(long j2) {
        this.f = j2;
    }

    public String g() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    public String h() {
        return this.h;
    }

    public void e(String str) {
        this.h = str;
    }

    public String i() {
        return this.i;
    }

    public void f(String str) {
        this.i = str;
    }

    public long j() {
        return this.j;
    }

    public void c(long j2) {
        this.j = j2;
    }

    public long k() {
        return this.k;
    }

    public void d(long j2) {
        this.k = j2;
    }

    public long l() {
        return this.l;
    }

    public void e(long j2) {
        this.l = j2;
    }

    public long m() {
        return this.m;
    }

    public void f(long j2) {
        this.m = j2;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f2013a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeBooleanArray(new boolean[]{this.d});
        parcel.writeString(this.e);
        parcel.writeLong(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeLong(this.j);
        parcel.writeLong(this.k);
        parcel.writeLong(this.l);
        parcel.writeLong(this.m);
        parcel.writeInt(this.n);
        parcel.writeInt(this.o);
    }

    public void a(Parcel parcel) {
        this.f2013a = parcel.readLong();
        this.b = parcel.readString();
        this.c = parcel.readString();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.d = zArr[0];
        this.e = parcel.readString();
        this.f = parcel.readLong();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readLong();
        this.k = parcel.readLong();
        this.l = parcel.readLong();
        this.m = parcel.readLong();
        this.n = parcel.readInt();
        this.o = parcel.readInt();
    }

    public String toString() {
        return "Announcer [id=" + this.f2013a + ", nickname=" + this.b + ", avatarUrl=" + this.c + ", isVerified=" + this.d + Operators.ARRAY_END_STR;
    }

    public int n() {
        return this.n;
    }

    public void a(int i2) {
        this.n = i2;
    }

    public int o() {
        return this.o;
    }

    public void b(int i2) {
        this.o = i2;
    }
}
