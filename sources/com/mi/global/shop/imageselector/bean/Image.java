package com.mi.global.shop.imageselector.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;

public class Image implements Parcelable {
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        /* renamed from: a */
        public Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        /* renamed from: a */
        public Image[] newArray(int i) {
            return new Image[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f6928a;
    public String b;
    public long c;

    public int describeContents() {
        return 0;
    }

    public Image(String str, String str2, long j) {
        this.f6928a = str;
        this.b = str2;
        this.c = j;
    }

    public boolean equals(Object obj) {
        try {
            return TextUtils.equals(this.f6928a, ((Image) obj).f6928a);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return super.equals(obj);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6928a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
    }

    protected Image(Parcel parcel) {
        this.f6928a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readLong();
    }

    public String toString() {
        return "Image{path='" + this.f6928a + Operators.SINGLE_QUOTE + ", name='" + this.b + Operators.SINGLE_QUOTE + ", time=" + this.c + Operators.BLOCK_END;
    }
}
