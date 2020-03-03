package com.ximalaya.ting.android.opensdk.model.album;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class RecommendTrack implements Parcelable {
    public static final Parcelable.Creator<RecommendTrack> CREATOR = new Parcelable.Creator<RecommendTrack>() {
        /* renamed from: a */
        public RecommendTrack createFromParcel(Parcel parcel) {
            return new RecommendTrack(parcel);
        }

        /* renamed from: a */
        public RecommendTrack[] newArray(int i) {
            return new RecommendTrack[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private long f2027a;
    @SerializedName("human_recommend_track_id")
    private long b;
    @SerializedName("human_recommend_real_title")
    private String c;
    @SerializedName("human_recommend_track_title")
    private String d;

    public int describeContents() {
        return 0;
    }

    public RecommendTrack() {
    }

    public RecommendTrack(Parcel parcel) {
        this.f2027a = parcel.readLong();
        this.b = parcel.readLong();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }

    public long a() {
        return this.f2027a;
    }

    public void a(long j) {
        this.f2027a = j;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String toString() {
        return "RecommendTrack [uid=" + this.f2027a + ", trackId=" + this.b + ", realTitle=" + this.c + ", tackTitle=" + this.d + Operators.ARRAY_END_STR;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f2027a);
        parcel.writeLong(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }
}
