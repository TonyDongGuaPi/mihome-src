package com.xiaomi.infrared.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class InfraredControllerInfo implements Parcelable {
    public static final Parcelable.Creator<InfraredControllerInfo> CREATOR = new Parcelable.Creator<InfraredControllerInfo>() {
        /* renamed from: a */
        public InfraredControllerInfo createFromParcel(Parcel parcel) {
            return new InfraredControllerInfo(parcel);
        }

        /* renamed from: a */
        public InfraredControllerInfo[] newArray(int i) {
            return new InfraredControllerInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f10228a;
    private String b;
    private String c;
    private String d;
    private IRType e;
    private String f;
    private String g;
    private IRFunctionType h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private int n;
    private List<IRKeyValue> o;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.m;
    }

    public void a(String str) {
        this.m = str;
    }

    public void b(String str) {
        this.j = str;
    }

    public String b() {
        return this.i;
    }

    public void c(String str) {
        this.i = str;
    }

    public List<IRKeyValue> c() {
        return this.o;
    }

    public void a(List<IRKeyValue> list) {
        this.o = list;
    }

    public String d() {
        return this.f10228a;
    }

    public void d(String str) {
        this.f10228a = str;
    }

    public String e() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public IRType f() {
        return this.e;
    }

    public String g() {
        return this.b;
    }

    public void f(String str) {
        this.b = str;
    }

    public String h() {
        return this.c;
    }

    public void g(String str) {
        this.c = str;
    }

    public void a(IRType iRType) {
        this.e = iRType;
    }

    public String i() {
        return this.f;
    }

    public void h(String str) {
        this.f = str;
    }

    public String j() {
        return this.g;
    }

    public void i(String str) {
        this.g = str;
    }

    public IRFunctionType k() {
        return this.h;
    }

    public void a(IRFunctionType iRFunctionType) {
        this.h = iRFunctionType;
    }

    public InfraredControllerInfo() {
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f10228a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        int i3 = -1;
        parcel.writeInt(this.e == null ? -1 : this.e.ordinal());
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        if (this.h != null) {
            i3 = this.h.ordinal();
        }
        parcel.writeInt(i3);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeInt(this.n);
        parcel.writeTypedList(this.o);
    }

    protected InfraredControllerInfo(Parcel parcel) {
        IRType iRType;
        this.f10228a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        int readInt = parcel.readInt();
        IRFunctionType iRFunctionType = null;
        if (readInt == -1) {
            iRType = null;
        } else {
            iRType = IRType.values()[readInt];
        }
        this.e = iRType;
        this.f = parcel.readString();
        this.g = parcel.readString();
        int readInt2 = parcel.readInt();
        this.h = readInt2 != -1 ? IRFunctionType.values()[readInt2] : iRFunctionType;
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readInt();
        this.o = parcel.createTypedArrayList(IRKeyValue.CREATOR);
    }
}
