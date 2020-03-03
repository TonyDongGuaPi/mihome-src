package com.xiaomi.miot;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public class BinderParcel implements Parcelable {
    public static final Parcelable.Creator<BinderParcel> CREATOR = new Parcelable.Creator<BinderParcel>() {
        /* renamed from: a */
        public BinderParcel createFromParcel(Parcel parcel) {
            return new BinderParcel(parcel);
        }

        /* renamed from: a */
        public BinderParcel[] newArray(int i) {
            return new BinderParcel[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    IBinder f11360a;

    public int describeContents() {
        return 0;
    }

    public BinderParcel(IBinder iBinder) {
        this.f11360a = iBinder;
    }

    public IBinder a() {
        return this.f11360a;
    }

    protected BinderParcel(Parcel parcel) {
        this.f11360a = parcel.readStrongBinder();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.f11360a);
    }
}
