package com.unionpay.tsmservice.mi.result;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class PinRequestResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public final PinRequestResult createFromParcel(Parcel parcel) {
            return new PinRequestResult(parcel);
        }

        public final PinRequestResult[] newArray(int i) {
            return new PinRequestResult[i];
        }
    };
    private Bundle mResultData;

    public PinRequestResult() {
    }

    public PinRequestResult(Parcel parcel) {
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
