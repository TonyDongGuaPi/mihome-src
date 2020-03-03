package com.unionpay.tsmservice.mi.result;

import android.os.Parcel;
import android.os.Parcelable;

public class ExchangeKeyResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public final ExchangeKeyResult createFromParcel(Parcel parcel) {
            return new ExchangeKeyResult(parcel);
        }

        public final ExchangeKeyResult[] newArray(int i) {
            return new ExchangeKeyResult[i];
        }
    };
    private String key;

    public ExchangeKeyResult() {
    }

    public ExchangeKeyResult(Parcel parcel) {
        this.key = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
    }
}
