package com.ximalaya.ting.android.opensdk.model.track;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.album.Announcer;
import com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum;

public class Track extends PlayableModel implements Parcelable {
    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        /* renamed from: a */
        public Track[] newArray(int i) {
            return new Track[i];
        }

        /* renamed from: a */
        public Track createFromParcel(Parcel parcel) {
            Track track = new Track();
            track.a(parcel);
            return track;
        }
    };
    public static final int n = 0;
    public static final int o = 1;
    public static final int p = 2;
    public static final int q = 3;
    public static final int r = 4;
    public static final int s = 0;
    public static final int t = 1;
    public static final int u = 2;
    public static final int v = 3;
    public static final int w = 100;
    public static final int x = 0;
    public static final int y = 1;
    @SerializedName(alternate = {"trackTags"}, value = "track_tags")
    private String A;
    @SerializedName(alternate = {"trackIntro"}, value = "track_intro")
    private String B;
    @SerializedName(alternate = {"cover_small_path", "coverSmall", "coverUrlSmall"}, value = "cover_url_small")
    private String C;
    @SerializedName(alternate = {"cover_middle_path", "coverUrlMiddle"}, value = "cover_url_middle")
    private String D;
    @SerializedName(alternate = {"cover_large_path", "coverUrlLarge"}, value = "cover_url_large")
    private String E;
    private Announcer F;
    private int G;
    @SerializedName(alternate = {"playCount"}, value = "play_count")
    private int H;
    @SerializedName(alternate = {"favoriteCount"}, value = "favorite_count")
    private int I;
    @SerializedName(alternate = {"commentCount"}, value = "comment_count")
    private int J;
    @SerializedName(alternate = {"downloadCount"}, value = "download_count")
    private int K;
    @SerializedName(alternate = {"play_path_32", "playPath32", "playUrl32"}, value = "play_url_32")
    private String L;
    @SerializedName(alternate = {"playSize32"}, value = "play_size_32")
    private int M;
    @SerializedName(alternate = {"play_path_64", "playPath64"}, value = "play_url_64")
    private String N;
    @SerializedName(alternate = {"playSize64"}, value = "play_size_64")
    private int O;
    @SerializedName(alternate = {"play_path_aac_v224", "playUrl24M4a"}, value = "play_url_24_m4a")
    private String P;
    @SerializedName(alternate = {"playSize24M4a"}, value = "play_size_24_m4a")
    private String Q;
    @SerializedName(alternate = {"play_path_aac_v164", "playUrl64M4a"}, value = "play_url_64_m4a")
    private String R;
    @SerializedName(alternate = {"playSize64m4a"}, value = "play_size_64_m4a")
    private String S;
    @SerializedName(alternate = {"orderNum"}, value = "order_num")
    private int T = -1;
    @SerializedName("type")
    private int U;
    private int V = -1;
    private int W = 0;
    private long X;
    @SerializedName(alternate = {"download_path", "downloadUrl"}, value = "download_url")
    private String Y;
    @SerializedName(alternate = {"downloadSize"}, value = "download_size")
    private long Z;
    @SerializedName(alternate = {"free"}, value = "is_free")
    private boolean aA;
    @SerializedName(alternate = {"is_bought"}, value = "authorized")
    private boolean aB;
    @SerializedName(alternate = {"isPaid"}, value = "is_paid")
    private boolean aC;
    private long aD;
    private int aE;
    @SerializedName(alternate = {"hasSample"}, value = "has_sample")
    private boolean aF;
    private int aG;
    private int aH;
    private int aI;
    private int aJ;
    @SerializedName(alternate = {"sampleDuration"}, value = "sample_duration")
    private int aK;
    private int aL;
    @SerializedName(alternate = {"isTrailer"}, value = "is_trailer")
    private boolean aM;
    @SerializedName(alternate = {"canDownload"}, value = "can_download")
    private boolean aN;
    private long aO;
    @SerializedName(alternate = {"playUrlAmr"}, value = "play_url_amr")
    private String aP;
    @SerializedName(alternate = {"playSizeAmr"}, value = "play_size_amr")
    private int aQ;
    @SerializedName(alternate = {"categoryId"}, value = "category_id")
    private int aR;
    private boolean aS;
    private String aT;
    private String aU;
    private String aV;
    private String aW;
    private String aX;
    private boolean aY;
    private String aZ;
    private int aa;
    @SerializedName(alternate = {"updatedAt"}, value = "updated_at")
    private long ab;
    @SerializedName(alternate = {"album"}, value = "subordinated_album")
    private SubordinatedAlbum ac;
    @SerializedName(alternate = {"createdAt"}, value = "created_at")
    private long ad;
    @SerializedName(alternate = {"playSource"}, value = "play_source")
    private int ae;
    private String af;
    private String ag;
    private long ah;
    private long ai;
    private int aj = -2;
    private String ak;
    private long al;
    private int am = -1;
    private int an = -2;
    private String ao;
    private boolean ap;
    private int aq;
    private long ar;
    private long as = 0;
    private String at;
    private String au;
    private long av;
    private long aw;
    private long ax;
    private double ay;
    private double az;
    private long ba;
    private boolean bb = true;
    private boolean bc;
    private long bd;
    private String be;
    private int bf;
    private int bg;
    private int bh;
    private int bi;
    private int bj;
    private int bk;
    private boolean bl;
    private String bm;
    private String bn;
    public boolean j;
    public int k;
    public String l;
    public String m;
    @SerializedName(alternate = {"title", "trackTitle"}, value = "track_title")
    private String z;

    public int describeContents() {
        return 0;
    }

    public int h() {
        return this.aj;
    }

    public void b(int i) {
        this.aj = i;
    }

    public long i() {
        return this.ah;
    }

    public void d(long j2) {
        this.ah = j2;
    }

    public long j() {
        return this.ai;
    }

    public void e(long j2) {
        this.ai = j2;
    }

    public String k() {
        return this.ak;
    }

    public void c(String str) {
        this.ak = str;
    }

    public String l() {
        return this.ag;
    }

    public void d(String str) {
        this.ag = str;
    }

    public int m() {
        return this.bi;
    }

    public void c(int i) {
        this.bi = i;
    }

    public int n() {
        return this.bj;
    }

    public void d(int i) {
        this.bj = i;
    }

    public int o() {
        return this.bk;
    }

    public void e(int i) {
        this.bk = i;
    }

    public void f(int i) {
        this.bf = i;
    }

    public int p() {
        return this.bf;
    }

    public void g(int i) {
        this.bg = i;
    }

    public int q() {
        return this.bg;
    }

    public void h(int i) {
        this.bh = i;
    }

    public int r() {
        return this.bh;
    }

    public String s() {
        return this.bm;
    }

    public void e(String str) {
        this.bm = str;
    }

    public String t() {
        return this.bn;
    }

    public void f(String str) {
        this.bn = str;
    }

    public void b(boolean z2) {
        this.bl = z2;
    }

    public boolean u() {
        return this.bl;
    }

    public long v() {
        return this.aD;
    }

    public void f(long j2) {
        this.aD = j2;
    }

    public boolean w() {
        return this.aC;
    }

    public void c(boolean z2) {
        this.aC = z2;
    }

    public boolean x() {
        return this.aS;
    }

    public void d(boolean z2) {
        this.aS = z2;
    }

    public long y() {
        return this.aO;
    }

    public void g(long j2) {
        this.aO = j2;
    }

    public double z() {
        return this.ay;
    }

    public void a(double d) {
        this.ay = d;
    }

    public double A() {
        return this.az;
    }

    public void b(double d) {
        this.az = d;
    }

    public boolean B() {
        return this.aA;
    }

    public void e(boolean z2) {
        this.aA = z2;
    }

    public boolean C() {
        return !this.aA && this.aC;
    }

    public boolean D() {
        return this.aB;
    }

    public void f(boolean z2) {
        this.aB = z2;
    }

    public int E() {
        return this.aG;
    }

    public void i(int i) {
        this.aG = i;
    }

    public int F() {
        return this.aH;
    }

    public void j(int i) {
        this.aH = i;
    }

    public int G() {
        return this.aI;
    }

    public void k(int i) {
        this.aI = i;
    }

    public int H() {
        return this.aJ;
    }

    public void l(int i) {
        this.aJ = i;
    }

    public Announcer I() {
        if (this.F == null) {
            return new Announcer();
        }
        return this.F;
    }

    public void a(Announcer announcer) {
        this.F = announcer;
    }

    public boolean J() {
        return this.ap;
    }

    public void g(boolean z2) {
        this.ap = z2;
    }

    public String K() {
        return this.aT;
    }

    public void g(String str) {
        this.aT = str;
    }

    public int L() {
        return this.ae;
    }

    public void m(int i) {
        this.ae = i;
    }

    public long M() {
        return this.ar;
    }

    public void h(long j2) {
        this.ar = j2;
    }

    public String N() {
        return this.z;
    }

    public void h(String str) {
        this.z = str;
    }

    public int O() {
        return this.V;
    }

    public void n(int i) {
        this.V = i;
    }

    public int P() {
        return this.aL;
    }

    public void o(int i) {
        this.aL = i;
    }

    public long Q() {
        return this.X;
    }

    public void i(long j2) {
        this.X = j2;
    }

    public String R() {
        return this.A;
    }

    public void i(String str) {
        this.A = str;
    }

    public String S() {
        return this.B;
    }

    public void j(String str) {
        this.B = str;
    }

    public String T() {
        return this.C;
    }

    public void k(String str) {
        this.C = str;
    }

    public String U() {
        return this.D;
    }

    public void l(String str) {
        this.D = str;
    }

    public String V() {
        return this.E;
    }

    public void m(String str) {
        this.E = str;
    }

    public int W() {
        return this.G;
    }

    public void p(int i) {
        this.G = i;
    }

    public int X() {
        return this.H;
    }

    public void q(int i) {
        this.H = i;
    }

    public int Y() {
        return this.I;
    }

    public void r(int i) {
        this.I = i;
    }

    public int Z() {
        return this.J;
    }

    public void s(int i) {
        this.J = i;
    }

    public int aa() {
        return this.K;
    }

    public void t(int i) {
        this.K = i;
    }

    public String ab() {
        return this.L;
    }

    public void n(String str) {
        this.L = str;
    }

    public int ac() {
        return this.M;
    }

    public void u(int i) {
        this.M = i;
    }

    public String ad() {
        return this.N;
    }

    public void o(String str) {
        this.N = str;
    }

    public int ae() {
        return this.O;
    }

    public void v(int i) {
        this.O = i;
    }

    public String af() {
        return this.P;
    }

    public void p(String str) {
        this.P = str;
    }

    public String ag() {
        return this.Q;
    }

    public void q(String str) {
        this.Q = str;
    }

    public String ah() {
        return this.R;
    }

    public void r(String str) {
        this.R = str;
    }

    public String ai() {
        return this.S;
    }

    public void s(String str) {
        this.S = str;
    }

    public int aj() {
        return this.T;
    }

    public void w(int i) {
        this.T = i;
    }

    public String ak() {
        return this.Y;
    }

    public void t(String str) {
        this.Y = str;
    }

    public long al() {
        return this.Z;
    }

    public void j(long j2) {
        if (j2 != 0) {
            this.Z = j2;
        }
    }

    public void k(long j2) {
        this.Z = j2;
    }

    public int am() {
        return this.aa;
    }

    public void x(int i) {
        this.aa = i;
    }

    public long an() {
        return this.ab;
    }

    public void l(long j2) {
        this.ab = j2;
    }

    @Nullable
    public SubordinatedAlbum ao() {
        return this.ac;
    }

    public void a(SubordinatedAlbum subordinatedAlbum) {
        this.ac = subordinatedAlbum;
    }

    public long ap() {
        return this.ad;
    }

    public void m(long j2) {
        this.ad = j2;
    }

    public String aq() {
        return this.af;
    }

    public void u(String str) {
        this.af = str;
    }

    public long ar() {
        return this.al;
    }

    public void n(long j2) {
        this.al = j2;
    }

    public int as() {
        return this.am;
    }

    public void y(int i) {
        this.am = i;
    }

    public int at() {
        return this.an;
    }

    public void z(int i) {
        this.an = i;
    }

    public String au() {
        return this.ao;
    }

    public void v(String str) {
        this.ao = str;
    }

    public int av() {
        return this.aq;
    }

    public void A(int i) {
        this.aq = i;
    }

    public boolean aw() {
        return this.j;
    }

    public void h(boolean z2) {
        this.j = z2;
    }

    public String ax() {
        return this.at;
    }

    public void w(String str) {
        this.at = str;
    }

    public String ay() {
        return this.au;
    }

    public void x(String str) {
        this.au = str;
    }

    public long az() {
        return this.av;
    }

    public void o(long j2) {
        this.av = j2;
    }

    public String aA() {
        return this.aU;
    }

    public void y(String str) {
        this.aU = str;
    }

    public String aB() {
        return this.aV;
    }

    public void z(String str) {
        this.aV = str;
    }

    public String aC() {
        return this.aW;
    }

    public void A(String str) {
        this.aW = str;
    }

    public String aD() {
        return this.aX;
    }

    public void B(String str) {
        this.aX = str;
    }

    public long aE() {
        return this.aw;
    }

    public void p(long j2) {
        this.aw = j2;
    }

    public long aF() {
        return this.ax;
    }

    public void q(long j2) {
        this.ax = j2;
    }

    public boolean aG() {
        return this.aY;
    }

    public void i(boolean z2) {
        this.aY = z2;
    }

    public long aH() {
        return this.as;
    }

    public void r(long j2) {
        this.as = j2;
    }

    public long aI() {
        return this.ba;
    }

    public void s(long j2) {
        this.ba = j2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.z);
        parcel.writeString(this.A);
        parcel.writeString(this.B);
        parcel.writeString(this.C);
        parcel.writeString(this.D);
        parcel.writeString(this.E);
        parcel.writeParcelable(this.F, i);
        parcel.writeInt(this.G);
        parcel.writeInt(this.H);
        parcel.writeInt(this.I);
        parcel.writeInt(this.J);
        parcel.writeInt(this.K);
        parcel.writeString(this.L);
        parcel.writeInt(this.M);
        parcel.writeString(this.N);
        parcel.writeInt(this.O);
        parcel.writeString(this.P);
        parcel.writeString(this.Q);
        parcel.writeString(this.R);
        parcel.writeString(this.S);
        parcel.writeInt(this.T);
        parcel.writeString(this.Y);
        parcel.writeLong(this.Z);
        parcel.writeLong(this.al);
        parcel.writeParcelable(this.ac, i);
        parcel.writeInt(this.aa);
        parcel.writeLong(this.ab);
        parcel.writeLong(this.ad);
        parcel.writeString(this.af);
        parcel.writeString(this.at);
        parcel.writeString(this.au);
        parcel.writeLong(this.av);
        parcel.writeString(this.aU);
        parcel.writeString(this.aV);
        parcel.writeString(this.aW);
        parcel.writeString(this.aX);
        parcel.writeLong(this.aw);
        parcel.writeLong(this.ax);
        parcel.writeInt(this.aY ? 1 : 0);
        parcel.writeInt(this.ap ? 1 : 0);
        parcel.writeString(this.aT);
        parcel.writeInt(this.ae);
        parcel.writeLong(this.ar);
        parcel.writeString(this.ao);
        parcel.writeString(this.aZ);
        parcel.writeInt(this.aA ? 1 : 0);
        parcel.writeInt(this.aB ? 1 : 0);
        parcel.writeInt(this.aC ? 1 : 0);
        parcel.writeDouble(this.ay);
        parcel.writeDouble(this.az);
        parcel.writeInt(this.aE);
        parcel.writeInt(this.aK);
        parcel.writeInt(this.aL);
        parcel.writeLong(this.aO);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeLong(this.ba);
        parcel.writeString(this.aP);
        parcel.writeInt(this.aQ);
        parcel.writeInt(this.bb ? 1 : 0);
        parcel.writeInt(this.bc ? 1 : 0);
        parcel.writeLong(this.bd);
        parcel.writeString(this.be);
        parcel.writeInt(this.bl ? 1 : 0);
        parcel.writeString(this.bm);
        parcel.writeString(this.bn);
        parcel.writeInt(this.U);
    }

    public void a(Parcel parcel) {
        super.a(parcel);
        this.z = parcel.readString();
        this.A = parcel.readString();
        this.B = parcel.readString();
        this.C = parcel.readString();
        this.D = parcel.readString();
        this.E = parcel.readString();
        this.F = (Announcer) parcel.readParcelable(Announcer.class.getClassLoader());
        this.G = parcel.readInt();
        this.H = parcel.readInt();
        this.I = parcel.readInt();
        this.J = parcel.readInt();
        this.K = parcel.readInt();
        this.L = parcel.readString();
        this.M = parcel.readInt();
        this.N = parcel.readString();
        this.O = parcel.readInt();
        this.P = parcel.readString();
        this.Q = parcel.readString();
        this.R = parcel.readString();
        this.S = parcel.readString();
        this.T = parcel.readInt();
        this.Y = parcel.readString();
        this.Z = parcel.readLong();
        this.al = parcel.readLong();
        this.ac = (SubordinatedAlbum) parcel.readParcelable(SubordinatedAlbum.class.getClassLoader());
        this.aa = parcel.readInt();
        this.ab = parcel.readLong();
        this.ad = parcel.readLong();
        this.af = parcel.readString();
        this.at = parcel.readString();
        this.au = parcel.readString();
        this.av = parcel.readLong();
        this.aU = parcel.readString();
        this.aV = parcel.readString();
        this.aW = parcel.readString();
        this.aX = parcel.readString();
        this.aw = parcel.readLong();
        this.ax = parcel.readLong();
        boolean z2 = false;
        this.aY = parcel.readInt() == 1;
        this.ap = parcel.readInt() == 1;
        this.aT = parcel.readString();
        this.ae = parcel.readInt();
        this.ar = parcel.readLong();
        this.ao = parcel.readString();
        this.aZ = parcel.readString();
        this.aA = parcel.readInt() == 1;
        this.aB = parcel.readInt() == 1;
        this.aC = parcel.readInt() == 1;
        this.ay = parcel.readDouble();
        this.az = parcel.readDouble();
        this.aE = parcel.readInt();
        this.aK = parcel.readInt();
        this.aL = parcel.readInt();
        this.aO = parcel.readLong();
        this.j = parcel.readInt() == 1;
        this.ba = parcel.readLong();
        this.aP = parcel.readString();
        this.aQ = parcel.readInt();
        this.bb = parcel.readInt() == 1;
        this.bc = parcel.readInt() == 1;
        this.bd = parcel.readLong();
        this.be = parcel.readString();
        if (parcel.readInt() == 1) {
            z2 = true;
        }
        this.bl = z2;
        this.bm = parcel.readString();
        this.bn = parcel.readString();
        this.U = parcel.readInt();
    }

    public String toString() {
        return "Track{" + super.toString() + "trackTitle='" + this.z + Operators.SINGLE_QUOTE + ", trackTags='" + this.A + Operators.SINGLE_QUOTE + ", trackIntro='" + this.B + Operators.SINGLE_QUOTE + ", coverUrlSmall='" + this.C + Operators.SINGLE_QUOTE + ", coverUrlMiddle='" + this.D + Operators.SINGLE_QUOTE + ", coverUrlLarge='" + this.E + Operators.SINGLE_QUOTE + ", announcer=" + this.F + ", duration=" + this.G + ", playCount=" + this.H + ", favoriteCount=" + this.I + ", commentCount=" + this.J + ", downloadCount=" + this.K + ", playUrl32='" + this.L + Operators.SINGLE_QUOTE + ", playSize32=" + this.M + ", playUrl64='" + this.N + Operators.SINGLE_QUOTE + ", playSize64=" + this.O + ", playUrl24M4a='" + this.P + Operators.SINGLE_QUOTE + ", playSize24M4a='" + this.Q + Operators.SINGLE_QUOTE + ", playUrl64M4a='" + this.R + Operators.SINGLE_QUOTE + ", playSize64m4a='" + this.S + Operators.SINGLE_QUOTE + ", orderNum=" + this.T + ", orderPositon=" + this.V + ", downloadTime=" + this.X + ", downloadUrl='" + this.Y + Operators.SINGLE_QUOTE + ", downloadSize=" + this.Z + ", source=" + this.aa + ", updatedAt=" + this.ab + ", album=" + this.ac + ", createdAt=" + this.ad + ", playSource=" + this.ae + ", downloadedSaveFilePath='" + this.af + Operators.SINGLE_QUOTE + ", downloadedSize=" + this.al + ", trackStatus=" + this.am + ", downloadStatus=" + this.an + ", sequenceId='" + this.ao + Operators.SINGLE_QUOTE + ", isAutoPaused=" + this.ap + ", insertSequence=" + this.aq + ", timeline=" + this.ar + ", downloadCreated=" + this.as + ", extra=" + this.j + ", startTime='" + this.at + Operators.SINGLE_QUOTE + ", endTime='" + this.au + Operators.SINGLE_QUOTE + ", scheduleId=" + this.av + ", programId=" + this.aw + ", radioId=" + this.ax + ", price=" + this.ay + ", discountedPrice=" + this.az + ", free=" + this.aA + ", authorized=" + this.aB + ", isPaid=" + this.aC + ", uid=" + this.aD + ", priceTypeId=" + this.aE + ", blockIndex=" + this.aG + ", blockNum=" + this.aH + ", protocolVersion=" + this.aI + ", chargeFileSize=" + this.aJ + ", sampleDuration=" + this.aK + ", canDownload=" + this.aN + ", radioName='" + this.aT + Operators.SINGLE_QUOTE + ", radioRate24AacUrl='" + this.aU + Operators.SINGLE_QUOTE + ", radioRate24TsUrl='" + this.aV + Operators.SINGLE_QUOTE + ", radioRate64AacUrl='" + this.aW + Operators.SINGLE_QUOTE + ", radioRate64TsUrl='" + this.aX + Operators.SINGLE_QUOTE + ", isLike=" + this.aY + ", playPathHq='" + this.aZ + Operators.SINGLE_QUOTE + ", priceTypeEnum='" + this.aL + Operators.SINGLE_QUOTE + ", trackActivityId='" + this.aO + Operators.SINGLE_QUOTE + ", liveRoomId='" + this.ba + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public String aJ() {
        return this.aZ;
    }

    public void C(String str) {
        this.aZ = str;
    }

    public int aK() {
        return this.aE;
    }

    public void B(int i) {
        this.aE = i;
    }

    public boolean aL() {
        return this.aN;
    }

    public void j(boolean z2) {
        this.aN = z2;
    }

    public int aM() {
        return this.aK;
    }

    public void C(int i) {
        this.aK = i;
    }

    public boolean aN() {
        return !this.aB && this.aK > 0 && C();
    }

    public boolean aO() {
        return this.aB || this.aK > 0 || this.aA || !this.aC;
    }

    public boolean aP() {
        return this.aM;
    }

    public void k(boolean z2) {
        this.aM = z2;
    }

    public String aQ() {
        return this.aP;
    }

    public void D(String str) {
        this.aP = str;
    }

    public int aR() {
        return this.aQ;
    }

    public void D(int i) {
        this.aQ = i;
    }

    public int aS() {
        return this.aR;
    }

    public void E(int i) {
        this.aR = i;
    }

    public String aT() {
        if (!TextUtils.isEmpty(this.E)) {
            return this.E;
        }
        if (!TextUtils.isEmpty(this.D)) {
            return this.D;
        }
        return !TextUtils.isEmpty(this.C) ? this.C : "";
    }

    public String aU() {
        return this.m;
    }

    public void E(String str) {
        this.m = str;
    }

    public int aV() {
        return this.k;
    }

    public void F(int i) {
        this.k = i;
    }

    public String aW() {
        return this.l;
    }

    public void F(String str) {
        this.l = str;
    }

    public boolean aX() {
        return this.aF;
    }

    public void l(boolean z2) {
        this.aF = z2;
    }

    public void G(int i) {
        this.W = i;
    }

    public int aY() {
        return this.U;
    }

    public void H(int i) {
        this.U = i;
    }

    public int aZ() {
        return this.W;
    }

    public boolean ba() {
        return this.bb;
    }

    public void m(boolean z2) {
        this.bb = z2;
    }

    public boolean bb() {
        return this.bc;
    }

    public void n(boolean z2) {
        this.bc = z2;
    }

    public long bc() {
        return this.bd;
    }

    public void t(long j2) {
        this.bd = j2;
    }

    public String bd() {
        return this.be;
    }

    public void G(String str) {
        this.be = str;
    }

    public boolean be() {
        return this.U != 4;
    }
}
