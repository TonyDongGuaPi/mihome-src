package com.ximalaya.ting.android.opensdk.model.live.program;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.model.live.schedule.LiveAnnouncer;
import java.util.ArrayList;
import java.util.List;

public class Program implements Parcelable {
    public static final Parcelable.Creator<Program> CREATOR = new Parcelable.Creator<Program>() {
        /* renamed from: a */
        public Program createFromParcel(Parcel parcel) {
            Program program = new Program();
            program.a(parcel);
            return program;
        }

        /* renamed from: a */
        public Program[] newArray(int i) {
            return new Program[i];
        }
    };
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2081a;
    @SerializedName("program_name")
    private String b;
    @SerializedName("back_pic_url")
    private String c;
    @SerializedName("support_bitrates")
    private int[] d;
    @SerializedName("rate24_aac_url")
    private String e;
    @SerializedName("rate24_ts_url")
    private String f;
    @SerializedName("rate64_aac_url")
    private String g;
    @SerializedName("rate64_ts_url")
    private String h;
    @SerializedName("live_announcers")
    private List<LiveAnnouncer> i;
    @SerializedName("update_at")
    private long j;

    public int describeContents() {
        return 0;
    }

    public long a() {
        return this.f2081a;
    }

    public void a(long j2) {
        this.f2081a = j2;
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

    public int[] d() {
        return this.d;
    }

    public void a(int[] iArr) {
        this.d = iArr;
    }

    public String e() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public String f() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public String g() {
        return this.g;
    }

    public void e(String str) {
        this.g = str;
    }

    public String h() {
        return this.h;
    }

    public void f(String str) {
        this.h = str;
    }

    public List<LiveAnnouncer> i() {
        return this.i;
    }

    public void a(List<LiveAnnouncer> list) {
        this.i = list;
    }

    public long j() {
        return this.j;
    }

    public void b(long j2) {
        this.j = j2;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f2081a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        int length = this.d == null ? 0 : this.d.length;
        parcel.writeInt(length);
        if (length > 0) {
            parcel.writeIntArray(this.d);
        }
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeList(this.i);
        parcel.writeLong(this.j);
    }

    public void a(Parcel parcel) {
        a(parcel.readLong());
        a(parcel.readString());
        b(parcel.readString());
        int readInt = parcel.readInt();
        if (readInt > 0) {
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            a(iArr);
        }
        c(parcel.readString());
        d(parcel.readString());
        e(parcel.readString());
        f(parcel.readString());
        ArrayList arrayList = new ArrayList();
        parcel.readList(arrayList, LiveAnnouncer.class.getClassLoader());
        a((List<LiveAnnouncer>) arrayList);
        b((long) parcel.readInt());
    }
}
