package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseGoResult implements Parcelable {
    public static final Parcelable.Creator<BaseGoResult> CREATOR = new Parcelable.Creator<BaseGoResult>() {
        public BaseGoResult createFromParcel(Parcel parcel) {
            return new BaseGoResult(parcel);
        }

        public BaseGoResult[] newArray(int i) {
            return new BaseGoResult[i];
        }
    };
    private int code;
    private String error;

    public int describeContents() {
        return 0;
    }

    public BaseGoResult() {
    }

    protected BaseGoResult(Parcel parcel) {
        this.code = parcel.readInt();
        this.error = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeString(this.error);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }
}
