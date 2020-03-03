package com.xiaomi.youpin.login.api.phone;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;

public class LocalPhoneDetailInfo implements Parcelable {
    public static final Parcelable.Creator<LocalPhoneDetailInfo> CREATOR = new Parcelable.Creator<LocalPhoneDetailInfo>() {
        /* renamed from: a */
        public LocalPhoneDetailInfo createFromParcel(Parcel parcel) {
            return new LocalPhoneDetailInfo(parcel);
        }

        /* renamed from: a */
        public LocalPhoneDetailInfo[] newArray(int i) {
            return new LocalPhoneDetailInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public ActivatorPhoneInfo f23476a;
    public RegisterUserInfo b;
    public int c;

    public int describeContents() {
        return 0;
    }

    public LocalPhoneDetailInfo(ActivatorPhoneInfo activatorPhoneInfo, RegisterUserInfo registerUserInfo, int i) {
        this.f23476a = activatorPhoneInfo;
        this.b = registerUserInfo;
        this.c = i;
    }

    protected LocalPhoneDetailInfo(Parcel parcel) {
        this.f23476a = (ActivatorPhoneInfo) parcel.readParcelable(ActivatorPhoneInfo.class.getClassLoader());
        this.b = (RegisterUserInfo) parcel.readParcelable(RegisterUserInfo.class.getClassLoader());
        this.c = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f23476a, i);
        parcel.writeParcelable(this.b, i);
        parcel.writeInt(this.c);
    }
}
