package com.xiaomi.smarthome.core.entity.account;

import android.os.Parcel;
import android.os.Parcelable;

public enum AccountType implements Parcelable {
    MI,
    WX,
    OAUTH;
    
    public static final Parcelable.Creator<AccountType> CREATOR = null;

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Parcelable.Creator<AccountType>() {
            /* renamed from: a */
            public AccountType createFromParcel(Parcel parcel) {
                return AccountType.values()[parcel.readInt()];
            }

            /* renamed from: a */
            public AccountType[] newArray(int i) {
                return new AccountType[i];
            }
        };
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
