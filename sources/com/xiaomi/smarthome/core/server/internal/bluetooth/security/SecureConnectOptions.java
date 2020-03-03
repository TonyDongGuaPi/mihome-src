package com.xiaomi.smarthome.core.server.internal.bluetooth.security;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;

public class SecureConnectOptions implements Parcelable {
    public static final Parcelable.Creator<SecureConnectOptions> CREATOR = new Parcelable.Creator<SecureConnectOptions>() {
        /* renamed from: a */
        public SecureConnectOptions createFromParcel(Parcel parcel) {
            return new SecureConnectOptions(parcel);
        }

        /* renamed from: a */
        public SecureConnectOptions[] newArray(int i) {
            return new SecureConnectOptions[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private BleConnectOptions f14321a;

    public int describeContents() {
        return 0;
    }

    private SecureConnectOptions() {
    }

    public BleConnectOptions a() {
        return this.f14321a;
    }

    /* access modifiers changed from: private */
    public void a(BleConnectOptions bleConnectOptions) {
        this.f14321a = bleConnectOptions;
    }

    protected SecureConnectOptions(Parcel parcel) {
        this.f14321a = (BleConnectOptions) parcel.readParcelable(BleConnectOptions.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f14321a, i);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private BleConnectOptions f14322a;

        public Builder a(BleConnectOptions bleConnectOptions) {
            this.f14322a = bleConnectOptions;
            return this;
        }

        public SecureConnectOptions a() {
            SecureConnectOptions secureConnectOptions = new SecureConnectOptions();
            secureConnectOptions.a(this.f14322a);
            return secureConnectOptions;
        }
    }
}
