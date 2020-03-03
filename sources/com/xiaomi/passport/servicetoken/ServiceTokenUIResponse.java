package com.xiaomi.passport.servicetoken;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.servicetoken.IServiceTokenUIResponse;

public class ServiceTokenUIResponse implements Parcelable {
    public static final Parcelable.Creator<ServiceTokenUIResponse> CREATOR = new Parcelable.Creator<ServiceTokenUIResponse>() {
        public ServiceTokenUIResponse createFromParcel(Parcel parcel) {
            return new ServiceTokenUIResponse(parcel);
        }

        public ServiceTokenUIResponse[] newArray(int i) {
            return new ServiceTokenUIResponse[i];
        }
    };
    private static final String TAG = "ServiceTokenUIResponse";
    private IServiceTokenUIResponse mResponse;

    public int describeContents() {
        return 0;
    }

    public ServiceTokenUIResponse(IServiceTokenUIResponse iServiceTokenUIResponse) {
        this.mResponse = iServiceTokenUIResponse;
    }

    public ServiceTokenUIResponse(Parcel parcel) {
        this.mResponse = IServiceTokenUIResponse.Stub.asInterface(parcel.readStrongBinder());
    }

    public void onResult(Bundle bundle) {
        if (Log.isLoggable(TAG, 2)) {
            bundle.keySet();
            AccountLog.v(TAG, "AccountAuthenticatorResponse.onResult");
        }
        try {
            this.mResponse.onResult(bundle);
        } catch (RemoteException unused) {
        }
    }

    public void onRequestContinued() {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "AccountAuthenticatorResponse.onRequestContinued");
        }
        try {
            this.mResponse.onRequestContinued();
        } catch (RemoteException unused) {
        }
    }

    public void onError(int i, String str) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "AccountAuthenticatorResponse.onError: " + i + ", " + str);
        }
        try {
            this.mResponse.onError(i, str);
        } catch (RemoteException unused) {
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.mResponse.asBinder());
    }
}
