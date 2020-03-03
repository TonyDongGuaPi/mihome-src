package com.ximalaya.ting.android.opensdk.model.advertis;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.ArrayList;
import java.util.List;

public class AdvertisList extends XimalayaResponse implements Parcelable {
    public static final Parcelable.Creator<AdvertisList> CREATOR = new Parcelable.Creator<AdvertisList>() {
        /* renamed from: a */
        public AdvertisList[] newArray(int i) {
            return new AdvertisList[i];
        }

        /* renamed from: a */
        public AdvertisList createFromParcel(Parcel parcel) {
            AdvertisList advertisList = new AdvertisList();
            advertisList.a(parcel);
            return advertisList;
        }
    };
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private List<Advertis> f2010a;
    private int b = -2;
    private String c;
    private int d;
    private long e;
    private boolean f;

    public int describeContents() {
        return 0;
    }

    public List<Advertis> a() {
        return this.f2010a;
    }

    public void a(List<Advertis> list) {
        this.f2010a = list;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public int d() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public void a(Parcel parcel) {
        a(parcel.readLong());
        a(parcel.readInt());
        b(parcel.readInt());
        a(parcel.readString());
        if (parcel.readInt() > 0) {
            ArrayList arrayList = new ArrayList();
            parcel.readList(arrayList, Advertis.class.getClassLoader());
            a((List<Advertis>) arrayList);
        }
        boolean z = true;
        if (parcel.readInt() != 1) {
            z = false;
        }
        a(z);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.e);
        parcel.writeInt(this.b);
        parcel.writeInt(this.d);
        parcel.writeString(this.c);
        int size = this.f2010a == null ? 0 : this.f2010a.size();
        parcel.writeInt(size);
        if (size > 0) {
            parcel.writeList(this.f2010a);
        }
        parcel.writeInt(this.f ? 1 : 0);
    }

    public String toString() {
        return "AdvertisList [advertisList=" + this.f2010a + ", ret=" + this.b + ", msg=" + this.c + ", source=" + this.d + ", responseId=" + this.e + Operators.ARRAY_END_STR;
    }

    public long e() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public boolean f() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }
}
