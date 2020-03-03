package com.xiaomi.smarthome.ad.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

public class AdExt implements Parcelable {
    public static final Parcelable.Creator<AdExt> CREATOR = new Parcelable.Creator<AdExt>() {
        /* renamed from: a */
        public AdExt createFromParcel(Parcel parcel) {
            return new AdExt(parcel);
        }

        /* renamed from: a */
        public AdExt[] newArray(int i) {
            return new AdExt[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f13635a;
    private String b;
    private String c;
    private String d;

    public int describeContents() {
        return 0;
    }

    protected AdExt(Parcel parcel) {
        this.f13635a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }

    public AdExt() {
    }

    public String a() {
        return this.f13635a;
    }

    public void a(String str) {
        this.f13635a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13635a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }

    public String toString() {
        return "AdExt{title='" + this.f13635a + Operators.SINGLE_QUOTE + ", msg='" + this.b + Operators.SINGLE_QUOTE + ", left='" + this.c + Operators.SINGLE_QUOTE + ", right='" + this.d + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
