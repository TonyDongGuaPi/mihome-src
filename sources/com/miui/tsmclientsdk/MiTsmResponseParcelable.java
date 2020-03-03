package com.miui.tsmclientsdk;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.miui.tsmclientsdk.IMiTsmResponse;

public class MiTsmResponseParcelable implements Parcelable {
    public static final Parcelable.Creator<MiTsmResponseParcelable> CREATOR = new Parcelable.Creator<MiTsmResponseParcelable>() {
        public MiTsmResponseParcelable createFromParcel(Parcel parcel) {
            return new MiTsmResponseParcelable(parcel);
        }

        public MiTsmResponseParcelable[] newArray(int i) {
            return new MiTsmResponseParcelable[i];
        }
    };
    private IMiTsmResponse mResponse;

    public int describeContents() {
        return 0;
    }

    public MiTsmResponseParcelable(IMiTsmResponse iMiTsmResponse) {
        this.mResponse = iMiTsmResponse;
    }

    private MiTsmResponseParcelable(Parcel parcel) {
        this.mResponse = IMiTsmResponse.Stub.asInterface(parcel.readStrongBinder());
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
