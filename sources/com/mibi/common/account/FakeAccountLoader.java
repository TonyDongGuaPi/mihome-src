package com.mibi.common.account;

import android.accounts.Account;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.mibi.common.exception.PaymentException;

public class FakeAccountLoader implements AccountLoader {
    public static final Parcelable.Creator<AccountLoader> CREATOR = new Parcelable.Creator<AccountLoader>() {
        /* renamed from: a */
        public FakeAccountLoader[] newArray(int i) {
            return new FakeAccountLoader[i];
        }

        /* renamed from: a */
        public AccountLoader createFromParcel(Parcel parcel) {
            return new FakeAccountLoader();
        }
    };

    public String a() {
        return "-1";
    }

    public boolean a(Context context) {
        return false;
    }

    public void b(Context context) throws PaymentException {
    }

    public void c(Context context) throws PaymentException {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    public static boolean a(Account account) {
        if (account == null) {
            return false;
        }
        return TextUtils.equals("-1", account.name);
    }

    public AccountToken b() {
        return new AccountToken("-1", "", "", "");
    }
}
