package com.ximalaya.ting.android.opensdk.model.history;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.track.Track;

public class HistoryModel implements Parcelable {
    public static final Parcelable.Creator<HistoryModel> CREATOR = new Parcelable.Creator<HistoryModel>() {
        /* renamed from: a */
        public HistoryModel createFromParcel(Parcel parcel) {
            return new HistoryModel(parcel);
        }

        /* renamed from: a */
        public HistoryModel[] newArray(int i) {
            return new HistoryModel[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public boolean f2075a;
    private Track b;
    private Radio c;
    private long d;
    private boolean e;
    private boolean f;
    private long g;
    private int h;
    private String i;
    @Deprecated
    private String j;
    private int k;
    private long l;
    private long m;
    private int n;
    private int o;
    private long p;
    private long q;
    private boolean r;

    public int describeContents() {
        return 0;
    }

    public HistoryModel(Track track, boolean z) {
        this.b = track;
        this.d = track.an();
        this.f2075a = false;
        this.e = z;
        if (TextUtils.isEmpty(track.b())) {
            return;
        }
        if (track.b().equals("track")) {
            this.o = 1;
        } else if (track.b().equals(PlayableModel.d)) {
            this.o = 4;
        }
    }

    public HistoryModel(Radio radio, boolean z) {
        this.c = radio;
        this.d = radio.w();
        this.f2075a = true;
        this.e = z;
        if (TextUtils.isEmpty(radio.b())) {
            return;
        }
        if (radio.y()) {
            this.o = 3;
        } else {
            this.o = 2;
        }
    }

    protected HistoryModel(Parcel parcel) {
        boolean z = false;
        this.f2075a = parcel.readByte() != 0;
        this.b = (Track) parcel.readParcelable(Track.class.getClassLoader());
        this.c = (Radio) parcel.readParcelable(Radio.class.getClassLoader());
        this.d = parcel.readLong();
        this.e = parcel.readByte() != 0;
        this.f = parcel.readByte() != 0;
        this.g = parcel.readLong();
        this.h = parcel.readInt();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readInt();
        this.l = parcel.readLong();
        this.m = parcel.readLong();
        this.n = parcel.readInt();
        this.o = parcel.readInt();
        this.p = parcel.readLong();
        this.q = parcel.readLong();
        this.r = parcel.readByte() != 0 ? true : z;
    }

    public void a(String str, String str2, int i2) {
        this.i = str;
        this.j = str2;
        this.k = i2;
    }

    public void a(long j2, long j3) {
        this.l = j2;
        this.m = j3;
    }

    public Track a() {
        return this.b;
    }

    public Radio b() {
        return this.c;
    }

    public long c() {
        return this.d;
    }

    public boolean d() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(Track track) {
        this.b = track;
    }

    public boolean e() {
        return this.f;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public long f() {
        return this.g;
    }

    public long g() {
        return this.q;
    }

    public void a(long j2) {
        this.q = j2;
    }

    public long h() {
        return this.l;
    }

    public void b(long j2) {
        this.l = j2;
    }

    public void c(long j2) {
        this.d = j2;
    }

    public long i() {
        return this.m;
    }

    public void d(long j2) {
        this.m = j2;
    }

    public void e(long j2) {
        this.g = j2;
    }

    public int j() {
        return this.h;
    }

    public int k() {
        return this.n;
    }

    public void a(int i2) {
        this.n = i2;
    }

    public void b(int i2) {
        this.h = i2;
    }

    public String l() {
        return this.i;
    }

    public boolean m() {
        return this.r;
    }

    public void c(boolean z) {
        this.r = z;
    }

    public void a(String str) {
        this.i = str;
    }

    public String n() {
        return this.j;
    }

    public int o() {
        return this.o;
    }

    public void c(int i2) {
        this.o = i2;
    }

    public void b(String str) {
        this.j = str;
    }

    public int p() {
        return this.k;
    }

    public void d(int i2) {
        this.k = i2;
    }

    public String q() {
        if (this.f2075a) {
            return this.c.h();
        }
        if (this.b == null) {
            return "";
        }
        if (this.b.ao() != null) {
            return this.b.ao().e();
        }
        return this.b.N();
    }

    public long r() {
        if (!(a() == null || a().ao() == null)) {
            this.p = a().ao().d();
        }
        return this.p;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.f2075a ? (byte) 1 : 0);
        parcel.writeParcelable(this.b, i2);
        parcel.writeParcelable(this.c, i2);
        parcel.writeLong(this.d);
        parcel.writeByte(this.e ? (byte) 1 : 0);
        parcel.writeByte(this.f ? (byte) 1 : 0);
        parcel.writeLong(this.g);
        parcel.writeInt(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeInt(this.k);
        parcel.writeLong(this.l);
        parcel.writeLong(this.m);
        parcel.writeInt(this.n);
        parcel.writeInt(this.o);
        parcel.writeLong(this.p);
        parcel.writeLong(this.q);
        parcel.writeByte(this.r ? (byte) 1 : 0);
    }
}
