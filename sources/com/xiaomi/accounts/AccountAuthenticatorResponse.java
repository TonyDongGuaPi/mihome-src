package com.xiaomi.accounts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.xiaomi.accounts.IAccountAuthenticatorResponse;
import com.xiaomi.accountsdk.utils.AccountLog;

public class AccountAuthenticatorResponse implements Parcelable {
    public static final Parcelable.Creator<AccountAuthenticatorResponse> CREATOR = new Parcelable.Creator<AccountAuthenticatorResponse>() {
        public AccountAuthenticatorResponse createFromParcel(Parcel parcel) {
            return new AccountAuthenticatorResponse(parcel);
        }

        public AccountAuthenticatorResponse[] newArray(int i) {
            return new AccountAuthenticatorResponse[i];
        }
    };
    private static final String TAG = "AccountAuthenticator";
    private IAccountAuthenticatorResponse mAccountAuthenticatorResponse;

    public int describeContents() {
        return 0;
    }

    public AccountAuthenticatorResponse(IAccountAuthenticatorResponse iAccountAuthenticatorResponse) {
        this.mAccountAuthenticatorResponse = iAccountAuthenticatorResponse;
    }

    public AccountAuthenticatorResponse(Parcel parcel) {
        this.mAccountAuthenticatorResponse = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
    }

    public void onResult(Bundle bundle) {
        if (Log.isLoggable(TAG, 2)) {
            bundle.keySet();
            AccountLog.v(TAG, "AccountAuthenticatorResponse.onResult: " + AccountManager.sanitizeResult(bundle));
        }
        try {
            this.mAccountAuthenticatorResponse.onResult(bundle);
        } catch (RemoteException unused) {
        }
    }

    public void onRequestContinued() {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "AccountAuthenticatorResponse.onRequestContinued");
        }
        try {
            this.mAccountAuthenticatorResponse.onRequestContinued();
        } catch (RemoteException unused) {
        }
    }

    public void onError(int i, String str) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "AccountAuthenticatorResponse.onError: " + i + ", " + str);
        }
        try {
            this.mAccountAuthenticatorResponse.onError(i, str);
        } catch (RemoteException unused) {
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.mAccountAuthenticatorResponse.asBinder());
    }
}
