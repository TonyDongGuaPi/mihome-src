package com.ximalaya.ting.android.opensdk.model.album;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.util.XiMaDataSupport;

public class SubordinatedAlbum extends XiMaDataSupport implements Parcelable {
    public static final Parcelable.Creator<SubordinatedAlbum> CREATOR = new Parcelable.Creator<SubordinatedAlbum>() {
        /* renamed from: a */
        public SubordinatedAlbum[] newArray(int i) {
            return new SubordinatedAlbum[i];
        }

        /* renamed from: a */
        public SubordinatedAlbum createFromParcel(Parcel parcel) {
            SubordinatedAlbum subordinatedAlbum = new SubordinatedAlbum();
            subordinatedAlbum.a(parcel);
            return subordinatedAlbum;
        }
    };
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2030a;
    @SerializedName("album_title")
    private String b;
    @SerializedName("cover_url_small")
    private String c;
    @SerializedName("cover_url_middle")
    private String d;
    @SerializedName("cover_url_large")
    private String e;
    private long f;
    private String g;
    private String h;
    private int i;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public String b() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public long c() {
        return this.f;
    }

    public void a(long j) {
        this.f = j;
    }

    public long d() {
        return this.f2030a;
    }

    public void b(long j) {
        this.f2030a = j;
    }

    public String e() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String f() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String g() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public String h() {
        return this.e;
    }

    public void f(String str) {
        this.e = str;
    }

    public int i() {
        return this.i;
    }

    public void a(int i2) {
        this.i = i2;
    }

    public String toString() {
        return "SubordinatedAlbum [albumId=" + this.f2030a + ", albumTitle=" + this.b + ", coverUrlSmall=" + this.c + ", coverUrlMiddle=" + this.d + ", coverUrlLarge=" + this.e + ", recSrc=" + this.g + ", recTrack=" + this.h + ",serializeStatus=" + this.i + Operators.ARRAY_END_STR;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f2030a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeInt(this.i);
    }

    public void a(Parcel parcel) {
        this.f2030a = parcel.readLong();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readInt();
    }

    public String j() {
        if (!TextUtils.isEmpty(this.d)) {
            return this.d;
        }
        if (!TextUtils.isEmpty(this.c)) {
            return this.c;
        }
        return !TextUtils.isEmpty(this.e) ? this.e : "";
    }
}
