package com.ximalaya.ting.android.opensdk.model.album;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class LastUpTrack implements Parcelable {
    public static Parcelable.Creator<LastUpTrack> CREATOR = new Parcelable.Creator<LastUpTrack>() {
        /* renamed from: a */
        public LastUpTrack[] newArray(int i) {
            return new LastUpTrack[i];
        }

        /* renamed from: a */
        public LastUpTrack createFromParcel(Parcel parcel) {
            return new LastUpTrack(parcel);
        }
    };
    @SerializedName("track_id")

    /* renamed from: a  reason: collision with root package name */
    private long f2025a;
    @SerializedName("track_title")
    private String b;
    @SerializedName("duration")
    private long c;
    @SerializedName("created_at")
    private long d;
    @SerializedName("updated_at")
    private long e;

    public int describeContents() {
        return 0;
    }

    public LastUpTrack() {
    }

    public LastUpTrack(Parcel parcel) {
        this.f2025a = parcel.readLong();
        this.c = parcel.readLong();
        this.d = parcel.readLong();
        this.e = parcel.readLong();
        this.b = parcel.readString();
    }

    public long a() {
        return this.f2025a;
    }

    public void a(long j) {
        this.f2025a = j;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public long c() {
        return this.c;
    }

    public void b(long j) {
        this.c = j;
    }

    public long d() {
        return this.d;
    }

    public void c(long j) {
        this.d = j;
    }

    public long e() {
        return this.e;
    }

    public void d(long j) {
        this.e = j;
    }

    public String toString() {
        return "LastUpTrack [trackId=" + this.f2025a + ", trackTitle=" + this.b + ", duration=" + this.c + ", createdAt=" + this.d + ", updatedAt=" + this.e + Operators.ARRAY_END_STR;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.d);
        parcel.writeLong(this.e);
        parcel.writeLong(this.f2025a);
        parcel.writeLong(this.c);
        parcel.writeString(this.b);
    }
}
