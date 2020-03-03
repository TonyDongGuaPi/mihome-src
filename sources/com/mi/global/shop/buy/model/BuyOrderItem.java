package com.mi.global.shop.buy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BuyOrderItem implements Parcelable {
    public static final Parcelable.Creator<BuyOrderItem> CREATOR = new Parcelable.Creator<BuyOrderItem>() {
        /* renamed from: a */
        public BuyOrderItem createFromParcel(Parcel parcel) {
            return new BuyOrderItem(parcel);
        }

        /* renamed from: a */
        public BuyOrderItem[] newArray(int i) {
            return new BuyOrderItem[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f6882a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6882a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }

    public BuyOrderItem() {
    }

    protected BuyOrderItem(Parcel parcel) {
        this.f6882a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
    }
}
