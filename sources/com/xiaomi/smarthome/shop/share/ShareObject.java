package com.xiaomi.smarthome.shop.share;

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
    private String f22184a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Uri f;
    private Uri g;
    private ArrayList<Uri> h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.q;
    }

    public void a(String str) {
        this.q = str;
    }

    public String b() {
        return this.m;
    }

    public void b(String str) {
        if (str != null) {
            this.m = str;
        }
    }

    public String c() {
        return this.n;
    }

    public void c(String str) {
        if (str != null) {
            this.n = str;
        }
    }

    public String d() {
        return this.o;
    }

    public void d(String str) {
        if (str != null) {
            this.o = str;
        }
    }

    public String e() {
        return this.p;
    }

    public void e(String str) {
        if (str != null) {
            this.p = str;
        }
    }

    public ShareObject() {
        this.m = "";
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = "";
        this.h = new ArrayList<>();
    }

    public String f() {
        return this.f22184a;
    }

    public void f(String str) {
        this.f22184a = str;
    }

    public String g() {
        return this.b;
    }

    public void g(String str) {
        this.b = str;
    }

    public String h() {
        return this.c;
    }

    public void h(String str) {
        this.c = str;
    }

    public String i() {
        return this.d;
    }

    public void i(String str) {
        this.d = str;
    }

    public String j() {
        return this.e;
    }

    public void j(String str) {
        this.e = str;
    }

    public Uri k() {
        return this.f;
    }

    public void a(Uri uri) {
        this.f = uri;
    }

    public Uri l() {
        return this.g;
    }

    public void b(Uri uri) {
        this.g = uri;
    }

    public ArrayList<Uri> m() {
        return this.h;
    }

    public void a(ArrayList<Uri> arrayList) {
        this.h = arrayList;
    }

    public String n() {
        return this.i;
    }

    public void k(String str) {
        this.i = str;
    }

    public String o() {
        return this.j;
    }

    public void l(String str) {
        this.j = str;
    }

    public String p() {
        return this.k;
    }

    public void m(String str) {
        this.k = str;
    }

    public String q() {
        return this.l;
    }

    public void n(String str) {
        this.l = str;
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
        sb.append("\nothers: ");
        Iterator<Uri> it = m().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(' ');
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f22184a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeParcelable(this.f, 0);
        parcel.writeParcelable(this.g, 0);
        parcel.writeTypedList(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
    }

    protected ShareObject(Parcel parcel) {
        this.m = "";
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = "";
        this.f22184a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.g = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.h = parcel.createTypedArrayList(Uri.CREATOR);
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
    }
}
