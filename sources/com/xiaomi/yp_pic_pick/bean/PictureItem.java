package com.xiaomi.yp_pic_pick.bean;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class PictureItem implements Parcelable {
    public static final Parcelable.Creator<PictureItem> CREATOR = new Parcelable.Creator<PictureItem>() {
        /* renamed from: a */
        public PictureItem createFromParcel(Parcel parcel) {
            return new PictureItem(parcel);
        }

        /* renamed from: a */
        public PictureItem[] newArray(int i) {
            return new PictureItem[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f19523a = 1;
    public static final int b = 2;
    private int c = 2;
    private String d;
    private Uri e;
    private String f;
    private boolean g;
    private String h;

    public int describeContents() {
        return 0;
    }

    public Uri a() {
        return this.e;
    }

    public void a(Uri uri) {
        this.e = uri;
    }

    public String b() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public boolean c() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public int e() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public void c(String str) {
        this.h = str;
    }

    public String f() {
        return this.h;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeParcelable(this.e, i);
        parcel.writeString(this.f);
        parcel.writeByte(this.g ? (byte) 1 : 0);
        parcel.writeString(this.h);
    }

    public PictureItem() {
    }

    protected PictureItem(Parcel parcel) {
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.f = parcel.readString();
        this.g = parcel.readByte() != 0;
        this.h = parcel.readString();
    }
}
