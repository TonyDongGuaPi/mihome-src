package com.ximalaya.ting.android.opensdk.model.live.radio;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;

public class Radio extends PlayableModel implements Parcelable {
    public static final Parcelable.Creator<Radio> CREATOR = new Parcelable.Creator<Radio>() {
        /* renamed from: a */
        public Radio[] newArray(int i) {
            return new Radio[i];
        }

        /* renamed from: a */
        public Radio createFromParcel(Parcel parcel) {
            Radio radio = new Radio();
            radio.a(parcel);
            return radio;
        }
    };
    private boolean A = false;
    private long B;
    @SerializedName("radio_name")
    private String j;
    @SerializedName("radio_desc")
    private String k;
    @SerializedName("program_name")
    private String l;
    @SerializedName("schedule_id")
    private long m;
    @SerializedName("start_time")
    private long n;
    @SerializedName("end_time")
    private long o;
    @SerializedName("support_bitrates")
    private int[] p;
    @SerializedName("rate24_aac_url")
    private String q;
    @SerializedName("rate24_ts_url")
    private String r;
    @SerializedName("rate64_aac_url")
    private String s;
    @SerializedName("rate64_ts_url")
    private String t;
    @SerializedName("radio_play_count")
    private int u;
    @SerializedName("cover_url_small")
    private String v;
    @SerializedName("cover_url_large")
    private String w;
    @SerializedName("update_at")
    private long x;
    private long y;
    private String z;

    public int describeContents() {
        return 0;
    }

    public String h() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public String i() {
        return this.k;
    }

    public void d(String str) {
        this.k = str;
    }

    public String j() {
        return this.l;
    }

    public long k() {
        return this.x;
    }

    public void d(long j2) {
        this.x = j2;
    }

    public void e(String str) {
        this.l = str;
    }

    public long l() {
        return this.m;
    }

    public void e(long j2) {
        this.m = j2;
    }

    public long m() {
        return this.n;
    }

    public void f(long j2) {
        this.n = j2;
    }

    public long n() {
        return this.o;
    }

    public void g(long j2) {
        this.o = j2;
    }

    public int[] o() {
        return this.p;
    }

    public void a(int[] iArr) {
        this.p = iArr;
    }

    public String p() {
        return this.q;
    }

    public void f(String str) {
        this.q = str;
    }

    public String q() {
        return this.r;
    }

    public void g(String str) {
        this.r = str;
    }

    public String r() {
        return this.s;
    }

    public void h(String str) {
        this.s = str;
    }

    public String s() {
        return this.t;
    }

    public void i(String str) {
        this.t = str;
    }

    public int t() {
        return this.u;
    }

    public void b(int i) {
        this.u = i;
    }

    public String u() {
        return this.v;
    }

    public void j(String str) {
        this.v = str;
    }

    public String v() {
        return this.w;
    }

    public void k(String str) {
        this.w = str;
    }

    public long w() {
        return this.y;
    }

    public void h(long j2) {
        this.y = j2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeLong(this.m);
        parcel.writeLong(this.n);
        parcel.writeLong(this.o);
        int length = this.p == null ? 0 : this.p.length;
        parcel.writeInt(length);
        if (length > 0) {
            parcel.writeIntArray(this.p);
        }
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.t);
        parcel.writeInt(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        parcel.writeLong(this.y);
        parcel.writeString(this.z);
        parcel.writeInt(this.A ? 1 : 0);
        parcel.writeLong(this.B);
    }

    public void a(Parcel parcel) {
        super.a(parcel);
        c(parcel.readString());
        d(parcel.readString());
        e(parcel.readString());
        e(parcel.readLong());
        f(parcel.readLong());
        g(parcel.readLong());
        int readInt = parcel.readInt();
        if (readInt > 0) {
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            a(iArr);
        }
        f(parcel.readString());
        g(parcel.readString());
        h(parcel.readString());
        i(parcel.readString());
        b(parcel.readInt());
        j(parcel.readString());
        k(parcel.readString());
        h(parcel.readLong());
        l(parcel.readString());
        b(parcel.readInt() != 0);
        i(parcel.readLong());
    }

    public String x() {
        return this.z;
    }

    public void l(String str) {
        this.z = str;
    }

    public boolean y() {
        return this.A;
    }

    public void b(boolean z2) {
        this.A = z2;
    }

    public long z() {
        return this.B;
    }

    public void i(long j2) {
        this.B = j2;
    }

    public String A() {
        if (!TextUtils.isEmpty(this.v)) {
            return this.v;
        }
        return !TextUtils.isEmpty(this.w) ? this.w : "";
    }
}
