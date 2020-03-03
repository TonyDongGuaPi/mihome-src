package com.xiaomi.youpin.share;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;

public class ShareObject implements Parcelable {
    public static final Parcelable.Creator<ShareObject> CREATOR = new Parcelable.Creator<ShareObject>() {
        /* renamed from: a */
        public ShareObject createFromParcel(Parcel parcel) {
            return new ShareObject(parcel);
        }

        /* renamed from: a */
        public ShareObject[] newArray(int i) {
            return new ShareObject[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f23669a = "one";
    public static final String b = "more";
    public static final String c = "pic";
    public static final String d = "image/*";
    public static final String e = "video/*";
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private Uri k;
    private Uri l;
    private ArrayList<Uri> m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.x;
    }

    public void a(String str) {
        this.x = str;
    }

    public String b() {
        return this.r;
    }

    public void b(String str) {
        if (str != null) {
            this.r = str;
        }
    }

    public String c() {
        return this.s;
    }

    public void c(String str) {
        if (str != null) {
            this.s = str;
        }
    }

    public String d() {
        return this.t;
    }

    public void d(String str) {
        if (str != null) {
            this.t = str;
        }
    }

    public String e() {
        return this.u;
    }

    public void e(String str) {
        if (str != null) {
            this.u = str;
        }
    }

    public ShareObject() {
        this.r = "";
        this.s = "";
        this.t = "";
        this.u = "";
        this.v = "";
        this.w = d;
        this.x = "";
        this.m = new ArrayList<>();
    }

    public String f() {
        return this.f;
    }

    public void f(String str) {
        this.f = str;
    }

    public String g() {
        return this.g;
    }

    public void g(String str) {
        this.g = str;
    }

    public String h() {
        return this.h;
    }

    public void h(String str) {
        this.h = str;
    }

    public String i() {
        return this.i;
    }

    public void i(String str) {
        this.i = str;
    }

    public String j() {
        return this.j;
    }

    public void j(String str) {
        this.j = str;
    }

    public Uri k() {
        return this.k;
    }

    public void a(Uri uri) {
        this.k = uri;
    }

    public Uri l() {
        return this.l;
    }

    public void b(Uri uri) {
        this.l = uri;
    }

    public ArrayList<Uri> m() {
        return this.m;
    }

    public void a(ArrayList<Uri> arrayList) {
        this.m = arrayList;
    }

    public String n() {
        return this.n;
    }

    public void k(String str) {
        this.n = str;
    }

    public String o() {
        return this.o;
    }

    public void l(String str) {
        this.o = str;
    }

    public String p() {
        return this.p;
    }

    public void m(String str) {
        this.p = str;
    }

    public String q() {
        return this.q;
    }

    public void n(String str) {
        this.q = str;
    }

    public String r() {
        return this.w;
    }

    public void o(String str) {
        this.w = str;
    }

    public String s() {
        return this.v;
    }

    public void p(String str) {
        this.v = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("title: ");
        sb.append(f());
        sb.append(10);
        sb.append("content: ");
        sb.append(g());
        sb.append(10);
        sb.append("wb content: ");
        sb.append(h());
        sb.append(10);
        sb.append("url: ");
        sb.append(i());
        sb.append(10);
        sb.append("filesUrl: ");
        sb.append(j());
        sb.append(10);
        sb.append("thumb: ");
        sb.append(k());
        sb.append(10);
        sb.append("pic: ");
        sb.append(l());
        sb.append("liteId: ");
        sb.append(s());
        sb.append("\nothers: ");
        Iterator<Uri> it = m().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(' ');
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeParcelable(this.k, 0);
        parcel.writeParcelable(this.l, 0);
        parcel.writeTypedList(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.t);
        parcel.writeString(this.u);
        parcel.writeString(this.x);
        parcel.writeString(this.w);
        parcel.writeString(this.v);
    }

    protected ShareObject(Parcel parcel) {
        this.r = "";
        this.s = "";
        this.t = "";
        this.u = "";
        this.v = "";
        this.w = d;
        this.x = "";
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.l = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.m = parcel.createTypedArrayList(Uri.CREATOR);
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readString();
        this.s = parcel.readString();
        this.t = parcel.readString();
        this.u = parcel.readString();
        this.x = parcel.readString();
        this.w = parcel.readString();
        this.v = parcel.readString();
    }
}
