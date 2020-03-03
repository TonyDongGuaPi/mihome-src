package com.ximalaya.ting.android.opensdk.model.album;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.model.pay.AlbumPriceTypeDetail;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import java.util.List;

public class Album implements Parcelable {
    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        /* renamed from: a */
        public Album createFromParcel(Parcel parcel) {
            return new Album(parcel);
        }

        /* renamed from: a */
        public Album[] newArray(int i) {
            return new Album[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f2011a = 1;
    public static final int b = 2;
    @SerializedName("category_id")
    private int A;
    @SerializedName("tracks_natural_ordered")
    private boolean B;
    @SerializedName("is_paid")
    private boolean C;
    @SerializedName("estimated_track_count")
    private int D;
    @SerializedName("album_rich_intro")
    private String E;
    @SerializedName("speaker_intro")
    private String F;
    @SerializedName("free_track_count")
    private int G;
    @SerializedName("free_track_ids")
    private String H;
    @SerializedName("sale_intro")
    private String I;
    @SerializedName("expected_revenue")
    private String J;
    @SerializedName("buy_notes")
    private String K;
    @SerializedName("speaker_title")
    private String L;
    @SerializedName("speaker_content")
    private String M;
    @SerializedName("has_sample")
    private boolean N;
    @SerializedName("composed_price_type")
    private int O;
    @SerializedName("price_type_detail")
    private String P;
    @SerializedName("price_type_info")
    private List<AlbumPriceTypeDetail> Q;
    @SerializedName("detail_banner_url")
    private String R;
    @SerializedName("album_score")
    private String S;
    @SerializedName(alternate = {"albumId"}, value = "id")
    private long c;
    @SerializedName(alternate = {"title"}, value = "album_title")
    private String d;
    @SerializedName("album_tags")
    private String e;
    @SerializedName("album_intro")
    private String f;
    @SerializedName("cover_url_small")
    private String g;
    @SerializedName("cover_url_middle")
    private String h;
    @SerializedName("cover_url_large")
    private String i;
    @SerializedName("announcer")
    private Announcer j;
    @SerializedName("play_count")
    private long k;
    @SerializedName("favorite_count")
    private long l;
    @SerializedName("include_track_count")
    private long m;
    @SerializedName("last_uptrack")
    private LastUpTrack n;
    @SerializedName("recommend_track")
    private RecommendTrack o;
    @SerializedName("updated_at")
    private long p;
    @SerializedName("created_at")
    private long q;
    private long r;
    @SerializedName("is_finished")
    private int s;
    @SerializedName("recommend_src")
    private String t;
    @SerializedName("based_relative_album_id")
    private long u;
    @SerializedName("recommend_trace")
    private String v;
    @SerializedName("share_count")
    private String w;
    private List<Track> x;
    @SerializedName("subscribe_count")
    private long y;
    @SerializedName("can_download")
    private boolean z;

    public int describeContents() {
        return 0;
    }

    public Album() {
    }

    public Album(Parcel parcel) {
        this.c = parcel.readLong();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = (Announcer) parcel.readParcelable(Announcer.class.getClassLoader());
        this.k = parcel.readLong();
        this.l = parcel.readLong();
        this.m = parcel.readLong();
        this.n = (LastUpTrack) parcel.readParcelable(LastUpTrack.class.getClassLoader());
        this.o = (RecommendTrack) parcel.readParcelable(RecommendTrack.class.getClassLoader());
        this.p = parcel.readLong();
        this.q = parcel.readLong();
        this.r = parcel.readLong();
        this.s = parcel.readInt();
        this.t = parcel.readString();
        this.u = parcel.readLong();
        this.v = parcel.readString();
        this.w = parcel.readString();
    }

    public static Parcelable.Creator<Album> a() {
        return CREATOR;
    }

    public long b() {
        return this.c;
    }

    public void a(long j2) {
        this.c = j2;
    }

    public String c() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String d() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String e() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public String f() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    public String g() {
        return this.h;
    }

    public void e(String str) {
        this.h = str;
    }

    public String h() {
        return this.i;
    }

    public void f(String str) {
        this.i = str;
    }

    public Announcer i() {
        return this.j;
    }

    public void a(Announcer announcer) {
        this.j = announcer;
    }

    public long j() {
        return this.k;
    }

    public void b(long j2) {
        this.k = j2;
    }

    public long k() {
        return this.l;
    }

    public void c(long j2) {
        this.l = j2;
    }

    public long l() {
        return this.m;
    }

    public void d(long j2) {
        this.m = j2;
    }

    public LastUpTrack m() {
        return this.n;
    }

    public void a(LastUpTrack lastUpTrack) {
        this.n = lastUpTrack;
    }

    public RecommendTrack n() {
        return this.o;
    }

    public void a(RecommendTrack recommendTrack) {
        this.o = recommendTrack;
    }

    public long o() {
        return this.p;
    }

    public void e(long j2) {
        this.p = j2;
    }

    public long p() {
        return this.q;
    }

    public void f(long j2) {
        this.q = j2;
    }

    public long q() {
        return this.r;
    }

    public void g(long j2) {
        this.r = j2;
    }

    public int r() {
        return this.s;
    }

    public void a(int i2) {
        this.s = i2;
    }

    public String s() {
        return this.t;
    }

    public void g(String str) {
        this.t = str;
    }

    public long t() {
        return this.u;
    }

    public void h(long j2) {
        this.u = j2;
    }

    public String u() {
        return this.v;
    }

    public void h(String str) {
        this.v = str;
    }

    public String v() {
        return this.w;
    }

    public void i(String str) {
        this.w = str;
    }

    public List<Track> w() {
        return this.x;
    }

    public void a(List<Track> list) {
        this.x = list;
    }

    public String toString() {
        return "Album [id=" + this.c + ", albumTitle=" + this.d + ", albumTags=" + this.e + ", albumIntro=" + this.f + ", coverUrlSmall=" + this.g + ", coverUrlMiddle=" + this.h + ", coverUrlLarge=" + this.i + ", announcer=" + this.j + ", playCount=" + this.k + ", favoriteCount=" + this.l + ", includeTrackCount=" + this.m + ", lastUptrack=" + this.n + ", recommendTrack=" + this.o + ", updatedAt=" + this.p + ", createdAt=" + this.q + ", soundLastListenId=" + this.r + ", isFinished=" + this.s + ", recommentSrc=" + this.t + ", basedRelativeAlbumId=" + this.u + ", recommendTrace=" + this.v + ", shareCount=" + this.w + Operators.ARRAY_END_STR;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeParcelable(this.j, i2);
        parcel.writeLong(this.k);
        parcel.writeLong(this.l);
        parcel.writeLong(this.m);
        parcel.writeParcelable(this.n, i2);
        parcel.writeParcelable(this.o, i2);
        parcel.writeLong(this.p);
        parcel.writeLong(this.r);
        parcel.writeLong(this.q);
        parcel.writeInt(this.s);
        parcel.writeString(this.t);
        parcel.writeLong(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
    }

    public String x() {
        if (!TextUtils.isEmpty(this.i)) {
            return this.i;
        }
        if (!TextUtils.isEmpty(this.h)) {
            return this.h;
        }
        return !TextUtils.isEmpty(this.g) ? this.g : "";
    }

    public String y() {
        if (!TextUtils.isEmpty(this.h)) {
            return this.h;
        }
        if (!TextUtils.isEmpty(this.i)) {
            return this.i;
        }
        return !TextUtils.isEmpty(this.g) ? this.g : "";
    }

    public boolean z() {
        return this.z;
    }

    public void a(boolean z2) {
        this.z = z2;
    }

    public long A() {
        return this.y;
    }

    public void i(long j2) {
        this.y = j2;
    }

    public int B() {
        return this.A;
    }

    public void b(int i2) {
        this.A = i2;
    }

    public boolean C() {
        return this.B;
    }

    public void b(boolean z2) {
        this.B = z2;
    }

    public boolean D() {
        return this.C;
    }

    public void c(boolean z2) {
        this.C = z2;
    }

    public int E() {
        return this.D;
    }

    public void c(int i2) {
        this.D = i2;
    }

    public String F() {
        return this.E;
    }

    public void j(String str) {
        this.E = str;
    }

    public String G() {
        return this.F;
    }

    public void k(String str) {
        this.F = str;
    }

    public int H() {
        return this.G;
    }

    public void d(int i2) {
        this.G = i2;
    }

    public String I() {
        return this.H;
    }

    public void l(String str) {
        this.H = str;
    }

    public String J() {
        return this.I;
    }

    public void m(String str) {
        this.I = str;
    }

    public String K() {
        return this.J;
    }

    public void n(String str) {
        this.J = str;
    }

    public String L() {
        return this.K;
    }

    public void o(String str) {
        this.K = str;
    }

    public String M() {
        return this.L;
    }

    public void p(String str) {
        this.L = str;
    }

    public String N() {
        return this.M;
    }

    public void q(String str) {
        this.M = str;
    }

    public boolean O() {
        return this.N;
    }

    public void d(boolean z2) {
        this.N = z2;
    }

    public int P() {
        return this.O;
    }

    public void e(int i2) {
        this.O = i2;
    }

    public String Q() {
        return this.R;
    }

    public void r(String str) {
        this.R = str;
    }

    public String R() {
        return this.S;
    }

    public void s(String str) {
        this.S = str;
    }

    public List<AlbumPriceTypeDetail> S() {
        return this.Q;
    }

    public void b(List<AlbumPriceTypeDetail> list) {
        this.Q = list;
    }

    public String T() {
        return this.P;
    }

    public void t(String str) {
        this.P = str;
    }
}
