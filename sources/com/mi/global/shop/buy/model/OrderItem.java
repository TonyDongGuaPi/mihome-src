package com.mi.global.shop.buy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderItem implements Parcelable {
    public static final Parcelable.Creator<OrderItem> CREATOR = new Parcelable.Creator<OrderItem>() {
        /* renamed from: a */
        public OrderItem createFromParcel(Parcel parcel) {
            return new OrderItem(parcel);
        }

        /* renamed from: a */
        public OrderItem[] newArray(int i) {
            return new OrderItem[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f6887a;
    public String b;
    public String c;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6887a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    public OrderItem() {
    }

    protected OrderItem(Parcel parcel) {
        this.f6887a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }
}
