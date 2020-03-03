package com.unionpay.tsmservice.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class SendCustomDataRequestParams extends RequestParams {
    public static final Parcelable.Creator<SendCustomDataRequestParams> CREATOR = new Parcelable.Creator<SendCustomDataRequestParams>() {
        public final SendCustomDataRequestParams createFromParcel(Parcel parcel) {
            return new SendCustomDataRequestParams(parcel);
        }

        public final SendCustomDataRequestParams[] newArray(int i) {
            return new SendCustomDataRequestParams[i];
        }
    };
    private Bundle mParams;

    public SendCustomDataRequestParams() {
    }

    public SendCustomDataRequestParams(Parcel parcel) {
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
