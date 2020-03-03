package com.mibi.common.account;

import android.accounts.AccountsException;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.exception.AccountException;
import com.mibi.common.exception.PaymentException;

class GuestAccountLoader implements AccountLoader {
    public static final Parcelable.Creator<AccountLoader> CREATOR = new Parcelable.Creator<AccountLoader>() {
        /* renamed from: a */
        public AccountLoader createFromParcel(Parcel parcel) {
            return new GuestAccountLoader();
        }

        /* renamed from: a */
        public AccountLoader[] newArray(int i) {
            return new GuestAccountLoader[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private AccountToken f7444a;

    public boolean a(Context context) {
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    GuestAccountLoader() {
    }

    public String a() {
        if (this.f7444a == null) {
            return "";
        }
        return this.f7444a.c();
    }

    public AccountToken b() {
        return this.f7444a;
    }

    public synchronized void b(Context context) throws PaymentException {
        try {
            this.f7444a = AccountRegistry.b().a(context, CommonConstants.C);
        } catch (AccountsException e) {
            throw new AccountException((Throwable) e);
        }
    }

    public synchronized void c(Context context) throws PaymentException {
        try {
            this.f7444a = AccountRegistry.b().b(context, CommonConstants.C);
        } catch (AccountsException e) {
            throw new AccountException((Throwable) e);
        }
    }
}
