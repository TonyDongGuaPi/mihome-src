package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ClaimRewardResult extends BaseResult {
    public static final Parcelable.Creator<ClaimRewardResult> CREATOR = new Parcelable.Creator<ClaimRewardResult>() {
        public ClaimRewardResult createFromParcel(Parcel parcel) {
            return new ClaimRewardResult(parcel);
        }

        public ClaimRewardResult[] newArray(int i) {
            return new ClaimRewardResult[i];
        }
    };
    private int point;

    public int describeContents() {
        return 0;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int i) {
        this.point = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.point);
    }

    public ClaimRewardResult() {
    }

    protected ClaimRewardResult(Parcel parcel) {
        super(parcel);
        this.point = parcel.readInt();
    }
}
