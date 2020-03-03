package com.unionpay.tsmservice.result;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class UniteResult implements Parcelable {
    public static final Parcelable.Creator<UniteResult> CREATOR = new Parcelable.Creator<UniteResult>() {
        public final UniteResult createFromParcel(Parcel parcel) {
            return new UniteResult(parcel);
        }

        public final UniteResult[] newArray(int i) {
            return new UniteResult[i];
        }
    };
    Bundle mResult;

    public UniteResult() {
    }

    public UniteResult(Parcel parcel) {
        this.mResult = parcel.readBundle();
    }

    public int describeContents() {
        return 0;
    }

    public Bundle getResultData() {
        return this.mResult;
    }

    public void setResult(Bundle bundle) {
        this.mResult = bundle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mResult);
    }
}
