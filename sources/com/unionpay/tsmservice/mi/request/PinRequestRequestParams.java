package com.unionpay.tsmservice.mi.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class PinRequestRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public final PinRequestRequestParams createFromParcel(Parcel parcel) {
            return new PinRequestRequestParams(parcel);
        }

        public final PinRequestRequestParams[] newArray(int i) {
            return new PinRequestRequestParams[i];
        }
    };
    private Bundle mParams;

    public PinRequestRequestParams() {
    }

    public PinRequestRequestParams(Parcel parcel) {
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
