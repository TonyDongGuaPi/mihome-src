package com.miui.tsmclient.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.miui.tsmclient.service.IServiceResponse;

public class ServiceResponseParcelable implements Parcelable {
    public static final Parcelable.Creator<ServiceResponseParcelable> CREATOR = new Parcelable.Creator<ServiceResponseParcelable>() {
        public ServiceResponseParcelable createFromParcel(Parcel parcel) {
            return new ServiceResponseParcelable(parcel);
        }

        public ServiceResponseParcelable[] newArray(int i) {
            return new ServiceResponseParcelable[i];
        }
    };
    private IServiceResponse mResponse;

    public int describeContents() {
        return 0;
    }

    public ServiceResponseParcelable(IServiceResponse iServiceResponse) {
        this.mResponse = iServiceResponse;
    }

    private ServiceResponseParcelable(Parcel parcel) {
        this.mResponse = IServiceResponse.Stub.asInterface(parcel.readStrongBinder());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.mResponse.asBinder());
    }

    public void onResult(Bundle bundle) {
        if (this.mResponse != null) {
            try {
                this.mResponse.onResult(bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    public void onError(int i, String str) {
        if (this.mResponse != null) {
            try {
                this.mResponse.onError(i, str);
            } catch (RemoteException unused) {
            }
        }
    }

    public void onProgress(int i) {
        if (this.mResponse != null) {
            try {
                this.mResponse.onProgress(i);
            } catch (RemoteException unused) {
            }
        }
    }
}
