package com.unionpay.tsmservice.mi.request;

import android.os.Parcel;
import android.os.Parcelable;

public class GetSeIdRequestParams extends RequestParams {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public final GetSeIdRequestParams createFromParcel(Parcel parcel) {
            return new GetSeIdRequestParams(parcel);
        }

        public final GetSeIdRequestParams[] newArray(int i) {
            return new GetSeIdRequestParams[i];
        }
    };

    public GetSeIdRequestParams() {
    }

    public GetSeIdRequestParams(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
