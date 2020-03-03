package com.ximalaya.ting.android.opensdk.model.live.schedule;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.live.program.Program;

public class Schedule extends PlayableModel implements Parcelable {
    public static final Parcelable.Creator<Schedule> CREATOR = new Parcelable.Creator<Schedule>() {
        /* renamed from: a */
        public Schedule[] newArray(int i) {
            return new Schedule[i];
        }

        /* renamed from: a */
        public Schedule createFromParcel(Parcel parcel) {
            Schedule schedule = new Schedule();
            schedule.a(parcel);
            return schedule;
        }
    };
    @SerializedName("start_time")
    private String j;
    @SerializedName("end_time")
    private String k;
    @SerializedName("related_program")
    private Program l;
    @SerializedName("update_at")
    private long m;
    @SerializedName("play_type")
    private int n;
    @SerializedName("listen_back_url")
    private String o;
    @SerializedName("radio_id")
    private long p;
    private String q;
    private int r;

    public int describeContents() {
        return 0;
    }

    public String h() {
        int length;
        if (TextUtils.isEmpty(this.j) || (length = this.j.split(":").length) <= 2) {
            return this.j;
        }
        return this.j.split(":", length - 1)[length - 2];
    }

    public String i() {
        int length;
        if (TextUtils.isEmpty(this.k) || (length = this.k.split(":").length) <= 2) {
            return this.k;
        }
        return this.k.split(":", length - 1)[length - 2];
    }

    public String j() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public String k() {
        return this.k;
    }

    public void d(String str) {
        this.k = str;
    }

    public Program l() {
        return this.l;
    }

    public void a(Program program) {
        this.l = program;
    }

    public long m() {
        return this.m;
    }

    public void d(long j2) {
        this.m = j2;
    }

    public int n() {
        return this.n;
    }

    public void b(int i) {
        this.n = i;
    }

    public String o() {
        return this.o;
    }

    public void e(String str) {
        this.o = str;
    }

    public long p() {
        return this.p;
    }

    public void e(long j2) {
        this.p = j2;
    }

    public void a(Parcel parcel) {
        super.a(parcel);
        c(parcel.readString());
        d(parcel.readString());
        e(parcel.readLong());
        f(parcel.readString());
        a((Program) parcel.readParcelable(Schedule.class.getClassLoader()));
        c(parcel.readInt());
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeLong(this.p);
        parcel.writeParcelable(this.l, 0);
        parcel.writeString(this.q);
        parcel.writeInt(this.r);
    }

    public String q() {
        return this.q;
    }

    public void f(String str) {
        this.q = str;
    }

    public int r() {
        return this.r;
    }

    public void c(int i) {
        this.r = i;
    }
}
