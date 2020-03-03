package com.unionpay.tsmservice.mi.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class PayResultNotifyRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public final PayResultNotifyRequestParams createFromParcel(Parcel parcel) {
            return new PayResultNotifyRequestParams(parcel);
        }

        public final PayResultNotifyRequestParams[] newArray(int i) {
            return new PayResultNotifyRequestParams[i];
        }
    };
    private Bundle mParams;

    public PayResultNotifyRequestParams() {
    }

    public PayResultNotifyRequestParams(Parcel parcel) {
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
