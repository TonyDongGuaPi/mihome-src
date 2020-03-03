package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GetPushFlagModel extends BaseResult {
    public static final Parcelable.Creator<GetPushFlagModel> CREATOR = new Parcelable.Creator<GetPushFlagModel>() {
        public GetPushFlagModel createFromParcel(Parcel parcel) {
            return new GetPushFlagModel(parcel);
        }

        public GetPushFlagModel[] newArray(int i) {
            return new GetPushFlagModel[i];
        }
    };
    private int pushmsgflag = -1;

    public int describeContents() {
        return 0;
    }

    public int getPushmsgflag() {
        return this.pushmsgflag;
    }

    public void setPushmsgflag(int i) {
        this.pushmsgflag = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.pushmsgflag);
    }

    public GetPushFlagModel() {
    }

    protected GetPushFlagModel(Parcel parcel) {
        super(parcel);
        this.pushmsgflag = parcel.readInt();
    }
}
