package com.xiaomi.youpin.share.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class PosterData implements Parcelable {
    public static final Parcelable.Creator<PosterData> CREATOR = new Parcelable.Creator<PosterData>() {
        /* renamed from: a */
        public PosterData createFromParcel(Parcel parcel) {
            return new PosterData(parcel);
        }

        /* renamed from: a */
        public PosterData[] newArray(int i) {
            return new PosterData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f23682a;
    public String b;
    public String c;
    public int d;
    public boolean e;
    public String f;

    public int describeContents() {
        return 0;
    }

    public PosterData() {
    }

    public boolean a() {
        return !TextUtils.isEmpty(this.f23682a) && !TextUtils.isEmpty(this.b) && !TextUtils.isEmpty(this.c);
    }

    protected PosterData(Parcel parcel) {
        this.f23682a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readByte() != 0;
        this.f = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f23682a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeByte(this.e ? (byte) 1 : 0);
        parcel.writeString(this.f);
    }

    public static PosterData b() {
        PosterData posterData = new PosterData();
        posterData.f23682a = "进口北美冬青";
        posterData.f = "预售";
        posterData.b = "国宴级花材，果实色艳饱满，节庆装饰精品";
        posterData.d = 29900;
        posterData.e = true;
        posterData.c = "https://shop.io.mi-img.com/app/shop/img?id=shop_45972020ebd6e671feaddb538f22f665.jpeg&w=366&h=431&t=webp";
        return posterData;
    }
}
