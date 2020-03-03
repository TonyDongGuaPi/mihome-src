package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseResult implements Parcelable {
    public static final Parcelable.Creator<BaseResult> CREATOR = new Parcelable.Creator<BaseResult>() {
        public BaseResult createFromParcel(Parcel parcel) {
            return new BaseResult(parcel);
        }

        public BaseResult[] newArray(int i) {
            return new BaseResult[i];
        }
    };
    private String errmsg;
    private int errno;

    public int describeContents() {
        return 0;
    }

    public int getErrno() {
        return this.errno;
    }

    public void setErrno(int i) {
        this.errno = i;
    }

    public String getErrmsg() {
        return this.errmsg;
    }

    public void setErrmsg(String str) {
        this.errmsg = str;
    }

    public BaseResult() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.errno);
        parcel.writeString(this.errmsg);
    }

    protected BaseResult(Parcel parcel) {
        this.errno = parcel.readInt();
        this.errmsg = parcel.readString();
    }
}
