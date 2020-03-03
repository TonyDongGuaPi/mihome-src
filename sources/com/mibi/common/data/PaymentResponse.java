package com.mibi.common.data;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import miuipub.payment.IPaymentManagerResponse;

public class PaymentResponse implements Parcelable {
    public static final Parcelable.Creator<PaymentResponse> CREATOR = new Parcelable.Creator<PaymentResponse>() {
        /* renamed from: a */
        public PaymentResponse createFromParcel(Parcel parcel) {
            return new PaymentResponse(parcel);
        }

        /* renamed from: a */
        public PaymentResponse[] newArray(int i) {
            return new PaymentResponse[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static final String f7534a = "PaymentResponse";
    private IPaymentManagerResponse b;

    public int describeContents() {
        return 0;
    }

    public PaymentResponse(IPaymentManagerResponse iPaymentManagerResponse) {
        this.b = iPaymentManagerResponse;
    }

    public PaymentResponse(IBinder iBinder) {
        this.b = IPaymentManagerResponse.Stub.asInterface(iBinder);
    }

    private PaymentResponse(Parcel parcel) {
        this.b = IPaymentManagerResponse.Stub.asInterface(parcel.readStrongBinder());
    }

    public boolean a() {
        if (this.b == null) {
            return false;
        }
        return this.b.asBinder().pingBinder();
    }

    public void a(Bundle bundle) {
        if (this.b != null) {
            try {
                this.b.onResult(bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    public void a(int i, String str) {
        a(i, str, (Bundle) null);
    }

    public void a(int i, String str, Bundle bundle) {
        if (this.b != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            try {
                this.b.onError(i, str, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.b.asBinder());
    }
}
