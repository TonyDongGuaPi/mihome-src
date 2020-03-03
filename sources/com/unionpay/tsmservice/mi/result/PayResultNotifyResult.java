package com.unionpay.tsmservice.mi.result;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class PayResultNotifyResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public final PayResultNotifyResult createFromParcel(Parcel parcel) {
            return new PayResultNotifyResult(parcel);
        }

        public final PayResultNotifyResult[] newArray(int i) {
            return new PayResultNotifyResult[i];
        }
    };
    private Bundle mResultData;

    public PayResultNotifyResult() {
    }

    public PayResultNotifyResult(Parcel parcel) {
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
