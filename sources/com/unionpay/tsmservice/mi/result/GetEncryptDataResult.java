package com.unionpay.tsmservice.mi.result;

import android.os.Parcel;
import android.os.Parcelable;

public class GetEncryptDataResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public final GetEncryptDataResult createFromParcel(Parcel parcel) {
            return new GetEncryptDataResult(parcel);
        }

        public final GetEncryptDataResult[] newArray(int i) {
            return new GetEncryptDataResult[i];
        }
    };
    private String mData;

    public GetEncryptDataResult() {
    }

    public GetEncryptDataResult(Parcel parcel) {
        this.mData = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getData() {
        return this.mData;
    }

    public void setData(String str) {
        this.mData = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mData);
    }
}
