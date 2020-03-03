package com.xiaomi.smarthome.frame.login.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.accountsdk.account.data.AccountInfo;

public class LoginMiByPasstokenSDKResult implements Parcelable {
    public static final Parcelable.Creator<LoginMiByPasstokenSDKResult> CREATOR = new Parcelable.Creator<LoginMiByPasstokenSDKResult>() {
        /* renamed from: a */
        public LoginMiByPasstokenSDKResult createFromParcel(Parcel parcel) {
            return new LoginMiByPasstokenSDKResult(parcel);
        }

        /* renamed from: a */
        public LoginMiByPasstokenSDKResult[] newArray(int i) {
            return new LoginMiByPasstokenSDKResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public AccountInfo f16171a;
    public long b;

    public int describeContents() {
        return 0;
    }

    public LoginMiByPasstokenSDKResult() {
    }

    protected LoginMiByPasstokenSDKResult(Parcel parcel) {
        this.f16171a = (AccountInfo) parcel.readParcelable(AccountInfo.class.getClassLoader());
        this.b = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f16171a, i);
        parcel.writeLong(this.b);
    }
}
