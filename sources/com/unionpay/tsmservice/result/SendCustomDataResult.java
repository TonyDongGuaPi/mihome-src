package com.unionpay.tsmservice.result;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class SendCustomDataResult implements Parcelable {
    public static final Parcelable.Creator<SendCustomDataResult> CREATOR = new Parcelable.Creator<SendCustomDataResult>() {
        public final SendCustomDataResult createFromParcel(Parcel parcel) {
            return new SendCustomDataResult(parcel);
        }

        public final SendCustomDataResult[] newArray(int i) {
            return new SendCustomDataResult[i];
        }
    };
    private Bundle mResultData;

    public SendCustomDataResult() {
    }

    public SendCustomDataResult(Parcel parcel) {
        this.mResultData = parcel.readBundle();
    }

    public int describeContents() {
        return 0;
    }

    public Bundle getResultData() {
        return this.mResultData;
    }

    public void setResultData(Bundle bundle) {
        this.mResultData = bundle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mResultData);
    }
}
