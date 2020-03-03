package com.unionpay.tsmservice.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class UniteRequestParams extends RequestParams {
    public static final Parcelable.Creator<UniteRequestParams> CREATOR = new Parcelable.Creator<UniteRequestParams>() {
        public final UniteRequestParams createFromParcel(Parcel parcel) {
            return new UniteRequestParams(parcel);
        }

        public final UniteRequestParams[] newArray(int i) {
            return new UniteRequestParams[i];
        }
    };
    private Bundle mParams;

    public UniteRequestParams() {
    }

    public UniteRequestParams(Parcel parcel) {
        super(parcel);
        this.mParams = parcel.readBundle();
    }

    public int describeContents() {
        return 0;
    }

    public Bundle getParams() {
        return this.mParams;
    }

    public void setParams(Bundle bundle) {
        this.mParams = bundle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(this.mParams);
    }
}
