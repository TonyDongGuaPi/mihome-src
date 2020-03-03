package com.xiaomi.smarthome.auth.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.authlib.IAuthCallBack;

public class AuthCallBackInfo implements Parcelable {
    public static final Parcelable.Creator<AuthCallBackInfo> CREATOR = new Parcelable.Creator<AuthCallBackInfo>() {
        /* renamed from: a */
        public AuthCallBackInfo createFromParcel(Parcel parcel) {
            return new AuthCallBackInfo(parcel);
        }

        /* renamed from: a */
        public AuthCallBackInfo[] newArray(int i) {
            return new AuthCallBackInfo[i];
        }
    };
    public IAuthCallBack mAuthCallBack;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.mAuthCallBack != null) {
            parcel.writeInt(1);
            parcel.writeStrongBinder(this.mAuthCallBack.asBinder());
        }
    }

    public AuthCallBackInfo() {
    }

    protected AuthCallBackInfo(Parcel parcel) {
        if (parcel.readInt() == 1) {
            this.mAuthCallBack = IAuthCallBack.Stub.asInterface(parcel.readStrongBinder());
        }
    }
}
