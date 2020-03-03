package com.liulishuo.filedownloader.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FileDownloadModel implements Parcelable {
    public static final Parcelable.Creator<FileDownloadModel> CREATOR = new Parcelable.Creator<FileDownloadModel>() {
        /* renamed from: a */
        public FileDownloadModel createFromParcel(Parcel parcel) {
            return new FileDownloadModel(parcel);
        }

        /* renamed from: a */
        public FileDownloadModel[] newArray(int i) {
            return new FileDownloadModel[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f6442a = -1;
    public static final int b = 100;
    public static final String c = "_id";
    public static final String d = "url";
    public static final String e = "path";
    public static final String f = "pathAsDirectory";
    public static final String g = "filename";
    public static final String h = "status";
    public static final String i = "sofar";
    public static final String j = "total";
    public static final String k = "errMsg";
    public static final String l = "etag";
    public static final String m = "connectionCount";
    private int n;
    private String o;
    private String p;
    private boolean q;
    private String r;
    private final AtomicInteger s;
    private final AtomicLong t;
    private long u;
    private String v;
    private String w;
    private int x;
    private boolean y;

    public int describeContents() {
        return 0;
    }

    public void a(int i2) {
        this.n = i2;
    }

    public void a(String str) {
        this.o = str;
    }

    public void a(String str, boolean z) {
        this.p = str;
        this.q = z;
    }

    public void a(byte b2) {
        this.s.set(b2);
    }

    public void a(long j2) {
        this.t.set(j2);
    }

    public void b(long j2) {
        this.t.addAndGet(j2);
    }

    public void c(long j2) {
        this.y = j2 > 2147483647L;
        this.u = j2;
    }

    public int a() {
        return this.n;
    }

    public String b() {
        return this.o;
    }

    public String c() {
        return this.p;
    }

    public String d() {
        return FileDownloadUtils.a(c(), l(), m());
    }

    public String e() {
        if (d() == null) {
            return null;
        }
        return FileDownloadUtils.e(d());
    }

    public byte f() {
        return (byte) this.s.get();
    }

    public long g() {
        return this.t.get();
    }

    public long h() {
        return this.u;
    }

    public boolean i() {
        return this.u == -1;
    }

    public String j() {
        return this.w;
    }

    public void b(String str) {
        this.w = str;
    }

    public String k() {
        return this.v;
    }

    public void c(String str) {
        this.v = str;
    }

    public void d(String str) {
        this.r = str;
    }

    public boolean l() {
        return this.q;
    }

    public String m() {
        return this.r;
    }

    public void b(int i2) {
        this.x = i2;
    }

    public int n() {
        return this.x;
    }

    public void o() {
        this.x = 1;
    }

    public ContentValues p() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(a()));
        contentValues.put("url", b());
        contentValues.put("path", c());
        contentValues.put("status", Byte.valueOf(f()));
        contentValues.put(i, Long.valueOf(g()));
        contentValues.put("total", Long.valueOf(h()));
        contentValues.put("errMsg", k());
        contentValues.put("etag", j());
        contentValues.put(m, Integer.valueOf(n()));
        contentValues.put(f, Boolean.valueOf(l()));
        if (l() && m() != null) {
            contentValues.put("filename", m());
        }
        return contentValues;
    }

    public boolean q() {
        return this.y;
    }

    public void r() {
        s();
        t();
    }

    public void s() {
        String e2 = e();
        if (e2 != null) {
            File file = new File(e2);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public void t() {
        String d2 = d();
        if (d2 != null) {
            File file = new File(d2);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public String toString() {
        return FileDownloadUtils.a("id[%d], url[%s], path[%s], status[%d], sofar[%s], total[%d], etag[%s], %s", Integer.valueOf(this.n), this.o, this.p, Integer.valueOf(this.s.get()), this.t, Long.valueOf(this.u), this.w, super.toString());
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeByte(this.q ? (byte) 1 : 0);
        parcel.writeString(this.r);
        parcel.writeByte((byte) this.s.get());
        parcel.writeLong(this.t.get());
        parcel.writeLong(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        parcel.writeInt(this.x);
        parcel.writeByte(this.y ? (byte) 1 : 0);
    }

    public FileDownloadModel() {
        this.t = new AtomicLong();
        this.s = new AtomicInteger();
    }

    protected FileDownloadModel(Parcel parcel) {
        this.n = parcel.readInt();
        this.o = parcel.readString();
        this.p = parcel.readString();
        boolean z = false;
        this.q = parcel.readByte() != 0;
        this.r = parcel.readString();
        this.s = new AtomicInteger(parcel.readByte());
        this.t = new AtomicLong(parcel.readLong());
        this.u = parcel.readLong();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.x = parcel.readInt();
        this.y = parcel.readByte() != 0 ? true : z;
    }
}
